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

public class TestCreatingSecretBoard extends BasePinterestTest {

    /**
     * This test creates the SECRET board
     * Expected result: The secret board is created and displyed on the list of user secret boards
     * @param email     user email
     * @param password  user password
     * @param name      secret board name
     */
    @Test(dataProvider = "ValidBoardName")
    public void testCreatingNewBoard(String email, String password, String name){
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
        UserBoardsPage userBoardsPage = new UserBoardsPage();

        Log.step(4);
        Board board = new Board(name, true);
        userBoardsPage.deleteBoardIfExists(board);
        userBoardsPage.clickCreateBoard();
        CreateBoardForm createBoardForm = new CreateBoardForm();
        softAssert.assertFalse(createBoardForm.isSecret(), "Secret board selected!");

        Log.step(5);
        createBoardForm.createBoard(board);
        BoardPage boardPage = new BoardPage();
        softAssert.assertTrue(boardPage.isSecret(), "The sign of the secret board is not displayed!");
        softAssert.assertFalse(boardPage.isButtonPresent(BoardPage.InfoBar.SEND), "Secret board can be shared!");

        Log.step(6);
        boardPage.getMenu().navigateItem(Menu.MainMenu.PROFILE);
        userPage.navigate(UserPage.Tabs.BOARDS);
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
