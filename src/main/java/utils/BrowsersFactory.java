package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

//here we return browser driver specified in property file
public abstract class BrowsersFactory {

    private static final Duration IMPLICITLY_WAIT = Duration.ofSeconds(10);
    private static WebDriver webDriver;

    public static WebDriver startBrowser(String urlBasePage) {
        PropertiesResourceManager.initProperties();
        switch (PropertiesResourceManager.getCurrentBrowser().toLowerCase()) {
            case "chrome":
                webDriver = new ChromeDriver();
                break;
            case "edge":
                webDriver = new EdgeDriver();
                break;
            case "firefox":
                webDriver = new FirefoxDriver();
                break;
            case "safari":
                webDriver = new SafariDriver();
                break;
        }
        webDriver.get(urlBasePage);
        webDriver.manage().timeouts().implicitlyWait
                (IMPLICITLY_WAIT);
        webDriver.manage().window().maximize();
        return webDriver;
    }
}
