package qa.Tests;

import org.testng.annotations.Test;
import qa.Pages.CartPage;
import qa.Pages.ItemPage;
import qa.Pages.MainPage;
import qa.Pages.CategoryPage;

public class AddItemsToCartTest extends TestBase {

  @Test
  public void cartAddTest() throws Exception {
    //Pages initialization
    MainPage mainPage = new MainPage(driver);
    CategoryPage categoryPage = new CategoryPage(driver);
    ItemPage itemPage = new ItemPage(driver);
    CartPage cartPage = new CartPage(driver);

    //Test steps
    mainPage.isInitialized()
            .closePopUp()
            .goToCategoryPage();
    categoryPage.isInitialized()
            .sortPriceDescending()
            .addItemToCartAndVerify(0); //put 0 for first item
    cartPage.returnToItem();
    itemPage.returnToCategory();
    categoryPage.isInitialized()
            .addItemToCartAndVerify(1); // put 1 for second item


  }


}

