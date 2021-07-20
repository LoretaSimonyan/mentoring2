package pages;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.PropertiesReader;

public class LoginEmailPage extends BasePage {


    private final String nextButtonLocator = "//div[@id='identifierNext']";

    @FindBy(xpath = "//input[@id='identifierId']")
    private WebElement emailFiled;

    @FindBy(xpath = nextButtonLocator)
    private WebElement nextButton;

    Logger logger = LogManager.getRootLogger();

    PropertiesReader userProperties = new PropertiesReader();


    public LoginPasswordPage enterEmail(User currentUser) {
        waitAndSendKeys(emailFiled, currentUser.getUserEmail());
        logger.info("User entered email");
        nextButton.click();
        logger.info("User navigated to Login password page");
        return new LoginPasswordPage();
    }
}
