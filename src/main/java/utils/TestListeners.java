package utils;


import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListeners implements ITestListener {
    private Logger logger = LogManager.getRootLogger();

    public void onTestStart(ITestResult iTestResult) {
       logger.info("Test Started");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        logger.info("Test Compiled Successfully");

    }

    public void onTestFailure(ITestResult iTestResult) {
       saveFailureScreenShot();
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }

    private void saveScreenshot(){
        File screenCapture = ((TakesScreenshot)DriverFactory
                .getDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(
                    "./target/screenshots/"
                            + Utils.getCurrentTimeAsString() +
                            ".png"));
        } catch (IOException e) {
            logger.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    @Attachment(value = "Screenshot after test failed", type = "image/png")
    private byte[] saveFailureScreenShot() {
      return ((TakesScreenshot)DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
