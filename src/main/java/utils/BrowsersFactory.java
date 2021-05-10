package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

//here we return browser driver specified in property file
public abstract class BrowsersFactory {

    private static final long IMPLICITLY_WAIT = 10;
    private static WebDriver webDriver;

    public static WebDriver startBrowser(String urlBasePage) {
        PropertiesResourceManager.initProperties();
        switch (PropertiesResourceManager.getCurrentBrowser().toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
        }
        webDriver.get(urlBasePage);
        webDriver.manage().timeouts().implicitlyWait
                (IMPLICITLY_WAIT, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        return webDriver;
    }
}
