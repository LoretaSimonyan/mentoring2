package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InboxPage extends BasePage {
    private final String allMailsInInboxLocator = "//div[@role = 'main' ]//tr[@role = 'row']";
    @FindBy(xpath = allMailsInInboxLocator)
    private List<WebElement> allMailsInInbox;

    @FindBy(xpath = "//tr[@class = 'zA zE' and @role = 'row']//div[@class = 'afn']")
    private WebElement mailTextLocator;

    public int quantityOfMailsInInbox() {
        waits.waitElementVisibility(By.xpath(allMailsInInboxLocator));
        return allMailsInInbox.size();
    }

    public OpenedMailPage openLastMailFromInbox() {
        waits.waitElementToBeClickableByLocator(By.xpath(allMailsInInboxLocator));
        allMailsInInbox.get(0).click();
        return new OpenedMailPage();
    }
}
