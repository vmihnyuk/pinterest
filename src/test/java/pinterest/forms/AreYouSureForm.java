package pinterest.forms;

import framework.BaseForm;
import framework.elements.Button;
import org.openqa.selenium.By;

public class AreYouSureForm extends BaseForm {

    private static By formLocator = By.xpath("//form[@class='standardForm']//div[text()='Are you sure?']");
    private String strLocatorButtonsOnForm = "//div[@class='formFooterButtons']//div[text()='%s']";
    private final String DELETE = "Delete board";

    public AreYouSureForm() {
        super(formLocator);
    }

    public void clickDelete(){
        Button btnDelete = new Button(By.xpath(String.format(strLocatorButtonsOnForm, DELETE)), "Delete button");
        btnDelete.clickAndWait();
    }
}
