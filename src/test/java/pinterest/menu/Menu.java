package pinterest.menu;

import framework.BaseForm;
import framework.Log;
import framework.elements.Button;
import org.openqa.selenium.By;

import java.util.Set;

public class Menu extends BaseForm {

private static By formLocator = By.xpath("//div[@id='HeaderContent']");
private String strLocatorMenu = "//div[@class='HeaderContent']//*[@aria-label='%s']";
private String strLocatorSettings = "//div[@role='listitem']//div[contains(text(), '%s')]";

    public Menu() {
        super(formLocator);
    }

    public enum MainMenu {
        HOME("Home"),
        FOLLOWING("Pins from people you follow"),
        PROFILE("Saved"),
        MESSEGES("Messages"),
        NOTIFICATION("Notifications"),
        SETTINGS("Settings and more options related to your account");

        private String innerText;

        MainMenu(String name) {
            this.innerText = name;
        }

        public String getInnerText(){
            return innerText;
        }
    }

    public enum Settings {
        EDIT("Edit settings"),
        UPGRADE("Upgrade to a business account"),
        HELP("Get help"),
        PRIVACY("See terms and privacy"),
        ADD_ACCOUNT("Add account"),
        LOGOUT("Log out");

        private String innerText;

        Settings(String name) {
            this.innerText = name;
        }

        public String getInnerText(){
            return innerText;
        }
    }

    public void navigateItem(MainMenu mainItemName){
        Log.info(String.format("Navigate to '%s' in the menu", mainItemName.getInnerText()));
        Button btnMainItem = new Button(By.xpath(String.format(strLocatorMenu, mainItemName.getInnerText())), mainItemName.getInnerText());
        btnMainItem.clickAndWait();
    }

    public void navigateSettings(Settings item){
        navigateItem(MainMenu.SETTINGS);
        Button btnSettingsItem = new Button(By.xpath(String.format(strLocatorSettings, item.getInnerText())), item.getInnerText());
        btnSettingsItem.clickAndWait();
    }

}
