package tests;

import dataprovider.UserCreator;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import model.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.DriverFactory;
import utils.PropertiesReader;
import utils.RandomData;
import utils.TestListeners;

@Listeners({TestListeners.class})
public class GmailTest {
    WebDriver driver ;
    SoftAssert softAssert;
    GmailMainPage gmailMainPage;
    PropertiesReader propertiesReader = new PropertiesReader();
    private final String mailSubjectText = RandomData.randomStringGenerator(10);
    private final String mailBodyText = RandomData.randomStringGenerator(20);
    private final String statusText = RandomData.randomStringGenerator(10);
    User currentUser;
    int sentMailsBeforeSendinNewMail;

    @BeforeMethod()
    @Severity(SeverityLevel.BLOCKER)
    @Description("verifying gmail mail page title after login ")
    public void login() {
        driver = DriverFactory.getDriver();
        driver.get(propertiesReader.getBaseURL());
        currentUser = UserCreator.MAIN_USER();
        LoginEmailPage loginEmailPage = new LoginEmailPage();
        LoginPasswordPage loginPasswordPage = loginEmailPage.enterEmail(currentUser);
        gmailMainPage = loginPasswordPage.enterPassword(currentUser);
        Assert.assertTrue(gmailMainPage.isInGmailPage(), "It's not Gmail main page");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1, description = "verifying that email sent")
    @Description("verifying that email sent")
    public void sentEmailTest() {
        SentPage sentPage = gmailMainPage.openSentMails();
        sentMailsBeforeSendinNewMail = sentPage.getSentMailsCount();
        DraftPage draftPage = gmailMainPage.openDraftsPage();
        int draftsQuantityBeforeCreatingNewMail = gmailMainPage.getDraftsQuantity();
        MailCreatingPage mailCreatingPage = gmailMainPage.clickOnComposeButton();
        mailCreatingPage.createNewMail(mailSubjectText, mailBodyText);
        mailCreatingPage.clickOnSetSaveAndCloseButton();
        int draftsQuantityAfterCreatingNewMail = gmailMainPage.getDraftsQuantity();
        Assert.assertEquals(draftsQuantityAfterCreatingNewMail - 1, draftsQuantityBeforeCreatingNewMail, "The message isn't saved in drafts");

        Assert.assertEquals(draftPage.getDraftPageTitle(),String.format("Drafts (%d) - %s - Gmail",draftsQuantityAfterCreatingNewMail,propertiesReader.getUserEmail(), "Drafts page title is nor right"));
        draftPage.openLastMailFromDrafts();
        softAssert = new SoftAssert();
        softAssert.assertEquals(mailBodyText, mailCreatingPage.getTextFromMailBody(), "Actual body are different from Expected");
        softAssert.assertEquals(mailSubjectText, mailCreatingPage.getTextFromSubjectFiled(), "Subjects are different");
        softAssert.assertEquals(propertiesReader.getOtherUserEmail(), mailCreatingPage.getTextFromSendToFiled(), "Users whom mails was sent are different");
        softAssert.assertAll();

        mailCreatingPage.sendMail();
        int draftsQuantityAfterSendingMail = gmailMainPage.getDraftsQuantity();
        Assert.assertEquals(draftsQuantityAfterSendingMail, draftsQuantityAfterCreatingNewMail - 1, "After sending mail, mail isn't disappeared from drafts");
        gmailMainPage.openSentMails();
        Assert.assertEquals(sentPage.getSentMailPageTitle(),String.format("Sent Mail - %s - Gmail",propertiesReader.getUserEmail()));
        int sentMailsAfterSendingNewMail = sentPage.getSentMailsCount();
        Assert.assertEquals(sentMailsBeforeSendinNewMail + 1, sentMailsAfterSendingNewMail, "Sent mail isn't in Sent folder");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "verifying that email appeared after sending it to yourself")
    @Description("verifying that email appeared after sending it to yourself")
    public void receiveAndCheckEmail() {

        int mailsCountBeforeSendingMail = gmailMainPage
                .openSentMails()
                .getSentMailsCount();
        int inboxQuantityBeforeSendingMail = gmailMainPage
                .openInboxPage()
                .quantityOfMailsInInbox();
        gmailMainPage
                .clickOnComposeButton()
                .sendEmailToYourself(mailSubjectText, mailBodyText)
                .sendMail();
        gmailMainPage.openSentMails();
        Assert.assertEquals(gmailMainPage.openSentMails().getSentMailsCount(),mailsCountBeforeSendingMail+1, "Quantity isn't changed after sending mail");
        gmailMainPage.openInboxPage();
        Assert.assertEquals(gmailMainPage.openInboxPage().quantityOfMailsInInbox(),inboxQuantityBeforeSendingMail+1,"Quantity in Inbox isn't increased");

        OpenedMailPage openedMailPage = gmailMainPage.openInboxPage().openLastMailFromInbox();
        softAssert = new SoftAssert();
        softAssert.assertEquals(mailBodyText, openedMailPage.getOpenedMailBodyText());
        softAssert.assertEquals(mailSubjectText,openedMailPage.getOpenedMailSubjectText());
        softAssert.assertEquals(currentUser.getFirstName()  + " " + currentUser.getLastName(),openedMailPage.getOpenedMailSenderText());
        softAssert.assertAll();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2, description = "verifying that all sent emails are deleted")
    @Description("verifying that all sent emails are deleted")
    public void deleteAllSentEmails(){
        SentPage sentPage = gmailMainPage.openSentMails();
        Assert.assertNotEquals(sentPage.getSentMailsCount(),0, "Sent page is empty");
        gmailMainPage.selectAllMails();
        Assert.assertTrue(gmailMainPage.isAllMailsAreSelected(),"All mails aren't selected after clicking 'select all' checkbox");
        gmailMainPage.clickOnDeleteButton();
        softAssert = new SoftAssert();
        softAssert.assertEquals(sentPage.getNoMailMassageText(),"No sent messages! Send one now!","Massage for empty sent page isn't correct");
        softAssert.assertAll();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "verifying status after sharing it")
    @Description("verifying status after sharing it")
    public void shareStatusTest(){
        HangoutsPage hangoutsPage = gmailMainPage.switchToHangoutsFrame();
        hangoutsPage.shareYourStatus(statusText);
        Assert.assertEquals(gmailMainPage.getSharedStatus(),statusText, "Status isn't set");
    }

    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("verifying that user can sign out from hangouts")
    public void signOutFromHangoutsTest(){
        HangoutsPage hangoutsPage = gmailMainPage.switchToHangoutsFrame();
        hangoutsPage.signOutOfHangouts();
        Assert.assertTrue(gmailMainPage.checkAvailabilityOfSignInToHangouts());
        gmailMainPage.signInHangouts();
    }

    @Test(description = "versifying that text appeared after hovering to delete button")
    @Severity(SeverityLevel.MINOR)
    @Description("versifying that text appeared after hovering to delete button")
    public void deleteButtonTextInCreatingMailPageTest(){
        MailCreatingPage mailCreatingPage = gmailMainPage.clickOnComposeButton();
        mailCreatingPage.moveToDeleteButton();
        softAssert = new SoftAssert();
        softAssert.assertTrue(mailCreatingPage.isDeleteButtonTextDisplayed(),"The text isn't displayed");
        softAssert.assertEquals(mailCreatingPage.getDeleteButtonText(),"Discard draft(Ctrl-Shift-D)");
        softAssert.assertAll();
    }

    @AfterMethod
    public void signOut() {
        gmailMainPage.signOut();
        DriverFactory.quit();
    }
}
