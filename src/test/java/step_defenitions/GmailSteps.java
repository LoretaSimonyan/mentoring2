package step_defenitions;

import dataprovider.UserCreator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.GmailMainPage;
import pages.HangoutsPage;
import pages.LoginEmailPage;
import pages.LoginPasswordPage;
import utils.DriverFactory;
import utils.PropertiesReader;
import utils.RandomData;

public class GmailSteps {
    public  static WebDriver driver ;
    SoftAssert softAssert;
    GmailMainPage gmailMainPage;
    PropertiesReader propertiesReader = new PropertiesReader();
    private final String mailSubjectText = RandomData.randomStringGenerator(10);
    private final String mailBodyText = RandomData.randomStringGenerator(20);
    User currentUser;
    int sentMailsBeforeSendinNewMail;


    @Given("The user opens gmail Accounts Page")
    public void gmailAccountsPageIsOpen(){
        driver.get(propertiesReader.getBaseURL());
    }

    @When("User login gmail account")
    public  void login(){
        currentUser = UserCreator.MAIN_USER();
        LoginEmailPage loginEmailPage = new LoginEmailPage();
        LoginPasswordPage loginPasswordPage = loginEmailPage.enterEmail(currentUser);
        gmailMainPage = loginPasswordPage.enterPassword(currentUser);
        Assert.assertTrue(gmailMainPage.isInGmailPage(), "It's not Gmail main page");
    }

    @Then("User share {string} status")
    public void shareStatus(String statusText){
        HangoutsPage hangoutsPage = gmailMainPage.switchToHangoutsFrame();
        hangoutsPage.shareYourStatus(statusText);
        Assert.assertEquals(gmailMainPage.getSharedStatus(),statusText, "Status isn't set");
    }

    @Then("User signed out from hangouts")
    public void signOutFromHangoutsTest(){
        HangoutsPage hangoutsPage = gmailMainPage.switchToHangoutsFrame();
        hangoutsPage.signOutOfHangouts();
        Assert.assertTrue(gmailMainPage.checkAvailabilityOfSignInToHangouts());
        gmailMainPage.signInHangouts();
    }
}
