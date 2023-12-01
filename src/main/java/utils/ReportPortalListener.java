package utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.io.File;

public class ReportPortalListener extends ReportPortalTestNGListener {

    @Override
    public void onTestFailure(ITestResult testResult) {
        if (!testResult.isSuccess()) {
            if (WebDriverRunner.getWebDriver() instanceof TakesScreenshot) {
                File screenshot = Selenide.screenshot(OutputType.FILE);
                LoggingUtils.log(screenshot, "Screenshot of failure");
            }
        }
        super.onTestFailure(testResult);
    }


}
