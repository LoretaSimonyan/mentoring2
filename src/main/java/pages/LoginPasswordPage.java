package pages;

import io.qameta.allure.Step;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPasswordPage extends BasePage {

    private final String passwordLocator = "password";
    private final String nextButtonLocator = "passwordNext";
    Logger logger = LogManager.getLogger(LoginPasswordPage.class);

    @FindBy(id = nextButtonLocator)
    private WebElement nextButton;

    @FindBy(name = passwordLocator)
    private WebElement passwordFiled;

    @Step()
    public GmailMainPage enterPassword(User currentUser) {
        waitAndSendKeys(passwordFiled, currentUser.getUserPassword());
        logger.info("User entered password");
        waitAndClick(nextButton);
        logger.info("User navigated to Gmail page");
        return new GmailMainPage();
    }
}
