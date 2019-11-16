package qa.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;


public class ItemPage extends BasePage {
  public ItemPage(WebDriver driver) {
    super(driver);
  }

  CategoryPage categoryPage = new CategoryPage(driver);
  JavascriptExecutor js = (JavascriptExecutor) driver;
  WebDriverWait wait = new WebDriverWait(driver, 50);

  //Page elements
  @FindBy(css = "h1")
  private WebElement itemName;

  @FindBy(css = ".btn.nd-btn-primary.buyBtn")
  private WebElement submitButton;

  @FindBy(css = "div.message.success")
  private WebElement messageSuccess;

  @FindBy(css = ".nd-btn-default")
  private WebElement returnToItemButton;

  @FindBy(css = "a[title='kufry']")
  private WebElement returnToCategoryButton;

  @FindBy(css = "a.btn-showBasket > span")
  private WebElement moveToCartButton;

  //Page methods
  public ItemPage isInitialized() {
    refreshPage();
    waitElement(this.itemName);
    return this;
  }

  public ItemPage addItemToCart() throws AWTException {
    js.executeScript("window.scrollBy(0,500)"); //at some screen resolutions, the button is hidden under the chat window
    try {
      waitElement(this.submitButton);
      this.submitButton.click();
    } catch (Exception e) {
      System.out.println("Warning! Selected item is out of stock."); //Submit button may not appear if hte product is out of stock.
    }
    return this;
  }

  public ItemPage verifyItemInCart() {
    waitElement(this.messageSuccess);
    return this;
  }

  public CategoryPage returnToCategory() {
    returnToPreviousPage();
    return new CategoryPage(driver);
  }

  public CartPage moveToCart() {
    waitElement(this.moveToCartButton);
    this.moveToCartButton.click();
    return new CartPage(driver);
  }


}