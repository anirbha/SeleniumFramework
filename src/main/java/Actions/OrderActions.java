package Actions;

import Base.DriverManager;
import Utils.ExtentManager;
import Utils.Log;
import Utils.TestUtils;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.OrderPage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderActions {

    private WebDriver driver;
    OrderPage orderPage=new OrderPage();
    public String pricerange;
    public List<String> brandnames=new ArrayList<>();
    public List<String> storagenames=new ArrayList<>();
    public List<String> materialnames=new ArrayList<>();
    public List<String> productnames=new ArrayList<>();
    public List<String> items=new ArrayList<>();
    public String totalPayableamnt;

    public OrderActions(WebDriver driver)
    {
        this.driver=driver;
    }

    public void navigateToCrokery() {

        TestUtils.mouseHover(driver,orderPage.Dinning);
        driver.findElement(orderPage.CrockeryItems).click();

    }

    public void validateNavToCrokeryPage() {
        boolean crockeryheaderflag= TestUtils.CheckElementIsVisible(driver,orderPage.CrockeryHeader);
        String path=TestUtils.takeScreenshot(driver);
        try{
            Assert.assertTrue(crockeryheaderflag);
            ExtentManager.getTest().pass( "Navigated to the Crockery page", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info("Navigated to the Crockery page");

        } catch (Exception e) {
            ExtentManager.getTest().fail( "Not navigated to the Crockery page",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error("Not navigated to the Crockery page");
        }
    }

    public void selectPriceFilter() {

        TestUtils.mouseHover(driver,orderPage.PriceFilter);

        TestUtils.waitExplicitlyForElemTobeClickable(driver,orderPage.PriceFilterRadioBtn);

        String price=driver.findElement(orderPage.SpecificPrice).getText();

        pricerange=price.replaceAll("\\s+", "");

        ExtentManager.getTest().info( "Price Filter selected" );

        Log.info("Price Filter selected");

    }

    public void selectBrandFilter() throws IOException, ParseException, InterruptedException {
      TestUtils.waitExplicitlyForElemTobeClickable(driver,orderPage.BrandFilter);

      TestUtils.mouseHover(driver,orderPage.BrandFilter);

      TestUtils.MouseHoverFilterAndClick(driver,orderPage.BrandFilter,orderPage.BrandFilterCheckboxes);

      List<WebElement> checkboxnames=TestUtils.RetrieveCheckboxNames(driver,orderPage.BrandFilter,orderPage.BrandFilterNames);

        for(int chkboxname=0; chkboxname<checkboxnames.size();chkboxname++)
        {
            brandnames.add(checkboxnames.get(chkboxname).getText());
        }
        ExtentManager.getTest().info( "Brand Filter selected" );
        Log.info("Brand Filter selected");

    }

    public void storageFilter() throws IOException, ParseException, InterruptedException {

        TestUtils.waitExplicitlyForElemTobeClickable(driver,orderPage.StorageFilter);

        TestUtils.MouseHoverFilterAndClick(driver,orderPage.StorageFilter,orderPage.StorageFilterCheckboxes);

        List<WebElement> checkboxnames=TestUtils.RetrieveCheckboxNames(driver,orderPage.StorageFilter,orderPage.StorageFilterNames);

        for (int checkboxname=0; checkboxname<checkboxnames.size();checkboxname++)
        {
            storagenames.add(checkboxnames.get(checkboxname).getText());
        }

        ExtentManager.getTest().info( "Storage Filter selected" );
        Log.info("Storage Filter selected");
    }

    public void materialFilter() throws InterruptedException {

        TestUtils.waitExplicitlyForWebElementVisible(driver,orderPage.MaterialFilter);

        TestUtils.MouseHoverFilterAndClick(driver,orderPage.MaterialFilter,orderPage.MaterialCheckbox);

        List<WebElement> checkboxnames=TestUtils.RetrieveCheckboxNames(driver,orderPage.MaterialFilter,orderPage.MaterialFilterNames);

        for (int checkbxname=0; checkbxname<checkboxnames.size();checkbxname++)
        {
            materialnames.add(checkboxnames.get(checkbxname).getText());
        }
        ExtentManager.getTest().info( "Material Filter selected" );
        Log.info("Material Filter selected");
    }

    public void validatePriceFilter() {
        String actualtext=driver.findElement(orderPage.AppliedPriceFilter).getText();
        String actualprice=actualtext.replaceAll("\\s+", "");
        try
        {
            TestUtils.assertEquals(actualprice,pricerange,"The Price matches");
            ExtentManager.getTest().pass( "The Price matches" );
            Log.info("The Price matches");
        } catch (Exception e) {
            ExtentManager.getTest().fail( "The Price didn't matched" );
            Log.info("The Price didn't matched");
        }
    }

    public void validateBrandFilter() {

        List<WebElement> appliedBrandFilters= TestUtils.ReturnList(driver,orderPage.AppliedBrandFilter);

        for(int brandfltrname=0; brandfltrname<appliedBrandFilters.size();brandfltrname++)
        {
            String actual=appliedBrandFilters.get(brandfltrname).getText();

            try {
                Assert.assertTrue(brandnames.contains(actual));
                ExtentManager.getTest().pass( "Actual Filter" + actual + " is present in the Expected Filter items" );
                Log.info(brandfltrname + " no filter matched");

            } catch (Exception e) {
                ExtentManager.getTest().fail("Actual Filter" + actual + " is not present in the Expected Filter item");
                Log.error(brandfltrname + " no filter not matched");
            }
        }
    }

    public void validateStorageFilter() {
        List<WebElement> appliedStorageFilters= TestUtils.ReturnList(driver,orderPage.AppliedStorageFilter);
        for(int storagefltrname=0; storagefltrname<appliedStorageFilters.size();storagefltrname++)
        {
            String actual=appliedStorageFilters.get(storagefltrname).getText();

            try {
                Assert.assertTrue(storagenames.contains(actual));
                ExtentManager.getTest().pass( "Actual Filter" + actual + " is present in the Expected Filter items" );
                Log.info(storagefltrname + " no filter matched");

            } catch (Exception e) {
                ExtentManager.getTest().fail("Actual Filter" + actual + " is not present in the Expected Filter item");
                Log.error(storagefltrname + " no filter not matched");
            }
        }
    }

    public void validateMaterialFilter() {
        List<WebElement> appliedMaterialFilters= TestUtils.ReturnList(driver,orderPage.AppliedMaterialFilter);
        for(int apldmatrlfiltr=0; apldmatrlfiltr<appliedMaterialFilters.size();apldmatrlfiltr++)
        {
            String actual=appliedMaterialFilters.get(apldmatrlfiltr).getText();

            try {
                Assert.assertTrue(materialnames.contains(actual));
                ExtentManager.getTest().pass( "Actual Filter" + actual + " is present in the Expected Filter items" );
                Log.info(apldmatrlfiltr + " no filter matched");

            } catch (Exception e) {
                ExtentManager.getTest().fail("Actual Filter" + actual + " is not present in the Expected Filter item");
                Log.error(apldmatrlfiltr + " no filter not matched");
            }
        }
    }
    public void clickOnAnyThreeItems() throws InterruptedException {
        String mainWindowId=TestUtils.getWindoWId(driver);

        List<WebElement> clickableItems=TestUtils.ReturnList(driver,orderPage.ItemsTobeClickable);

        for(int clickableitem=0; clickableitem<3;clickableitem++)
        {
            clickableItems.get(clickableitem).click();

        }
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();

        // Switch to the new tab
        for (String handle: windowHandles) {

            if (!handle.equals(mainWindowId)) {

                driver.switchTo().window(handle);
                TestUtils.waitImplicitly(driver, 7);
                String path=TestUtils.takeScreenshot(driver);
                ExtentManager.getTest().pass("Switched to new window"+MediaEntityBuilder.createScreenCaptureFromPath(path).build());
                Log.info("Switched to a new tab");
                TestUtils.waitExplicitlyForWebElementVisible(driver,orderPage.ItemHeader);
                Thread.sleep(2000);

                productnames.add(driver.findElement(orderPage.ItemHeader).getText());

                driver.findElement(orderPage.AddToCartButton).click();
            }
        }
        driver.switchTo().window(mainWindowId);
        Log.info("Switched to the main tab");
    }

    public void checkOutFromTheCart() {
        TestUtils.ScrollToTop(driver);
        driver.findElement(orderPage.CartButton).click();
        driver.findElement(orderPage.CheckOutButton).click();

        String path=TestUtils.takeScreenshot(driver);
        ExtentManager.getTest().info("Navigated to the Cart page", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        Log.info("Navigated to the Cart page");
    }

    public void fillUpAddress() throws IOException {
        TestUtils.EnterValue(driver,orderPage.PinCode,TestUtils.ReadExcelData(0,1,5));
        driver.findElement(orderPage.Address).clear();
        TestUtils.EnterValue(driver,orderPage.Address,TestUtils.ReadExcelData(0,1,6));
        driver.findElement(orderPage.FirstName).clear();
        TestUtils.EnterValue(driver,orderPage.FirstName,TestUtils.ReadExcelData(0,1,3));
        driver.findElement(orderPage.LastName).clear();
        TestUtils.EnterValue(driver,orderPage.LastName,TestUtils.ReadExcelData(0,1,0));
        TestUtils.EnterValue(driver,orderPage.Mobile,TestUtils.ReadExcelData(0,1,4));

        List<WebElement> itemsOrdered=TestUtils.ReturnList(driver,orderPage.ItemProductnames);

        for(int itemorderno=1;itemorderno<itemsOrdered.size();itemorderno++ )
        {
            System.out.println(itemsOrdered.get(itemorderno).getText());
            items.add(itemsOrdered.get(itemorderno).getText());
        }
        TestUtils.waitExplicitlyForWebElementVisible(driver,orderPage.totalpayableamount);
        totalPayableamnt=driver.findElement(orderPage.totalpayableamount).getText();
        System.out.println(totalPayableamnt);
        String path=TestUtils.takeScreenshot(driver);
        ExtentManager.getTest().info("All the details are filled in Address page", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        Log.info("All the details are filled in Address page");
    }

    public void clickSaveAndContBtn(){
       driver.findElement(orderPage. saveandcontbtn).click();
       TestUtils.waitImplicitly(driver,13);
        String path=TestUtils.takeScreenshot(driver);

        ExtentManager.getTest().info("Navigated to the Payment page", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        Log.info("Navigated to the Payment page");
    }

    public void validateFinalPrice() throws InterruptedException {
        Thread.sleep(15000);
        TestUtils.waitExplicitlyForWebElementVisible(driver,orderPage.grandtotal);
        Thread.sleep(2000);
        String grandtotal=driver.findElement(orderPage.grandtotal).getText();

        String path=TestUtils.takeScreenshot(driver);
        try
        {
            TestUtils.assertEquals(grandtotal,totalPayableamnt,"Final Price Matched");
            ExtentManager.getTest().pass("Final Price Matched", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.info("Final Price Matched");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Final Price is not Matched", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.info("Final Price didn't Matched");
        }
    }

    public void validateFinalItems() {

        List<WebElement> itemsOrdered=TestUtils.ReturnList(driver,orderPage.finalproductnames);
        String path=TestUtils.takeScreenshot(driver);
        for(int itemOrderno=1;itemOrderno<itemsOrdered.size();itemOrderno++)
        {
            String actual=itemsOrdered.get(itemOrderno).getText();

            try {
                Assert.assertTrue(items.contains(actual));
                ExtentManager.getTest().pass( "Item Matched", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
                Log.info(itemOrderno + " no item matched");

            } catch (Exception e) {
                ExtentManager.getTest().fail("Item didn't match" + actual + " is not present in the Expected item list",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
                Log.error(itemOrderno + " no item not matched");
            }
        }

        DriverManager.quitDriver();

    }
}
