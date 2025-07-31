package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extentReports;

    public static ExtentReports getInstance() {
        if (extentReports == null) {
            // Create a dynamic report file name with timestamp
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = System.getProperty("user.dir") + "/TestOutput/ExtentReport/" + timeStamp + ".html";

            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
            extentReports = new ExtentReports();
            extentReports.attachReporter(htmlReporter);

        }
        return extentReports;
    }


}
