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

public class TestCreatingBoardWhenSavePin extends BasePinterestTest {
    /**
     * This test creates a board from a pin save form
     * Expected result: Board is created and displayed on the user boards page
     * @param email     user email
     * @param password  user password
     * @param name      board name
     */
    @Test(dataProvider = "ValidBoardName")
    public void testCreatingBoardWhenSavePin(String email, String password, String name) {
        Log.step(1);
        RegistrationPage registrationPage = new RegistrationPage();

        Log.step(2);
        registrationPage.clickLogin();
        LoginPage loginPage= new LoginPage();
        loginPage.login(email, password);
        HomePage homePage = new HomePage();
        homePage.getMenu().navigateItem(Menu.MainMenu.PROFILE);
        UserPage userPage = new UserPage();
        userPage.navigate(UserPage.Tabs.BOARDS);
        UserBoardsPage userBoardsPage = new UserBoardsPage();
        Board board = new Board(name, false);
        userBoardsPage.deleteBoardIfExists(board);

        Log.step(3);
        userBoardsPage.getMenu().navigateItem(Menu.MainMenu.HOME);
        homePage.saveFirstPin();

        Log.step(4);
        CreateBoardForm createBoardForm = new CreateBoardForm();

        createBoardForm.createBoard(board);
        homePage.isOpen();

        Log.step(5);
        homePage.getMenu().navigateItem(Menu.MainMenu.PROFILE);
        userPage.isOpen();
        userPage.navigate(UserPage.Tabs.BOARDS);
        userBoardsPage.isOpen();
        Assert.assertTrue(userBoardsPage.isBoardPresented(board),
                "The board " + board.getName() + " is not in the list of boards");

        Log.step(6);
        userBoardsPage.clickEditBoard(board);
        EditBoardForm editBoardForm = new EditBoardForm();
        editBoardForm.clickDelete();
        AreYouSureForm areYouSureForm = new AreYouSureForm();
        areYouSureForm.clickDelete();
        Assert.assertFalse(userBoardsPage.isBoardPresented(board),
                "The board " + board.getName() + " doesn't delete!");

        Log.step(7);
        userBoardsPage.getMenu().navigateSettings(Menu.Settings.LOGOUT);
        softAssert.assertTrue(registrationPage.isOpen(), "Logout failed!");
    }
}