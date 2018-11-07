package pinterest.forms;

import framework.BaseForm;
import framework.elements.Button;
import org.openqa.selenium.By;

public class EditBoardForm extends BaseForm {

    private static By formLocator = By.xpath("//h5[text()='Edit your board']");
    private String strLocatorButtonOnForm = "//button/div[text()='%s']";
    private final String DELETE = "Delete";

    public EditBoardForm() {
        super(formLocator);
    }

    public void clickDelete(){
        Button btnDelete = new Button(By.xpath(String.format(strLocatorButtonOnForm, DELETE)), "Delete button");
        btnDelete.clickAndWait();
    }
}
