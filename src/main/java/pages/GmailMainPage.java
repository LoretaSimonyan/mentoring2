package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GmailMainPage extends BasePage {

    private final String gmailPageIdentifierLocator = "//a[@title = 'Gmail' and @class = 'gb_ke gb_pc gb_ie']";
    @FindBy(xpath = gmailPageIdentifierLocator)
    private WebElement gmailPageIdentifier;

    @FindBy(xpath = "//div[text() = 'Compose']")
    private WebElement composeButton;

    private final String sentButtonLocator = "//div[@data-tooltip = 'Sent']";
    @FindBy(xpath = sentButtonLocator)
    private WebElement sentButton;

    private final String draftsButtonLocator = "//div[@data-tooltip = 'Drafts']";
    @FindBy(xpath = draftsButtonLocator)
    private WebElement draftsButton;

    private final String inboxButtonLocator = "//a[text() = 'Inbox']";
    @FindBy(xpath = "//a[text() = 'Inbox']")
    private WebElement inboxButton;

    private final String draftsQuantityLocator = "//a[contains(text(),'Drafts')]/parent::span/following-sibling::div";
    @FindBy(xpath = draftsQuantityLocator)
    private WebElement draftsQuantity;

    @FindBy(xpath = "//a[@class = 'gb_C gb_Ma gb_h']")
    private WebElement userButton;

    private final String signOutButtonLocator = "//a[text() = 'Sign out']";
    @FindBy(xpath = signOutButtonLocator)
    private WebElement signOutButton;

    @FindBy(xpath = "//a[text() ='Inbox']/parent::span/following-sibling::div")
    private WebElement inboxQuantity;

    private final String locatorForAllMailsCheckBox = "//div[text() = 'To: ']//ancestor::tr[@role = 'row']//div[@role = 'checkbox']";
    @FindBy(xpath = locatorForAllMailsCheckBox)
    private List<WebElement> allMailsCheckBox;

    @FindBy(xpath = "//div[@gh = 'tm']//span[@role = 'checkbox']")
    private WebElement mainCheckbox;

    @FindBy(xpath = "//div[@gh = 'tm']//div[@data-tooltip = 'Delete' ]")
    private WebElement deleteButton;

    @FindBy(xpath = "//div[@aria-label = 'Main menu']")
    private WebElement mainMenuButton;

    @FindBy(xpath = "//div[@class = 'qR DGj28e']")
    private WebElement hangoutsUserLocator;

    @FindBy(xpath = "//iframe[@class = 'a1j']")
    private WebElement hangoutsIframe;

    @FindBy(xpath = "//img[@class = 'xU zc-Sf']")
    private WebElement hangoutsImage;

    @FindBy(xpath = "//div[@class = 'pBtHLd MfmRS']")
    private WebElement sharedStatus;

    @FindBy(xpath = "//div[text() = 'Sign in']")
    private WebElement signInToHangoutsButton;

    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;


    public int getDraftsQuantity() {
        waits.waitElementToBeClickableByLocator(By.xpath(draftsQuantityLocator));
        return Integer.parseInt(draftsQuantity.getText());
    }

    public MailCreatingPage clickOnComposeButton() {
        composeButton.click();
        return new MailCreatingPage();
    }

    public DraftPage openDraftsPage() {
        waitAndClick(draftsButton);
        return new DraftPage();
    }

    public boolean isInGmailPage() {
        waits.waitElementVisibility(By.xpath(gmailPageIdentifierLocator));
        return gmailPageIdentifier.isDisplayed();
    }

    public SentPage openSentMails() {
       waits.waitElementVisibility(By.xpath(sentButtonLocator));
       sentButton.click();
        return new SentPage();
    }

    public void signOut() {
        waitAndClick(userButton);
        waitAndClick(signOutButton);
    }

    public InboxPage openInboxPage() {
        javascriptExecutor.executeScript("arguments[0].click();", inboxButton);
        return new InboxPage();
    }

    public void selectAllMails() {

        mainCheckbox.click();
    }

    public boolean isAllMailsAreSelected() {
        waits.waitElementVisibility(By.xpath(locatorForAllMailsCheckBox));
        for (int i = 0; i < allMailsCheckBox.size(); ++i) {
            waits.waitElementToBeClickableByWebElement(allMailsCheckBox.get(i));
            if (!Boolean.parseBoolean(allMailsCheckBox.get(i).getAttribute("aria-checked"))) {
                return false;
            }
        }
        return true;
    }

    public void clickOnDeleteButton() {
        waitAndClick(deleteButton);
    }

    public void clickOnMainMenu() {
        mainMenuButton.click();
    }

    public HangoutsPage switchToHangoutsFrame() {
        switchToFrame(hangoutsIframe);
        waitAndClick(hangoutsImage);
        driver.switchTo().defaultContent();
        return new HangoutsPage();
    }


    public String getSharedStatus() {
        switchToFrame(hangoutsIframe);
        String hangoutsStatus = waitAndGetText(sharedStatus);
        driver.switchTo().defaultContent();
        return hangoutsStatus;
    }

    public boolean checkAvailabilityOfSignInToHangouts() {
        switchToFrame(hangoutsIframe);
        waits.waitElementVisibility(signInToHangoutsButton);
        boolean isDisplayed = signInToHangoutsButton.isDisplayed();
        driver.switchTo().defaultContent();
        return isDisplayed;
    }

    public void signInHangouts() {
        switchToFrame(hangoutsIframe);
        signInToHangoutsButton.click();
        driver.switchTo().defaultContent();
    }

}
