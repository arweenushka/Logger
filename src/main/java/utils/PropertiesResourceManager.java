package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class PropertiesResourceManager {

    //private static final String BROWSER = "browser";
    private static String currentBrowser;

    public static void initProperties() {
        Properties properties = new Properties();
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream
                    ("src/test/resources/selenium.properties");
            properties.load(fileInputStream);
        }catch (IOException e) {
            e.printStackTrace();
        }
        //updated to get properties from pom file and to specify browser using command line
        currentBrowser =  System.getProperty("browser");
        //currentBrowser = properties.getProperty(BROWSER);
    }

    public static String getCurrentBrowser() {
        return currentBrowser;
    }
}
