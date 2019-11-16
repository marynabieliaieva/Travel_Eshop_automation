package qa.Pages;

import dataProvider.ConfigFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
  public CartPage(WebDriver driver) {
    super(driver);
  }

  ConfigFileReader configFileReader = new ConfigFileReader();

  //Page elements


  //Page methods
  public CartPage isInitialized() {
    String URL = driver.getCurrentUrl();
    Assert.assertEquals(URL, configFileReader.getApplicationUrl() + "kosik");
    return this;
  }

  public List<String> getItemTitlesStringList() {
    isInitialized();
    List<WebElement> itemElementsInCart = driver.findElements(By.cssSelector("div.name>a>span")); //find all item title elements
    ArrayList<String> itemTitlesInCart = new ArrayList<>();
    for (WebElement item : itemElementsInCart) {
      String itemTitleInCart = item.getText().toLowerCase(); //get string value for each title element, method toLowerCase for future comparation
      itemTitlesInCart.contains(itemTitleInCart); //add each title element string value to ArrayList
    }
    return itemTitlesInCart;
  }

  public ItemPage returnToItem() {
    returnToPreviousPage();
    return new ItemPage(driver);
  }

}
