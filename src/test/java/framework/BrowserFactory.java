package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BrowserFactory {

    private static WebDriver driver;
    private static String browser;
    private static String os;
    private static Logger log = Logger.getLogger(BrowserFactory.class.getName());
    private static String winExtension = ".exe";
    private static String linuxExtension = "";
    private static String chromedriver = "chromedriver";
    private static String geckodriver = "geckodriver";

    private BrowserFactory() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    public static void quit(){
        driver.quit();
        driver=null;
    }

    private static WebDriver createDriver() {
        os = System.getProperty("os.name").toLowerCase();
        browser = System.getProperty("browser");
        if(System.getProperty("browser")==null)  browser = PropertyReader.getTestProperty("browser").toLowerCase();
        String extension = getExtension(os);
        try {
            String canonPath = CommonFunctions.getCanonicalPathToResource();
            switch (browser) {
                case "chrome":
                    driver = setChromeDriver(canonPath, extension);
                    Log.info("Current driver is chromedriver");
                    break;
                case "firefox":
                    driver = setFireFoxDriver(canonPath, extension);
                    Log.info("Current driver is geckodriver");
                    break;
            }
        } catch (Exception ex) {
            log.log(Level.SEVERE, "Error with driver!", ex);
        }
        return driver;
    }

    private static String getExtension(String os) {
        if (os.contains("windows")) return winExtension;
        else if (os.contains("linux")) return linuxExtension;
        else return null;
    }

    private static WebDriver setChromeDriver(String path, String extension) {
        System.setProperty("webdriver.chrome.driver",
                new StringBuilder().append(path).append(chromedriver).append(extension).toString());
        return driver = new ChromeDriver();
    }

    private static WebDriver setFireFoxDriver(String path, String extension) {
        System.setProperty("webdriver.gecko.driver",
                new StringBuilder().append(path).append(geckodriver).append(extension).toString());
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        return driver = new FirefoxDriver();
    }
}