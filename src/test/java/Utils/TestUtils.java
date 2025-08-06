package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;
    public static Logger logger = LogManager.getLogger();
//    public static final Logger logger = (Logger) LoggerFactory.getLogger(TestUtils.class);
    String logFilePath = System.getProperty("user.dir") + "/resources/log4j2.properties" ;


        static {
            try (FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "/TestData/Config.properties")) {
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
            String filePath = System.getProperty("user.dir") + "/TestData/Locators.json" ;
            FileReader reader = new FileReader(filePath);
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(reader);}
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public static String takeScreenshot(WebDriver driver) throws IOException {
        String Screenshotfilename= LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMMYY_HHmmss"));

        File screenshotfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        String relativePath = "TestOutput/Screenshots/" + Screenshotfilename + ".png";
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
        FileInputStream fileInputStream = new FileInputStream("TestData/UserDetails.xlsx"); // convert the Excel into inputstream
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
    public static void waitExplicitlyForWebElement(WebDriver driver,String locator)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));

    }
    public static void waitImplicitly(WebDriver driver, int time) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
        }
    public static String getTextFromElement(WebDriver driver, String locator)
    {
        return driver.findElement(By.xpath(locator)).getText();
    }
    public static void ClickOnElements(WebDriver driver,String locator)
    {
        List<WebElement> elementslist=driver.findElements(By.xpath(locator));

        for (WebElement element : elementslist) {
            element.click();
        }
    }

    public static int SizeOfList(WebDriver driver,String locator)
    {
        int size=driver.findElements(By.xpath(locator)).size();
        return size;
    }

    public static List<WebElement> ReturnList(WebDriver driver,String locator)
    {
        List<WebElement> elementslist=driver.findElements(By.xpath(locator));
        return elementslist;
    }

    public static void PressEnterKey(WebDriver driver,String locator)
    {
        driver.findElement(By.xpath(locator)).sendKeys(Keys.ENTER);
    }
    public static void ScrollUsingJS(WebDriver driver, String locator)
    {
        WebElement element = driver.findElement(By.xpath(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", element);
//        element.click();
    }
    public static void mouseHover(WebDriver driver, String locator) {
         WebElement element= driver.findElement(By.xpath(locator));
            Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
//
    public static void MouseHoverAndClick(WebDriver driver,int iteration, String hoverelem, String clickElem)
    {
        List<WebElement> hoverElements = driver.findElements(By.xpath(hoverelem));

        // Locate the element to click
         List<WebElement> clickElements = driver.findElements(By.xpath(clickElem));

        // Create Actions instance
         Actions actions = new Actions(driver);

        // Perform mouse hover and click
        for(int i=0;i<iteration;i++)
        {
            actions.moveToElement(hoverElements.get(i)).click(clickElements.get(i)).build().perform();
        }

    }

    public static boolean CheckPresenceOfElement(WebDriver driver,String locator) {
            boolean flag=driver.findElement(By.xpath(locator)).isDisplayed();
            return flag;
    }
    public static String alertHandle(WebDriver driver) {

        Alert alert = driver.switchTo().alert();

        String alertText = alert.getText();

        alert.dismiss();

        return alertText;
    }
    public static void waitExplicitlyForElemTobeClickable(WebDriver driver,String locator)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));

    }
}







