package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpenedMailPage extends BasePage {

    @FindBy(xpath = "//div[text() = 'Inbox']/ancestor::div[@class = 'ha']/h2")
    private WebElement openedMailSubject;

    private final String openedMailBodyLocator = "//div/div[@dir = 'ltr']";
    @FindBy(xpath = openedMailBodyLocator)
    private WebElement openedMailBody;

    @FindBy(xpath = "//span[@email]/span")
    private WebElement openedMailSender;

    @Step()
    public String getOpenedMailSubjectText() {
        return waitAndGetText(openedMailSubject);
    }

    @Step()
    public String getOpenedMailBodyText() {
        waits.waitElementVisibility(By.xpath(openedMailBodyLocator));
        return openedMailBody.getText();
    }

    @Step()
    public String getOpenedMailSenderText() {
        return waitAndGetText(openedMailSender);
    }
}
