package Base;
import Utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BaseTest {
        protected WebDriver driver;
//        protected ExtentReports extentReports;
//        protected ExtentTest extentTest;

        private static ExtentReports extentReports=ExtentManager.getInstance();
        private static ThreadLocal<ExtentTest> extentTestThreadLocal=new ThreadLocal<>();


        public void initiatedriver() {
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--headless=new");
//            driver = new ChromeDriver(options);

//            ExtentTest extentTest=extentReports.createTest(scenario.getName());
//            extentTestThreadLocal.set(extentTest);

            driver = new ChromeDriver();
            driver.manage().window().maximize();
//            return driver;

        }

    public WebDriver getDriver() {
        return driver;
    }

//    @AfterStep
//        public void afterStep(Scenario scenario) throws IOException {
//            if(scenario.isFailed())
//            {
//                String screenshotpath= TestUtils.takeScreenshot(getDriver());
//                try {
//                    extentTestThreadLocal.get().fail("Step failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotpath).build());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }

//        @AfterMethod
//        public void endReport(ITestResult iTestResult)
//        {
//            if(iTestResult.getStatus()==iTestResult.FAILURE)
//            {
//                String
//                extentTest.log(Status.FAIL,"Test Case failed is "+iTestResult.getTestName());
//                extentTest.log(Status.FAIL,extentTest.addScreenCaptureFromPath())
//            }
//
//
//        }

        public void quitdriver() {
            if (driver != null) {
                driver.quit();
            }
        }

        public static void afterAll(){
            extentReports.flush();
        }

}

