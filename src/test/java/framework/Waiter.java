package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.util.NoSuchElementException;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Waiter {

    public static void implicitWait(WebDriver driver) {
        int time = Integer.parseInt(PropertyReader.getTestProperty("timeoutForImplicity"));
        driver.manage().timeouts().implicitlyWait(time, SECONDS);
    }

    public static void waitUntilClickable(WebDriver driver, By locator) {
        int timeout = Integer.parseInt(PropertyReader.getTestProperty("timeoutForFindElement"));
        int interval = Integer.parseInt(PropertyReader.getTestProperty("interval"));
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(timeout, SECONDS)
                .pollingEvery(interval, SECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForClickable(WebDriver driver, By locator) {
        int timeout = Integer.parseInt(PropertyReader.getTestProperty("timeoutForFindElement"));
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(driver.findElement(locator))));
    }

    public static void waitInvisibilityOfElement(WebDriver driver, By locator) {
        int time = Integer.parseInt(PropertyReader.getTestProperty("timeoutForWaitInvisibility"));
        new WebDriverWait(driver, time)
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitForElementDisplayed(WebDriver driver, By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(isDisplayed(locator));
    }


    private static ExpectedCondition<WebElement> isDisplayed(final By locator) {
        return new ExpectedCondition<WebElement>() {

            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(locator);
                if (element.isDisplayed()) {
                    return element;
                }
                return element;
            }
        };
    }

    public static void waitForLoad(WebDriver driver) {
        int time = Integer.parseInt(PropertyReader.getTestProperty("timeoutForFindElement"));
        new WebDriverWait(driver, time).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}
