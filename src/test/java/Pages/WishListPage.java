package Pages;

import Base.DriverManager;
import Utils.ExtentManager;
import Utils.Log;
import Utils.TestUtils;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WishListPage {

    private WebDriver driver;
    private String searchitem;
    public List<WebElement> wishlisteditems;
    public List<WebElement> wishlists;
    public List<String> wishlistedItemsName = new ArrayList<>();

    public WishListPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearchItem() throws IOException, ParseException {
        searchitem=TestUtils.getProperty("SearchItem");;
        TestUtils.EnterValue(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("SearchTextBox"),searchitem);
        TestUtils.PressEnterKey(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("SearchTextBox"));
        Log.info("Entered"+ searchitem + "in the searchbox");
    }

    public void verifyTheSearchHeader() throws IOException, ParseException {
        String actualSearchTxt=TestUtils.getTextFromElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("HeaderTextSearch")).replace("'", "");
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

        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("CatagoryFilter"));
        TestUtils.ClickOnElements(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("FilterCheckboxes"));
        TestUtils.waitImplicitly(driver,10);

        Log.info("Checked all filters");

    }

    public void addWishlistItems() throws IOException, ParseException, InterruptedException {
//        TestUtils.waitExplicitlyForWebElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("Firstitem"));
        TestUtils.waitExplicitlyForElemTobeClickable(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("Firstitem"));
        TestUtils.MouseHoverAndClick(driver,3,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("ImageMousehover"),(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("WishlistsCheckbox"));


    }

    public void retriveWishListedItems() throws IOException, ParseException {
        wishlisteditems=TestUtils.ReturnList(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("WishListedItems"));
        for (int i=0;i<wishlisteditems.size();i++)
        {
            wishlistedItemsName.add(wishlisteditems.get(i).getText());
            System.out.println(wishlisteditems.get(i).getText());
        }

    }

    public void navigateToWishListPage() throws IOException, ParseException {
        TestUtils.ScrollUsingJS(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("WishListIcon"));
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("WishListIcon"));
        TestUtils.waitImplicitly(driver,3);
        String path=TestUtils.takeScreenshot(driver);
        String expectedHeading=TestUtils.getProperty("WishListHeading");
        String actualHeading= TestUtils.getTextFromElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("WishListHeader"));
        try
        {
            Assert.assertEquals(actualHeading, expectedHeading, "Navigated to Wishlist Page");
            ExtentManager.getTest().pass("Navigated to the Wishlist page", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info("Wishlist page has opened");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Sign up is not successful",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error("Wishlist page has not opened");
        }
    }

    public void validationOfWishListedItems() throws IOException, ParseException {
        wishlists=TestUtils.ReturnList(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("WishLists"));
        int size= wishlisteditems.size();
        for(int i=0;i<size;i++)
        {
            String actual=wishlists.get(i).getText();

                try {

                    Assert.assertTrue(wishlistedItemsName.contains(actual));
                    ExtentManager.getTest().pass( " Actual Wishlist item" + actual + " is present in the Expected Wishlisted item" );
                    Log.info(i + " no item matched");

                } catch (Exception e) {
                    ExtentManager.getTest().fail("Actual Wishlist item" + actual + " is not present in the Expected Wishlisted item");
                    Log.error(i + " no item not matched");
                }


        }

        DriverManager.quitDriver();
        ExtentManager.getInstance().flush();
    }
}
