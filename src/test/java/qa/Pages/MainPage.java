package qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
  public MainPage(WebDriver driver) {
    super(driver);
  }

  //Page elements
  @FindBy(css = ".vc-headerlogo")
  private WebElement eshopLogo;

  @FindBy(css = ".popupWindow")
  private WebElement discountPopUp;

  @FindBy(xpath = "//span[.='Zavazadla']")
  private WebElement suitcasesCategory;

  //Page methods
  public MainPage isInitialized() {
    waitElement(this.eshopLogo);
    return this;
  }

  public MainPage closePopUp() {
    if (this.discountPopUp.isDisplayed()) {
      refreshPage();
    }
    return this;
  }

  public CategoryPage goToCategoryPage() {
    this.suitcasesCategory.click();
    return new CategoryPage(driver);
  }
}







