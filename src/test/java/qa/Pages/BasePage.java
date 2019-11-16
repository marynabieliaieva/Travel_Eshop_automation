package qa.Pages;

import dataProvider.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
  protected WebDriver driver;
  ConfigFileReader configFileReader = new ConfigFileReader();

  public BasePage(WebDriver driver){
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void waitElement(WebElement element) {
    WebDriverWait wait = new WebDriverWait(driver, configFileReader.getTimeOut());
    wait.until(ExpectedConditions.visibilityOf(element));
  }

  public void refreshPage() {
    driver.navigate().refresh();
  }

  public void returnToPreviousPage() {
    driver.navigate().back();
  }
}
