package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utils.PropertiesReader;

import java.util.NoSuchElementException;

public class MailCreatingPage extends BasePage {
    PropertiesReader userProperties;

    private final String sendToLocator = "to";
    @FindBy(name = sendToLocator)
    private WebElement sendToFiled;

    private final String subjectFiledLocator = "subjectbox";
    @FindBy(name = subjectFiledLocator)
    private WebElement subjectFiled;

    private final String sendButtonLocator = "//div[text()='Send']";
    @FindBy(xpath = sendButtonLocator)
    private WebElement sendButton;

    private final String mailBodyLocator = "//div[@aria-label = 'Message Body']";
    @FindBy(xpath = mailBodyLocator)
    private WebElement mailBodyFiled;

    @FindBy(xpath = "//img[@data-tooltip='Save & close']")
    private WebElement saveAndCloseButton;

    private final String sendToTextLocator = "//div[@class = 'oL aDm az9']";
    @FindBy(xpath = sendToTextLocator)
    private WebElement sendToText;

    private final String mailSubjectTextLocator = "//div[@class = 'aYF']//span";
    @FindBy(xpath = mailSubjectTextLocator)
    private WebElement mailSubjectText;

    @FindBy(xpath = "//div[@class = 'oh J-Z-I J-J5-Ji T-I-ax7 oi'] ")
    private WebElement deleteButtonText;

    @FindBy(xpath = "//div[@class = 'oh J-Z-I J-J5-Ji T-I-ax7']")
    private WebElement deleteButton;
    Actions action;

    public void clickOnSetSaveAndCloseButton() {
        saveAndCloseButton.click();
    }

    public void createNewMail(String subjectText, String bodyText) {
        waits.waitElementToBeClickableByLocator(By.xpath(sendButtonLocator));
        userProperties = new PropertiesReader();
        sendToFiled.sendKeys(getOtherUserEmail());
        subjectFiled.sendKeys(subjectText);
        mailBodyFiled.sendKeys(bodyText);
    }

    public void sendEmailToYourself(String subjectText, String bodyText) {
        waits.waitElementToBeClickableByLocator(By.xpath(sendButtonLocator));
        userProperties = new PropertiesReader();
        sendToFiled.sendKeys(userProperties.getUserEmail());
        subjectFiled.sendKeys(subjectText);
        mailBodyFiled.sendKeys(bodyText);
    }

    public String getTextFromMailBody() {
        return waitAndGetText(mailBodyFiled);
    }

    public String getTextFromSendToFiled() {
        return waitAndGetText(sendToText);
    }

    public String getTextFromSubjectFiled() {
        waits.waitElementVisibility(By.xpath(mailSubjectTextLocator));
        return mailSubjectText.getText();
    }

    public String getOtherUserEmail() {
        userProperties = new PropertiesReader();
        return userProperties.getOtherUserEmail();
    }

    public void sendMail() {
        waits.waitElementToBeClickableByLocator(By.xpath(sendButtonLocator));
        sendButton.click();
    }

    public boolean isDeleteButtonTextDisplayed() {
        try {
            waits.waitElementVisibility(deleteButtonText);
            deleteButtonText.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public String getDeleteButtonText() {
        return waitAndGetText(deleteButtonText);
    }

    public void moveToDeleteButton() {
        action = new Actions(driver);
        waits.waitElementVisibility(deleteButton);
        action.moveToElement(deleteButton).perform();
    }
}
