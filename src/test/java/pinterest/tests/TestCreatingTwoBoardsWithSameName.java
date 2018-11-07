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

public class TestCreatingTwoBoardsWithSameName extends BasePinterestTest {

    /**
     * This test creates two boards with the same name
     * Expected result: the second board is not created. Error messege displayed
     * @param email     user email
     * @param password  user password
     * @param name      name of both boards
     * @param error     error text
     */
    @Test(dataProvider = "TwoSameBoards")
    public void testCreatingTwoBoardsWithSameName(String email, String password, String name, String error) {
        Log.step(1);
        RegistrationPage registrationPage = new RegistrationPage();

        Log.step(2);
        registrationPage.clickLogin();
        LoginPage loginPage = new LoginPage();
        loginPage.login(email, password);
        HomePage homePage = new HomePage();

        Log.step(3);
        homePage.getMenu().navigateItem(Menu.MainMenu.PROFILE);
        UserPage userPage = new UserPage();
        userPage.navigate(UserPage.Tabs.BOARDS);
        UserBoardsPage userBoardsPage = new UserBoardsPage();

        Log.step(4);
        Board firstBoard = new Board(name, false);
        userBoardsPage.deleteBoardIfExists(firstBoard);
        userBoardsPage.clickCreateBoard();
        CreateBoardForm createBoardForm = new CreateBoardForm();

        Log.step(5);

        createBoardForm.createBoard(firstBoard);
        BoardPage boardPage = new BoardPage();

        Log.step(6);
        boardPage.getMenu().navigateItem(Menu.MainMenu.PROFILE);
        userPage.navigate(UserPage.Tabs.BOARDS);
        Assert.assertTrue(userBoardsPage.isBoardPresented(firstBoard), "The first board is not in the list");
        Board secondOrdinaryBoard = new Board(name, false);
        userBoardsPage.clickCreateBoard();
        createBoardForm.createBoard(secondOrdinaryBoard);
        Assert.assertFalse(boardPage.isOpen(), "The second board with the same name was created!");
        softAssert.assertEquals(createBoardForm.getErrorTextOnForm(), error);

        Log.step(7);
        Board secondSecretBoard = new Board(name, true);
        createBoardForm.createBoard(secondSecretBoard);
        Assert.assertFalse(boardPage.isOpen(), "The secret board with the same name was created!");
        softAssert.assertEquals(createBoardForm.getErrorTextOnForm(), error);

        Log.step(8);
        createBoardForm.clickCancel();
        userBoardsPage.clickEditBoard(firstBoard);
        EditBoardForm editBoardForm = new EditBoardForm();
        editBoardForm.clickDelete();
        AreYouSureForm areYouSureForm = new AreYouSureForm();
        areYouSureForm.clickDelete();

        Log.step(9);
        userBoardsPage.getMenu().navigateSettings(Menu.Settings.LOGOUT);
        softAssert.assertTrue(registrationPage.isOpen(), "Logout failed!");
        softAssert.assertAll();
    }
}