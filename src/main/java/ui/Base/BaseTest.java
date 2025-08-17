package ui.Base;
import ui.Utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;


public class BaseTest {
        protected WebDriver driver;
        private static ExtentReports extentReports=ExtentManager.getInstance();
        private static ThreadLocal<ExtentTest> extentTestThreadLocal=new ThreadLocal<>();


        public void initiatedriver() {
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--headless=new");
//            driver = new ChromeDriver(options);





        }

    public WebDriver getDriver() {
        return driver;
    }



        public void quitdriver() {
            if (driver != null) {
                driver.quit();
            }
        }

        public static void afterAll(){
            extentReports.flush();
        }

}

