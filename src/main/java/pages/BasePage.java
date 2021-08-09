package pages;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import utils.DriverFactory;
import utils.PropertiesReader;
import utils.Waits;

import java.util.NoSuchElementException;


public abstract class BasePage extends AbstractWebDriverEventListener {
    static Waits waits;
    static WebDriver driver;
    JavascriptExecutor js ;
    Logger logger = LogManager.getRootLogger();

    BasePage() {
        driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
        waits = new Waits();
    }

    void waitAndClick(WebElement webElement) {
        waits.waitElementToBeClickableByWebElement(webElement);
        highLightElement(webElement);
        webElement.click();
//        try {
//                unHighLightElement(webElement);
//        }
//        catch (NoSuchElementException exception){
//           System.out.println("Eelement isn't found");
//        }
    }

    void waitAndClick(By by, WebDriver driver) {
        waits.waitElementToBeClickableByLocator(by);
        driver.findElement(by).click();
    }

    void waitAndSendKeys(WebElement webElement, String text) {
        waits.waitElementToBeClickableByWebElement(webElement);
        highLightElement(webElement);
        webElement.sendKeys(text);
    }

    String waitAndGetText(WebElement webElement) {
        waits.waitElementVisibility(webElement);

        return webElement.getText();
    }

    @SneakyThrows
    void sendKeys(WebElement webElement, String text) {
        waits.waitElementVisibility(webElement);
        highLightElement(webElement);
        webElement.click();
        webElement.clear();
        webElement.sendKeys(text);

    }

    WebElement findElement(By by) {
        waits.waitElementVisibility(by);
        return driver.findElement(by);
    }

    void switchToFrame(WebElement element) {
        waits.waitElementVisibility(element);
        driver.switchTo().frame(element);
    }

    private String elementOriginalStyle(WebElement webElement){
        return webElement.getAttribute("style");
    }
    void highLightElement(WebElement webElement){
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", webElement, "style", elementOriginalStyle(webElement) + "background: pink; border: 5px solid red;");
    }

    void unHighLightElement(WebElement webElement){
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", webElement, "style", elementOriginalStyle(webElement));
    }

//    public void highLighterMethod( WebElement element){
//        js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].setAttribute('style', 'background: green; border: 3px solid blue;');", element);
//    }
}
