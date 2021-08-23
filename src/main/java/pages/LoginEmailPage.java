package pages;

import io.qameta.allure.Step;
import model.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginEmailPage extends BasePage {

    private final String nextButtonLocator = "//div[@id='identifierNext']";

    @FindBy(xpath = "//input[@id='identifierId']")
    private WebElement emailFiled;

    @FindBy(xpath = nextButtonLocator)
    private WebElement nextButton;


    @Step()
    public LoginPasswordPage enterEmail(User currentUser) {
        waitAndSendKeys(emailFiled, currentUser.getUserEmail());
        logger.info("User entered email");
        waitAndClick(nextButton);
        logger.info("User navigated to Login password page");
        return new LoginPasswordPage();
    }
}
