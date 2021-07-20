package pages;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPasswordPage extends BasePage {

    private final String passwordLocator = "password";
    private final String nextButtonLocator = "passwordNext";
    Logger logger = LogManager.getRootLogger();

    @FindBy(id = nextButtonLocator)
    private WebElement nextButton;

    @FindBy(name = passwordLocator)
    private WebElement passwordFiled;

    public GmailMainPage enterPassword(User currentUser) {
        waitAndSendKeys(passwordFiled, currentUser.getUserPassword());
        logger.info("User entered password");
        nextButton.click();
        logger.info("User navigated to Gmail page");
        return new GmailMainPage();
    }
}
