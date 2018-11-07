package pinterest.tests;

import framework.Log;
import org.testng.Assert;
import org.testng.annotations.Test;
import pinterest.forms.AreYouSureForm;
import pinterest.forms.CreateBoardForm;
import pinterest.forms.EditBoardForm;
import pinterest.menu.Menu;
import pinterest.objects.Board;
import pinterest.pages.*;

public class TestCreatingNewBoard extends BasePinterestTest {

    /**
     * This test creates a board with valid name
     * Expected result: Board is created and displayed on the user boards page
     * @param email     user email
     * @param password  user password
     * @param name      board name
     */
    @Test(dataProvider = "ValidBoardName")
    public void testCreatingNewBoard(String email, String password, String name){
        Log.startLog(getClass().getSimpleName());

        Log.step(1);
        RegistrationPage registrationPage = new RegistrationPage();

        Log.step(2);
        registrationPage.clickLogin();
        LoginPage loginPage= new LoginPage();
        loginPage.login(email, password);
        HomePage homePage = new HomePage();

        Log.step(3);
        homePage.getMenu().navigateItem(Menu.MainMenu.PROFILE);
        UserPage userPage = new UserPage();
        userPage.navigate(UserPage.Tabs.BOARDS);

        Log.step(4);
        UserBoardsPage userBoardsPage = new UserBoardsPage();
        Board board = new Board(name, false);
        userBoardsPage.deleteBoardIfExists(board);
        userBoardsPage.clickCreateBoard();
        CreateBoardForm createBoardForm = new CreateBoardForm();
        softAssert.assertFalse(createBoardForm.isSecret(), "Secret board selected!");

        Log.step(5);

        createBoardForm.createBoard(board);
        BoardPage boardPage = new BoardPage();

        Log.step(6);
        boardPage.getMenu().navigateItem(Menu.MainMenu.PROFILE);
        userPage.isOpen();
        userPage.navigate(UserPage.Tabs.BOARDS);
        userBoardsPage.isOpen();
        Assert.assertTrue(userBoardsPage.isBoardPresented(board),
                "The board " + board.getName() + " is not in the list of boards");
        softAssert.assertEquals(userBoardsPage.getBoardName(board), board.getName(),
                "The name of the board on page is " + userBoardsPage.getBoardName(board)
                            + " but expected name is " + board.getName());

        Log.step(7);
        userBoardsPage.clickEditBoard(board);
        EditBoardForm editBoardForm = new EditBoardForm();
        editBoardForm.clickDelete();
        AreYouSureForm areYouSureForm = new AreYouSureForm();
        areYouSureForm.clickDelete();
        softAssert.assertFalse(userBoardsPage.isBoardPresented(board),
                "The board " + board.getName() + " doesn't delete!");

        Log.step(8);
        userBoardsPage.getMenu().navigateSettings(Menu.Settings.LOGOUT);
        softAssert.assertTrue(registrationPage.isOpen(), "Logout failed!");
        softAssert.assertAll();
    }
}
