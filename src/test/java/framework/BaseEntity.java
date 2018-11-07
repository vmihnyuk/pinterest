package framework;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public abstract class BaseEntity {

    protected Browser browser = new Browser();
    protected SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void before(){
        Log.precondStart(getClass().getSimpleName());
        browser.setMaximizeWindow();
        Waiter.implicitWait(browser.getDriver());
        browser.navigate(PropertyReader.getTestProperty("url"));
        Log.precondComplete();
    }

    @AfterMethod
    public void after(){
        browser.quit();
    }
}