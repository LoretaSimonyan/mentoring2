package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import org.openqa.selenium.WebDriver;
import step_defenitions.GmailSteps;
import utils.DriverFactory;


public class DriverHooks {
    WebDriver driver;

   @Before
    public void setUp(){
       driver = DriverFactory.getDriver();
       GmailSteps.driver = driver;
    }


    @After
    public void  quit(){
        DriverFactory.quit();
    }
}
