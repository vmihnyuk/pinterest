package pinterest.pages;

import framework.BaseForm;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;
import pinterest.forms.AreYouSureForm;
import pinterest.forms.EditBoardForm;
import pinterest.menu.Menu;
import pinterest.objects.Board;

public class UserBoardsPage extends BaseForm {

    private static By formLocator = By.xpath("//div[@class='UserProfileContent']//div[text()='Secret boards']");
    private Button btnCreateBoard = new Button(By.xpath("//div[@data-test-id='createBoardCard']//div[text()='Create board']"), "Create board button");

    private String strLocatorSecretBoard =
        "//div[text()='Create secret board']/ancestor::div[@data-test-id='createBoardCard']/parent::div/parent::div//div[@data-test-id='board-%s']";

    private String strLocatorOrdinaryBoard =
            "//div[text()='Create board']/ancestor::div[@data-test-id='createBoardCard']/parent::div/parent::div//div[@data-test-id='board-%s']";
    private String strLocatorAnyBoard = "//div[@data-test-id='board-%s']";


    private String strLocatorBtnEdit = "//button[@aria-label='Edit']";
    private String strLocatorBoardName = "//div[@title]";

    public UserBoardsPage() {
        super(formLocator);
    }

    public void clickCreateBoard(){
        btnCreateBoard.clickAndWait();
    }

    public void clickEditBoard(Board board){
        Label lblBoard = new Label(By.xpath(getStrLocatorBoard(board)), String.format("Board '%s'", board.getName()));
        browser.scrollIntoView(By.xpath(getStrLocatorBoard(board)));
        lblBoard.moveToElement();
        Button btnEdit = new Button(getBoardElementLocator(board, strLocatorBtnEdit), "Edit board button");
        btnEdit.clickAndWait();
    }

    public String getBoardName(Board board){
        Label lblBoardName = new Label(getBoardElementLocator(board, strLocatorBoardName), String.format("Board '%s'", board.getName()));
        return lblBoardName.getInnerText();
    }

    public boolean isBoardPresented(Board board){
        Label lblBoard = new Label(By.xpath(getStrLocatorBoard(board)));
        return lblBoard.isDisplayed();
    }

    private String getStrLocatorBoard(Board board){
        String strLocator;
        if (board.getIsSecret()) strLocator=strLocatorSecretBoard;
        else strLocator = strLocatorOrdinaryBoard;
        return String.format(strLocator, board.getName());
    }

    private By getBoardElementLocator(Board board, String strElementLocator){
        return By.xpath(new StringBuilder(getStrLocatorBoard(board)).append(strElementLocator).toString());
    }

    public Menu getMenu(){
        return new Menu();
    }

    public void deleteBoardIfExists(Board board){
        Label lblBoard = new Label(By.xpath(String.format(strLocatorAnyBoard, board.getName())));
        if (lblBoard.isDisplayed()){
            clickEditBoard(board);
            EditBoardForm editBoardForm = new EditBoardForm();
            editBoardForm.clickDelete();
            AreYouSureForm areYouSureForm = new AreYouSureForm();
            areYouSureForm.clickDelete();
        }
    }
}