package Utils;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;


public class TestUtils {

    private static Properties properties = new Properties();

        static {
            try (FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/resources/TestData/Config.properties")) {
                properties.load(input);
            } catch (Exception e) {
                throw new RuntimeException("Failed to load config.properties", e);
            }
        }

        public static String getProperty(String key) {
            return properties.getProperty(key);
        }

        public static JSONObject ReadJsonData() throws IOException, ParseException {
        try {
            String filePath = System.getProperty("user.dir") + getProperty("JsonPath") ;
            FileReader reader = new FileReader(filePath);
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(reader);}
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public static String takeScreenshot(WebDriver driver) {
        String Screenshotfilename= LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMMYY_HHmmss"));

        File screenshotfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        String relativePath = getProperty("ScreenshotRelativePath") + Screenshotfilename + ".png";
        String destination = System.getProperty("user.dir") + "/" + relativePath;

        try {
            Files.copy(screenshotfile.toPath(), new File(destination).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }
    public static void assertAndLogEquals(WebDriver driver,ExtentTest extentTest,Object actual, Object expected, String message) throws IOException {
        String screenshotPath = takeScreenshot(driver);

            try {
            Assert.assertEquals(actual, expected, message);
//            takeScreenshot(driver);
//            extentTest.log(Status.PASS, "Assertion passed: " + message);
                extentTest.pass("Test Passed: "+message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (AssertionError expception) {
//            takeScreenshot(driver);
//            extentTest.log(Status.FAIL, "Assertion failed: " + expception.getMessage());

            extentTest.fail("Test Failed: "+expception.getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            throw expception;
        }
    }

    public static String ReadExcelData(int Sheetno,int Rowno,int Cellno) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(getProperty("ExcelPath")); // convert the Excel into inputstream
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);  //
        XSSFSheet sheet = workbook.getSheetAt(Sheetno);
        Row row= sheet.getRow(Rowno);
        Cell cell=row.getCell(Cellno);
        String cellData=cell.toString();
        return cellData;
    }

    public static void LaunchUrl(WebDriver driver,String url)
    {
        driver.get(url);
    }
    public static void ClickOnTheElement(WebDriver driver,String locator)
    {
        driver.findElement(By.xpath(locator)).click();
    }
    public static void EnterValue(WebDriver driver, String locator,String text)
    {
        driver.findElement(By.xpath(locator)).sendKeys(text);
    }
    public static void EnterValue(WebDriver driver,By object,String text)
    {
        driver.findElement(object).sendKeys(text);
    }

    public static void waitExplicitlyForWebElementVisible(WebDriver driver, By object)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.visibilityOfElementLocated(object));
    }

    public static void waitImplicitly(WebDriver driver, int time) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
        }

    public static String getTextFromElement(WebDriver driver, By object)
    {
        return driver.findElement(object).getText();
    }

    public static void ClickOnElements(WebDriver driver,By object)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> elementslist=driver.findElements(object);

        for (WebElement element : elementslist) {

            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }
    }

    public static List<WebElement> ReturnList(WebDriver driver,By object)
    {
        List<WebElement> elementslist=driver.findElements(object);
        return elementslist;
    }


    public static void PressEnterKey(WebDriver driver,By object)
    {
        driver.findElement(object).sendKeys(Keys.ENTER);
    }

    public static void ScrollUsingJS(WebDriver driver, By object)
    {
        WebElement element = driver.findElement(object);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", element);
//        element.click();
    }

    public static void mouseHover(WebDriver driver, By object)  {
        WebElement element= driver.findElement(object);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public static void MouseHoverAndClick(WebDriver driver,int iteration, By hoverelem, By clickElem)
    {
        List<WebElement> hoverElements = driver.findElements(hoverelem);

        // Locate the element to click
        List<WebElement> clickElements = driver.findElements(clickElem);

        // Create Actions instance
        Actions actions = new Actions(driver);

        // Perform mouse hover and click
        for(int i=0;i<iteration;i++)
        {
            actions.moveToElement(hoverElements.get(i)).click(clickElements.get(i)).build().perform();

        }

    }

    public static boolean CheckPresenceOfElement(WebDriver driver,By object) {
        boolean flag=driver.findElement(object).isDisplayed();
        return flag;
    }
    public static String alertHandle(WebDriver driver) {

        Alert alert = driver.switchTo().alert();

        String alertText = alert.getText();

        alert.dismiss();

        return alertText;
    }

    public static void waitExplicitlyForElemTobeClickable(WebDriver driver,By object)  {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.elementToBeClickable(object));
        element.click();
    }

    public static boolean CheckElementIsVisible(WebDriver driver,By object)
    {
        return driver.findElement(object).isDisplayed();
    }


    public static void ClickOnAllTheCheckBoxes(WebDriver driver,By object)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js=(JavascriptExecutor) driver;

        List<WebElement> checkboxes=driver.findElements(object);

        for(WebElement checkbox: checkboxes) {
            try {

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", checkbox);

                wait.until(ExpectedConditions.elementToBeClickable(checkbox));

                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            } catch (Exception e) {
                System.out.println("Checkbox interaction failed" + e.getMessage());
            }
        }
    }

    public static void MouseHoverFilterAndClick(WebDriver driver, By hoverelem, By clickElem)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement hoverElement = wait.until(ExpectedConditions.elementToBeClickable(hoverelem));

        // Locate the element to click
        List<WebElement> clickElements = driver.findElements(clickElem);

        // Create Actions instance
        Actions actions = new Actions(driver);

//        actions.moveToElement(hoverElement).perform();

        // Perform mouse hover and click
        for(int clickElement=0;clickElement<clickElements.size();clickElement++)

        {
            WebElement element=clickElements.get(clickElement);

//            wait.until(ExpectedConditions.visibilityOf(element));
//            wait.until(ExpectedConditions.elementToBeClickable(element));
            actions.moveToElement(hoverElement).click(element).build().perform();
        }

    }

    public static List<WebElement> RetrieveCheckboxNames(WebDriver driver, By hoverElem, By chkboxElem) throws InterruptedException {
        WebElement element=driver.findElement(hoverElem);

        // Create Actions object
        Actions actions = new Actions(driver);

        // Click and hold the element
        actions.clickAndHold(element).perform();

        // Hold for 2 seconds
        Thread.sleep(3000);

        List<WebElement> checkboxnames=driver.findElements(chkboxElem);

        // Release the mouse button
        actions.release(element).perform();

        return checkboxnames;
    }

    public static String getWindoWId(WebDriver driver)
    {
        return driver.getWindowHandle();
    }

    public static void ScrollToTop(WebDriver driver) {
            JavascriptExecutor js=(JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0,0);");
    }
    public static void assertEquals(String actual, String expected, String message) {
         Assert.assertEquals(actual, expected ,message);

    }

}







