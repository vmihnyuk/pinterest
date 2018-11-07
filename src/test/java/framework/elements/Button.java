package framework.elements;

import org.openqa.selenium.By;

public class Button extends BaseElement{

    public Button(final By locator) {
        super(locator);
    }
    public Button(final By locator, String name) {
        super(locator,name);
    }
}
