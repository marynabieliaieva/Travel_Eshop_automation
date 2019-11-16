package qa.Tests;

import dataProvider.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


public class TestBase {
  ConfigFileReader configFileReader = new ConfigFileReader();
  //this way of drivers initializing is suitable only for small number of automated tests.
  // In the case of a large test set, good practice is to use Selenium grid or Selenoid
  WebDriver driver = new ChromeDriver();
//  WebDriver driver = new FirefoxDriver();
//  WebDriver driver = new InternetExplorerDriver();

  @BeforeTest
  public void setUp() throws Exception {
    driver.get(configFileReader.getApplicationUrl());
    driver.manage().window().maximize();
  }

  @AfterTest
  public void TearDown() {
    driver.quit();
  }
}
