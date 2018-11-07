package pinterest.pages;

import framework.BaseForm;
import framework.Log;
import framework.elements.Button;
import org.openqa.selenium.By;

public class UserPage extends BaseForm {

    public enum Tabs{
        BOARDS("Boards"),
        PINS("Pins"),
        TRIES("Tries"),
        TOPICS("Topics");

        private String innerText;

        Tabs(String name) {
            this.innerText = name;
        }

        public String getInnerText(){
            return innerText;
        }
    }

    private static By formLocator = By.xpath("//div[@class='BrioProfileHeaderWrapper']");
    private String strLocatorTabs = "//div[@role='tablist']//div[text()='%s']";

    public UserPage() {
        super(formLocator);
    }

    public void navigate(Tabs tab){
        Log.info(String.format("Navigate to tab '%s' in the user tabs", tab.innerText));
        Button btnTab = new Button(By.xpath(String.format(strLocatorTabs, tab.getInnerText())), tab.getInnerText());
        btnTab.waitAndClick();
    }
}