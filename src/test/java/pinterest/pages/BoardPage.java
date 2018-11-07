package pinterest.pages;

import framework.BaseForm;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;
import pinterest.menu.Menu;

public class BoardPage extends BaseForm {

    public enum InfoBar{
        ACTIONS ("Board Actions"),
        SEND    ("Send board"),
        EDIT    ("Edit board");

        private final String buttonName;

        InfoBar(String name){
            this.buttonName = name;
        }

        public String getName(){
            return this.buttonName;
        }
    }

    private static By formLocator = By.xpath("//div[@class='boardTabs']//div[text()='Your Pins']");
    private Label lblSecretBoard = new Label(By.xpath("//*[name()='svg' and @aria-label='Secret Board' ]"), "SecretBoard");
    private String strLocatorInfoBar = "//div[@class='infoBar']//button[@aria-label='Send board']";

    private final String SEND = "Send board";

    public BoardPage() {
        super(formLocator);
    }

    public Menu getMenu() {
        return new Menu();
    }

    public boolean isSecret(){
        return lblSecretBoard.isDisplayed();
    }

    public boolean isButtonPresent(InfoBar button){
        Button btn = new Button(By.xpath(String.format(strLocatorInfoBar, button.getName())), button.getName());
        return btn.isDisplayed();
    }
}
