package pinterest.pages;

import framework.BaseForm;
import framework.elements.Button;
import framework.elements.TextBox;
import org.openqa.selenium.By;

public class LoginPage extends BaseForm {

    private static By formLocator = By.xpath("//div[@data-test-id='login']");

    private TextBox txbEmail = new TextBox(By.xpath("//input[@id='email']"), "Email");
    private TextBox txbPassword = new TextBox(By.xpath("//input[@id='password']"), "Password");

    private Button btnLogin = new Button(By.xpath("//button[@class='red SignupButton active']"), "Login button");

    public LoginPage() {
        super(formLocator);
    }

    public void login(String email, String password){
        txbEmail.sendKey(email);
        txbPassword.sendKey(password);
        btnLogin.clickAndWait();
    }
}
