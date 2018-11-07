package framework.elements;

import framework.Log;
import org.openqa.selenium.By;

public class CheckBox extends BaseElement {

    public CheckBox(final By locator) {
        super(locator);
    }
    public CheckBox(final By locator, String name) {
        super(locator, name);
    }

    public boolean isChecked(){
        return findElement().isSelected();
    }

    public void check(){
        if (!isChecked()) findElement().click();
    }

    public void uncheck(){
        Log.info(String.format("Uncheck checkbox '%s' with locator %s", name, locator));
        if (isChecked()) clickAndWait();
    }
}
