package framework.elements;

import framework.BaseEntity;
import framework.Log;
import framework.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class BaseElement extends BaseEntity{

    protected By locator;
    protected String name;

    public BaseElement(By elementLocator) {
        this.locator = elementLocator;
    }

    public BaseElement(By elementLocator, String name){
        this.locator = elementLocator;
        this.name = name;
    }

    public void waitForIsElementPresent(){
        Waiter.waitForElementDisplayed(browser.getDriver(), locator);
    }

    public void waitAndClick(){
        Log.info(String.format("Clicking '%s' with locator %s", name, locator));
        Waiter.waitForClickable(browser.getDriver(), locator);
        findElement().click();
    }

    public String getText(){
        Log.info(String.format("Get inner text of element %s", name));
        waitForIsElementPresent();
        return findElement().getText();
    }

    public WebElement findElement(){
        WebElement element = null;
        try {
            element = browser.getDriver().findElement(locator);
            return element;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } finally {
            return element;
        }
    }

    public boolean isDisplayed(){
            if(findElement()!=null) {
                return true;
            }else{
                return false;
        }
    }

    public void moveToElement(){
        Log.info(String.format("Move to element '%s'", name));
        waitForIsElementPresent();
        Actions builder = new Actions(browser.getDriver());
        Action moveMouse = builder
                .moveToElement(browser.getDriver().findElement(locator))
                .build();
        moveMouse.perform();
    }

    public String getAttribute(String attribute){
        waitForIsElementPresent();
        return findElement().getAttribute(attribute);
    }

    public void clickAndWait(){
        waitAndClick();
        browser.waitForPageToLoad();
    }

    public void jsClick() {
        WebDriver driver = browser.getDriver();
        WebElement element = driver.findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
    }

    public void waitInvisibility(){
        Waiter.waitInvisibilityOfElement(browser.getDriver(), locator);
    }
}
