package ui.Actions;



import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ui.Utils.*;
import ui.pages.OrderPage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderActions {

    private WebDriver driver;
    OrderPage orderPage=new OrderPage();

    public List<String> brandnames=new ArrayList<>();
    public List<String> storagenames=new ArrayList<>();
    public List<String> materialnames=new ArrayList<>();
    public List<String> productnames=new ArrayList<>();
    public List<String> items=new ArrayList<>();
    public String totalPayableamnt;
    public String pricerange;

    public OrderActions(WebDriver driver)
    {
        this.driver=driver;
    }

    //This method will navigate to the Crockery page
    public void navigateToCrokery() {
        MouseHover.mouseHover(driver,orderPage.Dinning);
        driver.findElement(orderPage.CrockeryItems).click();
    }

    //This method will validate the navigation to Crokery page
    public void validateNavToCrokeryPage() {
        boolean crockeryheaderflag= TestUtils.checkElementIsVisible(driver,orderPage.CrockeryHeader);
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

    //This method will select the Price range radio button under Price Filter
    public void selectPriceFilter() {

        MouseHover.mouseHover(driver,orderPage.PriceFilter);

        WaitUtils.waitExplicitlyForElemTobeClickable(driver,orderPage.PriceFilterRadioBtn);

        driver.findElement(orderPage.PriceFilterRadioBtn).click();

        String price=driver.findElement(orderPage.SpecificPrice).getText();

        pricerange=price.replaceAll("\\s+", "");

        ExtentManager.getTest().info( "Price Filter selected" );

        Log.info("Price Filter selected");

    }

    //This method will select the checkboxes of different brands under Brand Filter
    public void selectBrandFilter() {
      WaitUtils.waitExplicitlyForElemTobeInvisible(driver,orderPage.PriceFilterRadioBtn);

      WaitUtils.waitExplicitlyForElemTobeClickable(driver,orderPage.BrandFilter);

      MouseHover.mouseHoverAndClick(driver,orderPage.BrandFilter,orderPage.BrandFilterCheckboxes);

      List<WebElement> checkboxnames=MouseHover.mouseHoverAndGetListOfNames(driver,orderPage.BrandFilter,orderPage.BrandFilterNames);

        for(int chkboxname=0; chkboxname<checkboxnames.size();chkboxname++)
        {
            brandnames.add(checkboxnames.get(chkboxname).getText());
        }
        ExtentManager.getTest().info( "Brand Filter selected" );
        Log.info("Brand Filter selected");

    }

    //This method will select the checkboxes of different Storage Options under Brand Filter
    public void selectStorageFilter() {

        WaitUtils.waitExplicitlyForElemTobeClickable(driver,orderPage.StorageFilter);

        MouseHover.mouseHoverAndClick(driver,orderPage.StorageFilter,orderPage.StorageFilterCheckboxes);

        List<WebElement> checkboxnames=MouseHover.mouseHoverAndGetListOfNames(driver,orderPage.StorageFilter,orderPage.StorageFilterNames);

        for (int checkboxname=0; checkboxname<checkboxnames.size();checkboxname++)
        {
            storagenames.add(checkboxnames.get(checkboxname).getText());
        }

        ExtentManager.getTest().info( "Storage Filter selected" );
        Log.info("Storage Filter selected");
    }

    //This method will select the checkboxes of different Material Types
    public void selectMaterialFilter() {

        WaitUtils.waitExplicitlyForWebElementVisible(driver,orderPage.MaterialFilter);

        MouseHover.mouseHoverAndClick(driver,orderPage.MaterialFilter,orderPage.MaterialCheckbox);

        List<WebElement> checkboxnames=MouseHover.mouseHoverAndGetListOfNames(driver,orderPage.MaterialFilter,orderPage.MaterialFilterNames);

        for (int checkbxname=0; checkbxname<checkboxnames.size();checkbxname++)
        {
            materialnames.add(checkboxnames.get(checkbxname).getText());
        }
        ExtentManager.getTest().info( "Material Filter selected" );
        Log.info("Material Filter selected");
    }

    //This method will validate the selection of Price Filter
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

    //This method will validate the selection of Brand Filter
    public void validateBrandFilter() {

        List<WebElement> appliedBrandFilters= TestUtils.returnList(driver,orderPage.AppliedBrandFilter);

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

    //This method will validate the selection of Storage Filter
    public void validateStorageFilter() {
        List<WebElement> appliedStorageFilters= TestUtils.returnList(driver,orderPage.AppliedStorageFilter);
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

    //This method will validate the selection of Material Filter
    public void validateMaterialFilter() {
        List<WebElement> appliedMaterialFilters= TestUtils.returnList(driver,orderPage.AppliedMaterialFilter);
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
    //This method will click on any three filtered items. Each and every item is opened in a new tab
    public void clickOnAnyThreeItems()  {
        String mainWindowId=TestUtils.getWindoWId(driver);

        List<WebElement> clickableItems=TestUtils.returnList(driver,orderPage.ItemsTobeClickable);

        for(int clickableitem=0; clickableitem<3;clickableitem++)
        {
            clickableItems.get(clickableitem).click();

        }
        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();

        // Switch to the new tab
        try {
            for (String handle : windowHandles) {

                if (!handle.equals(mainWindowId)) {

                    driver.switchTo().window(handle);
                    WaitUtils.waitImplicitly(driver, 7);
                    String path = TestUtils.takeScreenshot(driver);
                    ExtentManager.getTest().pass("Switched to new window" + MediaEntityBuilder.createScreenCaptureFromPath(path).build());
                    Log.info("Switched to a new tab");
                    WaitUtils.waitExplicitlyForWebElementVisible(driver, orderPage.ItemHeader);


                    productnames.add(driver.findElement(orderPage.ItemHeader).getText());

                    driver.findElement(orderPage.AddToCartButton).click();
                }
            }
            driver.switchTo().window(mainWindowId);
            Log.info("Switched to the main tab");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Exception occurred" + e);
        }
    }

    //This method will navigate to Cart first and then click on the CheckOut button
    public void checkOutFromTheCart() {
        TestUtils.scrollToTop(driver);
        WaitUtils.waitExplicitlyForElemTobeClickable(driver,orderPage.CartButton);
        driver.findElement(orderPage.CartButton).click();
        WaitUtils.waitExplicitlyForElemTobeClickable(driver,orderPage.CheckOutButton);
        driver.findElement(orderPage.CheckOutButton).click();

        String path=TestUtils.takeScreenshot(driver);
        ExtentManager.getTest().info("Navigated to the Cart page", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        Log.info("Navigated to the Cart page");
    }

    //This method will fill up the address details in the Address page
    public void fillUpAddress() throws IOException {
        TestUtils.enterValue(driver,orderPage.PinCode, ExcelUtils.readExcelData(0,1,5));
        driver.findElement(orderPage.Address).clear();
        TestUtils.enterValue(driver,orderPage.Address,ExcelUtils.readExcelData(0,1,6));
        driver.findElement(orderPage.FirstName).clear();
        TestUtils.enterValue(driver,orderPage.FirstName,ExcelUtils.readExcelData(0,1,3));
        driver.findElement(orderPage.LastName).clear();
        TestUtils.enterValue(driver,orderPage.LastName,ExcelUtils.readExcelData(0,1,0));
        TestUtils.enterValue(driver,orderPage.Mobile,ExcelUtils.readExcelData(0,1,4));

        List<WebElement> itemsOrdered=TestUtils.returnList(driver,orderPage.ItemProductnames);

        for(int itemorderno=1;itemorderno<itemsOrdered.size();itemorderno++ )
        {
            System.out.println(itemsOrdered.get(itemorderno).getText());
            items.add(itemsOrdered.get(itemorderno).getText());
        }
        WaitUtils.waitExplicitlyForWebElementVisible(driver,orderPage.totalpayableamount);
        totalPayableamnt=driver.findElement(orderPage.totalpayableamount).getText();
        System.out.println(totalPayableamnt);
        String path=TestUtils.takeScreenshot(driver);
        ExtentManager.getTest().info("All the details are filled in Address page", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        Log.info("All the details are filled in Address page");
    }
    //This method will navigate to Payment page by clicking the SAve and Continue button
    public void clickSaveAndContBtn(){
       driver.findElement(orderPage. saveandcontbtn).click();
       WaitUtils.waitImplicitly(driver,13);
        String path=TestUtils.takeScreenshot(driver);

        ExtentManager.getTest().info("Navigated to the Payment page", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        Log.info("Navigated to the Payment page");
    }

    //This method will validate the final price in the payment page with the order page
    public void validateFinalPrice()  {


        WaitUtils.waitExplicitlyForWebElementVisible(driver,orderPage.grandtotal);

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

    //This method will validate the final items in the payment page with the order page
    public void validateFinalItems() {

        List<WebElement> itemsOrdered=TestUtils.returnList(driver,orderPage.finalproductnames);
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

    }
}
