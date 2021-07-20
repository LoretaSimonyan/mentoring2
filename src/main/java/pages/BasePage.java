package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;
import utils.PropertiesReader;
import utils.Waits;


public abstract class BasePage {
    static Waits waits;
    static WebDriver driver;
    PropertiesReader propertiesReader = new PropertiesReader();

    BasePage() {
        driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
        waits = new Waits();
    }

    void waitAndClick(WebElement webElement) {
        waits.waitElementToBeClickableByWebElement(webElement);
        webElement.click();
    }

    void waitAndClick(By by, WebDriver driver) {
        waits.waitElementToBeClickableByLocator(by);
        driver.findElement(by).click();
    }

    void waitAndSendKeys(WebElement webElement, String text) {
        waits.waitElementToBeClickableByWebElement(webElement);
        webElement.sendKeys(text);
    }

    String waitAndGetText(WebElement webElement) {
        waits.waitElementVisibility(webElement);
        return webElement.getText();
    }

    void sendKeys(WebElement webElement, String text) {
        waits.waitElementVisibility(webElement);
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
}
