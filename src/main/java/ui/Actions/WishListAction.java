package ui.Actions;



import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ui.Utils.*;
import ui.pages.WishListPage;

import java.util.ArrayList;
import java.util.List;


public class WishListAction {

    private WebDriver driver;
    WishListPage wishListPage = new WishListPage();

    private String searchitem;
    public List<WebElement> wishlisteditems;
    public List<WebElement> wishlists;
    public List<String> wishlistedItemsName = new ArrayList<>();

    public WishListAction(WebDriver driver) {
        this.driver = driver;
    }

    //This method is going to search for an item in the searchbox
    public void enterSearchItem()  {

        searchitem= TestUtils.getProperty("WishlistSearchItem");

        driver.findElement(wishListPage.SearchTextBox).sendKeys(searchitem);

        TestUtils.pressEnterKey(driver,wishListPage.SearchTextBox);

        ExtentManager.getTest().info("Entered "+ searchitem + " in the searchbox");

        Log.info("Entered"+ searchitem + "in the searchbox");
    }

    //This item is going to verify the search header
    public void verifyTheSearchHeader()  {
        String actualSearchTxt=TestUtils.getTextFromElement(driver,wishListPage.HeaderTextSearch).replace("'", "");
        String path=TestUtils.takeScreenshot(driver);
        try
        {
            Assert.assertEquals(actualSearchTxt, searchitem, "Searched item is present in the search text header");
            ExtentManager.getTest().pass("Searched item is present in header", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info(searchitem +"Present in the Search Text Header");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Sign up is not successful",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error(searchitem +"is not present in the Search Text Header");
        }
    }

    //This method will click on all the checkboxes of category filter
    public void clickOnAllFilters()  {
        driver.findElement(wishListPage.CatagoryFilter).click();

        TestUtils.clickOnElements(driver,wishListPage.FilterCheckboxes);

        WaitUtils.waitExplicitlyForElemTobeInvisible(driver,wishListPage.PrimaryCatagoryTxtInFilter);

        WaitUtils.waitImplicitly(driver,10);

        Log.info("Checked all filters");

    }

    //This method will add wishlist items
    public void addWishlistItems() {
        MouseHover.mouseHover(driver,wishListPage.ExcludeOutOfStockChkbox);
        MouseHover.mouseHoverAndClick(driver,Integer.parseInt(TestUtils.getProperty("NoOfWishlistedItems")),wishListPage.ImageMousehover,wishListPage.WishlistsCheckbox);
    }

    //This method will retrieve the wishlisted items
    public void retriveWishListedItems(){
        WaitUtils.waitExplicitlyForWebElementVisible(driver,wishListPage.WishListedItems);
        wishlisteditems=TestUtils.returnList(driver,wishListPage.WishListedItems);
        for (int wishlisteditem=0; wishlisteditem<wishlisteditems.size();wishlisteditem++)
        {
            wishlistedItemsName.add(wishlisteditems.get(wishlisteditem).getText());
            System.out.println(wishlisteditems.get(wishlisteditem).getText());
        }

    }

    //This method will navigate to the Wishlist page
    public void navigateToWishListPage() {

        TestUtils.scrollUsingJS(driver,wishListPage.WishListIcon);

        driver.findElement(wishListPage.WishListIcon).click();
        WaitUtils.waitImplicitly(driver,3);

        String path=TestUtils.takeScreenshot(driver);
        String expectedHeading=TestUtils.getProperty("WishListHeading");
        String actualHeading=driver.findElement(wishListPage.WishListHeader).getText();
        try
        {
            TestUtils.assertEquals(actualHeading, expectedHeading, "Navigated to Wishlist Page");
            ExtentManager.getTest().pass("Navigated to the Wishlist page", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info("Wishlist page has opened");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Sign up is not successful",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error("Wishlist page has not opened");
        }
    }

    //This method will validate the wishlisted items
    public void validationOfWishListedItems() {

        wishlists=TestUtils.returnList(driver,wishListPage.WishLists);
        int wishlistedutemssize = wishlisteditems.size();
        for (int wishlisteditemno = 0; wishlisteditemno < wishlistedutemssize; wishlisteditemno++) {
            String actual = wishlists.get(wishlisteditemno).getText();

            try {

                Assert.assertTrue(wishlistedItemsName.contains(actual));
                ExtentManager.getTest().pass(" Actual Wishlist item" + actual + " is present in the Expected Wishlisted item");
                Log.info(wishlisteditemno + " no item matched");

            } catch (Exception e) {
                ExtentManager.getTest().fail("Actual Wishlist item" + actual + " is not present in the Expected Wishlisted item");
                Log.error(wishlisteditemno + " no item not matched");
            }


        }
    }

    }
