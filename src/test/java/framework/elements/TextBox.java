package framework.elements;

import framework.Log;
import org.openqa.selenium.By;

public class TextBox extends BaseElement {

    public TextBox(final By locator) {
        super(locator);
    }
    public TextBox(final By locator, String name) {
        super(locator, name);
    }

    public void sendKey(String text){
        Log.info(String.format("Enter the %s '%s' in the field with locator %s", name, text, locator));
        waitForIsElementPresent();
        findElement().sendKeys(text);
    }
}
