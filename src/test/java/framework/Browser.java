package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Browser {

    public void setMaximizeWindow(){
        BrowserFactory.getDriver().manage().window().maximize();
        Log.info("Maximize window");
    }

    public WebDriver getDriver(){
        return BrowserFactory.getDriver();
    }

    public void navigate(String url){
        BrowserFactory.getDriver().get(url);
    }

    public void waitForPageToLoad() {
        Log.info("Waiting  for page to load");
        Waiter.waitForLoad(BrowserFactory.getDriver());
    }

    public void quit() {
        Log.info("Quite browser");
        BrowserFactory.quit();
    }

    public void scrollIntoView(By locator){
        WebElement element = getDriver().findElement(locator);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
