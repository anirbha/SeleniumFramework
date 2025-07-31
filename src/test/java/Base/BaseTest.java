package Base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;




public class BaseTest {
        protected WebDriver driver;



        @BeforeClass
        public WebDriver setUp() {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            return driver;
        }
        @AfterClass
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }

}

