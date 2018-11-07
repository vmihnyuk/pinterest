package pinterest.forms;

import framework.BaseForm;
import framework.elements.Button;
import org.openqa.selenium.By;

public class ChooseBoardForm extends BaseForm {

    private static By formLocator = By.xpath("//div[@aria-label='Choose board']");

    private Button btnCreateBoard = new Button(By.xpath("//div[@data-test-id='createBoardButton']"), "Create board button");

    public ChooseBoardForm() {
        super(formLocator);
    }

    public void clickCreateBoard(){
        btnCreateBoard.clickAndWait();
    }


}
