package Actions;

import Utils.ExtentManager;
import Utils.Log;
import Utils.TestUtils;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.WishListPage;
import java.io.IOException;
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

    public void enterSearchItem() throws IOException, ParseException {

        searchitem= TestUtils.getProperty("SearchItem");

        driver.findElement(wishListPage.SearchTextBox).sendKeys(searchitem);

        TestUtils.PressEnterKey(driver,wishListPage.SearchTextBox);

        ExtentManager.getTest().info("Entered "+ searchitem + " in the searchbox");

        Log.info("Entered"+ searchitem + "in the searchbox");
    }

    public void verifyTheSearchHeader() throws IOException, ParseException {
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

    public void clickOnAllFilters() throws IOException, ParseException, InterruptedException {
        driver.findElement(wishListPage.CatagoryFilter).click();

        TestUtils.ClickOnElements(driver,wishListPage.FilterCheckboxes);
        Thread.sleep(3000);
        TestUtils.waitImplicitly(driver,10);

        Log.info("Checked all filters");



    }

    public void addWishlistItems() {
        TestUtils.mouseHover(driver,wishListPage.ExcludeOutOfStockChkbox);
        TestUtils.MouseHoverAndClick(driver,3,wishListPage.ImageMousehover,wishListPage.WishlistsCheckbox);
    }

    public void retriveWishListedItems(){
        wishlisteditems=TestUtils.ReturnList(driver,wishListPage.WishListedItems);
        for (int wishlisteditem=0; wishlisteditem<wishlisteditems.size();wishlisteditem++)
        {
            wishlistedItemsName.add(wishlisteditems.get(wishlisteditem).getText());
            System.out.println(wishlisteditems.get(wishlisteditem).getText());
        }

    }

    public void navigateToWishListPage() {

        TestUtils.ScrollUsingJS(driver,wishListPage.WishListIcon);

        driver.findElement(wishListPage.WishListIcon).click();
        TestUtils.waitImplicitly(driver,3);

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

    public void validationOfWishListedItems() {

        wishlists=TestUtils.ReturnList(driver,wishListPage.WishLists);
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
