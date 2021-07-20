package pages;

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

    public String getOpenedMailSubjectText() {
        return waitAndGetText(openedMailSubject);
    }

    public String getOpenedMailBodyText() {
        waits.waitElementVisibility(By.xpath(openedMailBodyLocator));
        return openedMailBody.getText();
    }

    public String getOpenedMailSenderText() {
        return waitAndGetText(openedMailSender);
    }
}
