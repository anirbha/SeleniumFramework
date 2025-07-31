package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TestUtils {

    private static Properties properties = new Properties();
    protected static ExtentReports extent;
    protected static ExtentTest test;

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
    public static void LaunchUrl(WebDriver driver,String url)
    {
        driver.get(url);
    }
    public static void ClickOnTheElement(WebDriver driver,String locator)
    {
        driver.findElement(By.xpath(locator)).click();
    }



}



