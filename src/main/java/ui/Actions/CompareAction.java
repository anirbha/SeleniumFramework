package ui.Actions;


import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ui.Utils.*;
import ui.pages.ComparePage;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CompareAction {

    private WebDriver driver;
    ComparePage comparePage=new ComparePage();
    ExcelUtils excelUtils;

    
    private String searchitem;
    List<String> namesOfAddedToCompareItems;
    private  String firstSearchItemToCompare;
    private String secondSearchItemToCompare;
    
    public CompareAction(WebDriver driver)
    {
        this.driver=driver;
    }

    // This method will Enter the desired keywords in the search box
    public void enterInSearchBox() {

        searchitem= TestUtils.getProperty("SearchItemForCompare");

        driver.findElement(comparePage.SearchTextBox).sendKeys(searchitem);

        TestUtils.pressEnterKey(driver,comparePage.SearchTextBox);

        ExtentManager.getTest().info("Entered "+ searchitem + " in the searchbox");

        Log.info("Entered"+ searchitem + "in the searchbox");
    }

    // This method will verify whether the searched keyword is coming in the search header
    public void verifyTheSearchHeader()  {

        String actualSearchTxt=driver.findElement(comparePage.HeaderTextSearch).getText().replace("'","");
        String path=TestUtils.takeScreenshot(driver);
        try
        {
            TestUtils.assertEquals(actualSearchTxt, searchitem, "Searched item is present in the search text header");
            ExtentManager.getTest().pass("Searched item is present in header", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info(searchitem +"Present in the Search Text Header");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Sign up is not successful",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error(searchitem +"is not present in the Search Text Header");
        }
    }

    //This method will add 2 products to compare
    public void addProductsToCompare() {
        MouseHover.mouseHoverAndClick(driver,Integer.parseInt(TestUtils.getProperty("NumberOfItemsToCompare")),comparePage.hoverElement,comparePage.addToCompareBtn);
        ExtentManager.getTest().info("Products added to compare");
        Log.info("Products added to compare");

    }

    //This method will retrieve names of the Products added for comparison
    public void retrieveProductsToCompareItems() {

        namesOfAddedToCompareItems=MouseHover.mouseHoverAndGetListOfNames(driver,Integer.parseInt(TestUtils.getProperty("NumberOfItemsToCompare")),comparePage.hoverElement,comparePage.productTitle,comparePage.productNames);

        ExtentManager.getTest().info("Retrieved the item names added to compare");
        Log.info("Retrieved the item names added to compare");
    }

    // This method will navigate to the Compare page
    public void navigationToComparePage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(comparePage.CompareButton));

        Actions actions = new Actions(driver);

        // Perform mouse hover and click
        actions.moveToElement(driver.findElement(comparePage.CompareButton)).click(driver.findElement(comparePage.CompareButton)).build().perform();
        String path=TestUtils.takeScreenshot(driver);
        ExtentManager.getTest().info("Clicked on the Compare page",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        Log.info("Clicked on the Compare page");
    }

    //This method will verify the navigation to Compare page
    public void validateComparePageNav() {

        String headerText=driver.findElement(comparePage.ComparePageHeader).getText();
        String searchKeyWord=TestUtils.getProperty("SearchItemForCompare");
        String path=TestUtils.takeScreenshot(driver);
        try
        {
            Assert.assertTrue(headerText.contains(TestUtils.getProperty("SearchItemForCompare")));
            ExtentManager.getTest().pass("Navigated to the Add to Compare page and the search keyword " + searchKeyWord +" is present in the header",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.info("Navigated to the Add to Compare page and the search keyword" + searchKeyWord +" is present in the header");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Unable to navigate to the Add to Compare page",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error("Unable to navigate to the Add to Compare page");
        }
    }

    //This method will verify the items added for comparison is present in the compare page
    //Note: In the application the names are getting changed in the compare page than previous. So, validation is done partially.
    //The names are captured properly in the excel sheet for reference

    public void validateComparePageItems()  {
        List<WebElement> itemsInTheComparePg=TestUtils.returnList(driver,comparePage.ComparePageItems);
        List<String> nameOfItemsInComparePg = new ArrayList<>();
        int sizeOfItemsInTheComparePg =itemsInTheComparePg.size();


        for (int itemname=0; itemname<sizeOfItemsInTheComparePg;itemname++ )
        {
            nameOfItemsInComparePg.add(itemsInTheComparePg.get(itemname).getText());
            ExtentManager.getTest().info(itemname+" no item in compare page is "+itemsInTheComparePg.get(itemname).getText());
            Log.info(itemname+" no item in compare page is"+itemsInTheComparePg.get(itemname).getText());
        }

        for (int itemname=0;itemname< namesOfAddedToCompareItems.size();itemname++ )
        {
            ExtentManager.getTest().info(itemname+" no item in the namesOfAddedToCompareItems list is "+itemsInTheComparePg.get(itemname).getText());
            Log.info(itemname+" no item in the namesOfAddedToCompareItems list is "+itemsInTheComparePg.get(itemname).getText());
        }

        String path= TestUtils.takeScreenshot(driver);
        for (String itemname : nameOfItemsInComparePg) {
            try {
                String splitedItemname[]=itemname.split(" Bed");
                for(int i=0;i<namesOfAddedToCompareItems.size();i++) {
                    String nameToAddedCmpr = namesOfAddedToCompareItems.get(i);
                    Assert.assertTrue(nameToAddedCmpr.contains(splitedItemname[0]), "Item is present");
                    ExtentManager.getTest().pass("Items match with the Added To Compare list", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
                    );
                    Log.info(itemname + " Items match with the Added To Compare list");
                }

            } catch (Exception e) {

                ExtentManager.getTest().fail("Items didn't match with the Added To Compare list", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
                );
                Log.info("Items didn't match with the Added To Compare list");

            }
        }



        //-----------------------------------------Writing in excel---------------------------------------------------//

        excelUtils = new ExcelUtils(TestUtils.getProperty("ExcelTabName"), System.getProperty("user.dir")+TestUtils.getProperty("ExcelOutputPathToCompareNames"));

        excelUtils.writeHeader(new String[]{TestUtils.getProperty("ExcelCompariosnNamesHeaderCol1"),TestUtils.getProperty("ExcelCompariosnNamesHeaderCol2") });

        for (int item = 0; item < sizeOfItemsInTheComparePg; item++) {
            excelUtils.writeRow(item+1, new String[]{namesOfAddedToCompareItems.get(item), nameOfItemsInComparePg.get(item)});
        }
        try {
            excelUtils.saveAndClose();
            ExtentManager.getTest().pass("Details successfully captured in the excel");
            Log.info("Details successfully captured in the excel");
        }
        catch (IOException e) {
            ExtentManager.getTest().fail("Exception occurred while save and closing of the excel" + e);
            Log.error("Exception occurred while save and closing of the excel");
        }


    }

    //This method will write the comparison properties into the excel sheet.
    public void writeProductSpecificationIntoExcel()  {

        excelUtils = new ExcelUtils(TestUtils.getProperty("ExcelSheetName"), System.getProperty("user.dir")+TestUtils.getProperty("ExcelOutputPath"));

        excelUtils.writeHeader(new String[]{TestUtils.getProperty("ExcelSheetHeaderCol1"), namesOfAddedToCompareItems.get(0), namesOfAddedToCompareItems.get(1)});

        //for the 1st row to record price

        String firstproperty = driver.findElement(comparePage.PriceColumn).getText();
        List<WebElement> priceslist = driver.findElements(comparePage.PriceValues);

        List<String> prices = new ArrayList<>();

        for (WebElement element : priceslist) {
            prices.add(element.getText());
        }

        excelUtils.writeRow(1, new String[]{firstproperty, prices.get(0), prices.get(1)});


        //To get the property names
        List<String> propertynames = new ArrayList<>();
        List<WebElement> properties=TestUtils.returnList(driver,1,comparePage.comparableProperties,comparePage.propLabels);
        for(WebElement property:properties)
        {
            propertynames.add(property.getText());
        }

        //Retrieve the first elements values
        List<WebElement> firstElements =TestUtils.returnList(driver,1,comparePage.comparableProperties,comparePage.propValues);

        List<String> firstElementsProps = new ArrayList<>();

        for (WebElement element : firstElements) {
            firstElementsProps.add(element.getText());
        }

        //Retrieve the second elements values
        List<WebElement> secondElements =TestUtils.returnList(driver,1,comparePage.comparableProperties,comparePage.propValues);

        List<String> secondElementsProps = new ArrayList<>();

        for (WebElement element : secondElements) {
            secondElementsProps.add(element.getText());
        }
        // Remaining Row creation
        for (int propertyname = 0; propertyname < propertynames.size(); propertyname++) {
            excelUtils.writeRow(propertyname+1, new String[]{propertynames.get(propertyname), firstElementsProps.get(propertyname), secondElementsProps.get(propertyname)});
        }

        try {
            excelUtils.saveAndClose();
            ExtentManager.getTest().pass("Details successfully captured in the excel");
            Log.info("Details successfully captured in the excel");

        } catch (Exception e) {
            ExtentManager.getTest().fail("Exception occurred while save and closing of the excel" + e);
            Log.error("Exception occurred while save and closing of the excel");
        }

    }

    //------------------------------ Testcase6: Compare items of different collections -------------------------------//

    //This method will search Recliner Chairs in the search box
    public void enterReclinerinTheSearchbox() {

        firstSearchItemToCompare = TestUtils.getProperty("WishlistSearchItem");

        driver.findElement(comparePage.SearchTextBox).sendKeys(firstSearchItemToCompare);

        TestUtils.pressEnterKey(driver,comparePage.SearchTextBox);

        ExtentManager.getTest().info("Entered "+ firstSearchItemToCompare + " in the searchbox");

        Log.info("Entered"+ firstSearchItemToCompare + "in the searchbox");

    }

    //This method will verify the search header for the search Keyword
    public void verifyTheSearchHeaderForRecliner() {

        String actualSearchTxt=driver.findElement(comparePage.HeaderTextSearch).getText().replace("'","");
        String path=TestUtils.takeScreenshot(driver);
        try
        {
            TestUtils.assertEquals(actualSearchTxt, firstSearchItemToCompare, "Searched item is present in the search text header");
            ExtentManager.getTest().pass("Searched item is present in header", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info(firstSearchItemToCompare +"Present in the Search Text Header");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Sign up is not successful",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error(firstSearchItemToCompare +"is not present in the Search Text Header");
        }

    }

    //This method will add the first product to compare
    public void addProductToCompare() {
        try{
            MouseHover.mouseHoverAndClick(driver,Integer.parseInt(TestUtils.getProperty("NumOfItemsForNonCompare")),comparePage.hoverElement,comparePage.addToCompareBtn);
            ExtentManager.getTest().info("Products added to compare");
            Log.info("Products added to compare");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Products unable to add to compare");
            Log.error("Products unable to add to compare");
        }

    }

    //This method will enter the second keyword in the searchbox
    public void enterMattressInTheSearchBox() {

        secondSearchItemToCompare = TestUtils.getProperty("SearchItemForNonCompare");

        TestUtils.scrollUsingJS(driver,comparePage.SearchTextBox);

        WaitUtils.waitExplicitlyForElemTobeClickable(driver,comparePage.SearchTextBox);

        driver.findElement(comparePage.SearchTextBox).click();

        driver.findElement(comparePage.SearchTextBox).clear();

        driver.findElement(comparePage.SearchTextBox).sendKeys(Keys.CONTROL+"a");

        driver.findElement(comparePage.SearchTextBox).sendKeys(Keys.DELETE);

        driver.findElement(comparePage.SearchTextBox).sendKeys(secondSearchItemToCompare);

        TestUtils.pressEnterKey(driver,comparePage.SearchTextBox);

        ExtentManager.getTest().info("Entered "+ secondSearchItemToCompare + " in the searchbox");

        Log.info("Entered"+ secondSearchItemToCompare + "in the searchbox");

    }
    //This method will try to add the second searched product to compare
    public void tryToAddProductToCompare() {
        try{
            MouseHover.mouseHoverAndClick(driver,Integer.parseInt(TestUtils.getProperty("NumOfItemsForNonCompare")),comparePage.hoverElement,comparePage.addToCompareBtn);
            ExtentManager.getTest().info("Clicked on the Add to Product");
            Log.info("Clicked on the Add to Product");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Unable to click on the Add to Product");
            Log.error("Unable to click on the Add to Product");
        }
    }

    //This method will validate the error message as two different types of items are tried to compare
    public void validateUnableToCompare() {
        WaitUtils.waitExplicitlyForWebElementVisible(driver,comparePage.CantCompareText);
        String cantcomparetxt=driver.findElement(comparePage.CantCompareText).getText().substring(0,39);
        Log.info(cantcomparetxt);
        try{
            Assert.assertTrue(driver.findElement(comparePage.CantCompareText).isDisplayed());
            ExtentManager.getTest().info("Error Message displayed as "+ cantcomparetxt);
            Log.info("Error Message displayed as "+ cantcomparetxt);
        } catch (Exception e) {
            ExtentManager.getTest().fail("Error Message not displayed as "+ cantcomparetxt);
            Log.error("Error Message not displayed as "+ cantcomparetxt);
        }

    }
}

