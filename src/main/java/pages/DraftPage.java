package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DraftPage extends BasePage {

    private final String allMailsDraftsLocator = "//span[text() = 'Draft']//ancestor::tr[@role = 'row']";
    @FindBy(xpath = allMailsDraftsLocator)
    private List<WebElement> allMailsInDrafts;
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;

    private final String allMailsSubjectLocator = "//span[@class = 'bog']/span";
    @FindBy(xpath = allMailsSubjectLocator)
    private List<WebElement> allMailsSubject;


    public void openLastMailFromDrafts() {
        waits.waitElementToBeClickableByLocator(By.xpath(allMailsDraftsLocator));
        allMailsInDrafts.get(0).click();
    }

    public String getDraftPageTitle() {
        return javascriptExecutor.executeScript("return document.title;").toString();
    }
}
