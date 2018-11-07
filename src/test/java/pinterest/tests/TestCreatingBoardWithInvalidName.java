package pinterest.tests;

import framework.Log;
import org.testng.Assert;
import org.testng.annotations.Test;
import pinterest.forms.CreateBoardForm;
import pinterest.menu.Menu;
import pinterest.objects.Board;
import pinterest.pages.*;

public class TestCreatingBoardWithInvalidName extends BasePinterestTest {
    /**
     * This test creates the board with invalid name
     * Expected result: the board is not created. Error messege displayed
     * @param email     user email
     * @param password  user password
     * @param name      board name
     * @param error     error text
     * @param position  error position: form or popup
     */
    @Test(dataProvider = "InvalidBoardName")
    public void testCreatingBoardWithInvalidName(String email, String password, String name, String error, String position) {
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
        Board board = new Board(name, false);
        userBoardsPage.deleteBoardIfExists(board);
        userBoardsPage.clickCreateBoard();
        CreateBoardForm createBoardForm = new CreateBoardForm();
        softAssert.assertFalse(createBoardForm.isSecret(), "Secret board selected!");
        createBoardForm.createBoard(board);
        Assert.assertTrue(createBoardForm.isOpen(), String.format("Board with invalid name '%s' was created!", board.getName()));
        softAssert.assertEquals(createBoardForm.getErrorText(position), error, "Wrong error messege!");

        Log.step(5);
        createBoardForm.clickCancel();

        Log.step(6);
        userBoardsPage.getMenu().navigateSettings(Menu.Settings.LOGOUT);
        softAssert.assertTrue(registrationPage.isOpen(), "Logout failed!");
        softAssert.assertAll();
    }
}