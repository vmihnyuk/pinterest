package pinterest.forms;

import framework.BaseForm;
import framework.elements.Button;
import framework.elements.CheckBox;
import framework.elements.Label;
import framework.elements.TextBox;
import org.openqa.selenium.By;
import pinterest.objects.Board;

public class CreateBoardForm extends BaseForm {

    private static By formLocator = By.xpath("//div/*[text()='Create board']");

    private TextBox txbName = new TextBox(By.xpath("//input[@id='boardEditName']"), "Board name");
    private Button btnCreate = new Button(By.xpath("//button/div[text()='Create']"), "Create button");
    private Button btnCancel = new Button(By.xpath("//button/div[text()='Cancel']"), "Cancel button");
    private CheckBox chbSecret = new CheckBox(By.xpath("//input[@id='secret']"), "Secret board");
    private Label lblForm = new Label(By.xpath("//div[@aria-label='Create board']"), "Form");

    private Label lblFormError = new Label(By.xpath("//form/div[@role='list']/following-sibling::div/div[position()='2']"), "Error on form");
    private Label lblPopupError = new Label(By.xpath("//form//span[@id='boardEditName-gestalt-error']"), "Popup error");

    public CreateBoardForm() {
        super(formLocator);
    }

    public void createBoard(Board board){
        if (board.getIsSecret()) chbSecret.check();
        else chbSecret.uncheck();
        txbName.sendKey(board.getName());
        btnCreate.clickAndWait();
        lblForm.waitInvisibility();
    }

    public boolean isSecret(){
        return chbSecret.isChecked();
    }

    public String getErrorText(String position){
        if (position.equalsIgnoreCase("form")) return getErrorTextOnForm();
        else if (position.contentEquals("popup"))return getPopupErrorText();
        return null;
    }

    public void clickCancel(){
        btnCancel.clickAndWait();
    }

    public String getPopupErrorText(){
        txbName.waitAndClick();
        String error = lblPopupError.getInnerText();
        return error;
    }

    public String getErrorTextOnForm(){
        return lblFormError.getInnerText();
    }
}
