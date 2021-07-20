package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {
    private static WebDriver driver;


    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (System.getProperty("browser","chrome")) {
                case "firefox":{
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                case "MicrosoftEdge":{
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                }
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quit() {
        driver.quit();
        driver = null;
    }
}
