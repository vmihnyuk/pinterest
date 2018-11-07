package pinterest.pages;

import framework.BaseForm;
import framework.elements.Button;
import org.openqa.selenium.By;

public class RegistrationPage extends BaseForm {

    private static By formLocator = By.xpath("//div[@data-test-id='signup']//form[@data-test-id='registerForm']");
    private static String name = "Registration page";
    private Button btnLogin = new Button(By.xpath("//div[@data-test-id='signup']//div/div[position()='2']/button[@type='button']/div"), "Login button");

    public RegistrationPage() {
        super(formLocator);
    }

    public void clickLogin(){
        btnLogin.clickAndWait();
    }
}