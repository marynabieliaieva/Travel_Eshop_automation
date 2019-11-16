package qa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class CategoryPage extends BasePage {
  public CategoryPage(WebDriver driver) {
    super(driver);
  }

  JavascriptExecutor js = (JavascriptExecutor) driver;
  //Page elements
  @FindBy(css = ".categoryName")
  private WebElement categoryNamePageHeader;

  @FindBy(xpath = "//a[@href='?sortOrder=1&sortBy=Price']")
  private WebElement sortPriceDescending;

  @FindBy(xpath = "//a[@href='?sortOrder=1&sortBy=Price']/parent::li[@class='active']")
  private WebElement sortPriceDescendingActive;

  @FindBy(css = ".thumb.main")
  private WebElement itemElement;

  //Page methods
  public CategoryPage isInitialized() {
    waitElement(this.categoryNamePageHeader);
    return this;
  }

  public CategoryPage sortPriceDescending() {
    waitElement(this.sortPriceDescending);
    this.sortPriceDescending.click(); // switch to sorting by price descending
    assertTrue(this.sortPriceDescendingActive.isEnabled()); //verify that sorting by price descending filter is active
    return this;
  }

  //Use addItemToCartAndVerify method we can select any of 40 products
  //In the case if will be passed as a parameter negative number or number greater than 39
  // - test will show warning message and finish as fail
  public CartPage addItemToCartAndVerify(int number) throws AWTException {
    if (number < 0 || number > 39) {
      System.out.println("Warning! addItemToCartAndVerify method parameter value must be from 0 to 39");
    } else {
      List<WebElement> items = driver.findElements(By.cssSelector("h3.title")); //find all item title elements
      String itemTitleCategoryPage = items.get(number).getText().toLowerCase();
      js.executeScript("window.scrollBy(0,500)"); //at some screen resolutions, the button is hidden under the chat window
      items.get(number).click();
      WebElement item = driver.findElement(By.cssSelector("h1")); //find element title
      String itemTitleItemPage = item.getText().toLowerCase(); //get string value for each title element, method toLowerCase for future comparation
      assertEquals(itemTitleCategoryPage, itemTitleItemPage); //compare titles from Category page and Item page
      ItemPage itemPage = new ItemPage(driver);
      itemPage.isInitialized()
              .addItemToCart()
              .verifyItemInCart()
              .moveToCart();
      CartPage cartPage = new CartPage(driver);
      cartPage.getItemTitlesStringList().contains(itemTitleItemPage); //verify if item is in cart
    }
    return new CartPage(driver);
  }
}
