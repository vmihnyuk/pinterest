package pinterest.pages;

import framework.BaseForm;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;
import pinterest.forms.ChooseBoardForm;
import pinterest.menu.Menu;

public class HomePage extends BaseForm {

    private static By formLocator = By.xpath("//div[@class='appContent']//div[@data-test-id='pin']");

    private String strPin = "//div[@data-test-id='pin']";

    private Button btnSaveWithSelect = new Button(By.xpath("//button[@data-test-id='PinBetterSaveButton']"), "Save pin button");
    private Button btnSave = new Button(By.xpath("//div[@data-test-id='SaveButton']"), "Save pin button");

    private Label lblPin = new Label(By.xpath(strPin), "Pin");

    private Button btnCreate = new Button(By.xpath("//div[@data-test-id='create-board']"), "Create board button");

    public HomePage() {
        super(formLocator);
    }

    public Menu getMenu() {
        return new Menu();
    }

    public void saveFirstPin(){
        lblPin.moveToElement();
        if (btnSaveWithSelect.isDisplayed()) {
            btnSaveWithSelect.clickAndWait();
            btnCreate.clickAndWait();
        }
        else{
            btnSave.clickAndWait();
            ChooseBoardForm chooseBoardForm = new ChooseBoardForm();
            chooseBoardForm.clickCreateBoard();
        }
    }
}
