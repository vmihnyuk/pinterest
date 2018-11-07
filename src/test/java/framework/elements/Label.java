package framework.elements;

import framework.CommonFunctions;
import org.openqa.selenium.By;

public class Label extends BaseElement{

    public Label(final By locator) {
        super(locator);
    }
    public Label(final By locator, String name) {
        super(locator, name);
    }

    public String getGroupFromInnerText(String regexp,int numberOfGroup){
        String str = this.getText();
        return CommonFunctions.regexpGetGroup(str, regexp, numberOfGroup);
    }

    public String getInnerText(){
        return this.getText();
    }
}
