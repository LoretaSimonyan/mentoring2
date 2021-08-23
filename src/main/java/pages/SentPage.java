package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SentPage extends BasePage {

    private final String allSentMailsLocator = "//div[text() = 'To: ']//ancestor::tr[@role = 'row']";
    @FindBy(xpath = allSentMailsLocator)
    private List<WebElement> allSentMails;

    @FindBy(xpath = "//td[@class = 'TC']")
    private WebElement noMailMessage;

    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;

    @Step()
    public int getSentMailsCount() {
        try {
            waits.waitElementVisibility(By.xpath(allSentMailsLocator));
        } catch (TimeoutException exception) {
            return 0;
        }
        return allSentMails.size();
    }

    @Step()
    public String getNoMailMassageText() {
        return waitAndGetText(noMailMessage);
    }

    @Step()
    public String getSentMailPageTitle() {
        return javascriptExecutor.executeScript("return document.title;").toString();
    }
}
