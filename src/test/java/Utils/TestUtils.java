package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.collect.Table;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestUtils {

    private static Properties properties = new Properties();
    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;

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
        String destination = System.getProperty("user.dir")+"/TestOutput/Screenshots/"+Screenshotfilename+ ".png";

//        FileUtils.copyFile(screenshotfile,new File(dest +Screenshotfilename+ ".png"));

        try {
            Files.copy(screenshotfile.toPath(), new File(destination).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }
    public static void assertAndLogEquals(WebDriver driver,Object actual, Object expected, String message) throws IOException {
        String screenshotPath = takeScreenshot(driver);

            try {
            Assert.assertEquals(actual, expected, message);
//            takeScreenshot(driver);
//            extentTest.log(Status.PASS, "Assertion passed: " + message);
                extentTest.pass("Test Passed: ", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (AssertionError expception) {
//            takeScreenshot(driver);
//            extentTest.log(Status.FAIL, "Assertion failed: " + expception.getMessage());

            extentTest.fail("Test Failed: "+expception.getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            throw expception;
        }
    }

    public static String ReadExcelData(int Sheetno,int Rowno,int Cellno) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("Test Data/UserDetails.xlsx"); // convert the Excel into inputstream
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);  //
        XSSFSheet sheet = workbook.getSheetAt(Sheetno);
        Row row= sheet.getRow(Rowno);
        Table.Cell cell= (Table.Cell) row.getCell(Cellno);
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



}



