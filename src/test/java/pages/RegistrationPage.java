package pages;

import com.github.javafaker.service.FakeValues;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class RegistrationPage {
    @FindBy(className = "ico-register")
    public WebElement btnRegisterLink;
    @FindBy(css = "[type=radio]")
    public List<WebElement> btnGender;
    @FindBy(id = "FirstName")
    public WebElement txtFirstName;
    @FindBy(id = "LastName")
    public WebElement txtLastName;
    @FindBy(tagName = "select")
    public List <WebElement> optionDOB;
    @FindBy(id = "Email")
    public WebElement txtEmail;
    @FindBy(id = "Company")
    public WebElement txtCompany;
    @FindBy(id = "Password")
    public WebElement txtPassword;
    @FindBy(id = "ConfirmPassword")
    public WebElement txtConfirmPassword;
    @FindBy(className = "register-next-step-button")
    public WebElement btnRegister;

    public RegistrationPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doRegistrationLink(){
        btnRegisterLink.click();
    }
    public void doRegistration(String firstName, String lastName, String email, String companyName, String password) throws InterruptedException {
        btnGender.get(0).click();
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);

        Select selectDay = new Select(optionDOB.get(1));
        selectDay.selectByValue("4");
        Thread.sleep(1000);
        Select selectMonth = new Select(optionDOB.get(2));
        selectMonth.selectByVisibleText("May");
        Thread.sleep(1000);
        Select selectYear = new Select(optionDOB.get(3));
        selectYear.selectByValue("1998");
        Thread.sleep(1000);
        txtEmail.sendKeys(email);
        txtCompany.sendKeys(companyName);
        txtPassword.sendKeys(password);
        txtConfirmPassword.sendKeys(password);
        btnRegister.click();
        Thread.sleep(2000);
    }
}
