package testrunner;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.RegistrationPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class RegistrationTestRunner extends Setup {
    RegistrationPage registrationPage;

    @Test(priority = 1, description = "User should not be able to registration new account successfully without any inputs")
    public void doInvalidRegistration() throws InterruptedException {
        registrationPage = new RegistrationPage(driver);
        registrationPage.doRegistrationLink();
        String firstName = "";
        String lastName = "";
        String email = "";
        String companyName = "";
        String password = "";
        registrationPage.doRegistration(firstName, lastName, email, companyName, password);

        String firstName_actual = driver.findElement(By.id("FirstName-error")).getText();
        String firstName_expected = "First name is required.";
        String lastName_actual = driver.findElement(By.id("LastName-error")).getText();
        String lastName_expected = "Last name is required.";
        String email_actual = driver.findElement(By.id("Email-error")).getText();
        String email_expected = "Email is required.";
        String password_actual = driver.findElement(By.id("Password-error")).getText();
        String password_expected = "Password is required.";
        String confirmPassword_actual = driver.findElement(By.id("ConfirmPassword-error")).getText();
        String confirmPassword_expected = "Password is required.";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(firstName_actual.contains(firstName_expected));
        softAssert.assertTrue(lastName_actual.contains(lastName_expected));
        softAssert.assertTrue(email_actual.contains(email_expected));
        softAssert.assertTrue(password_actual.contains(password_expected));
        softAssert.assertTrue(confirmPassword_actual.contains(confirmPassword_expected));
        softAssert.assertAll();
    }
    @Test(priority = 2, description = "User should not be able to registration new account successfully without firstname")
    public void doInvalidFirstNameRegistration() throws IOException, ParseException, InterruptedException {
        registrationPage = new RegistrationPage(driver);
        registrationPage.doRegistrationLink();
        Utils utils = new Utils();
        Utils.replaceJsonFile();
        utils.generateUserInfo();
        utils.setFirstName("");
        registrationPage.doRegistration(utils.getFirstName(), utils.getLastName(), utils.getEmail(), utils.getCompanyName(), utils.getPassword());
        utils.saveUserInfo(utils.getFirstName(), utils.getLastName(), utils.getEmail(), utils.getCompanyName(), utils.getPassword());

        String validate_actual = driver.findElement(By.id("FirstName-error")).getText();
        String validate_expected = "First name is required.";
        Assert.assertTrue(validate_actual.contains(validate_expected));
    }

    @Test(priority = 3, description = "User should not be able to registration new account successfully without lastName")
    public void doInvalidlastNameRegistration() throws IOException, ParseException, InterruptedException {
        registrationPage = new RegistrationPage(driver);
        registrationPage.doRegistrationLink();
        Utils utils = new Utils();
        Utils.replaceJsonFile();
        utils.generateUserInfo();
        utils.setLastName("");
        registrationPage.doRegistration(utils.getFirstName(), utils.getLastName(), utils.getEmail(), utils.getCompanyName(), utils.getPassword());
        utils.saveUserInfo(utils.getFirstName(), utils.getLastName(), utils.getEmail(), utils.getCompanyName(), utils.getPassword());

        String lastName_actual = driver.findElement(By.id("LastName-error")).getText();
        String lastName_expected = "Last name is required.";
        Assert.assertTrue(lastName_actual.contains(lastName_expected));
    }

    @Test(priority = 4, description = "User should not be able to registration new account successfully without email")
    public void doInvalidEmailRegistration4() throws IOException, ParseException, InterruptedException {
        registrationPage = new RegistrationPage(driver);
        registrationPage.doRegistrationLink();
        Utils utils = new Utils();
        Utils.replaceJsonFile();
        utils.generateUserInfo();
        utils.setEmail("");
        registrationPage.doRegistration(utils.getFirstName(), utils.getLastName(), utils.getEmail(), utils.getCompanyName(), utils.getPassword());
        utils.saveUserInfo(utils.getFirstName(), utils.getLastName(), utils.getEmail(), utils.getCompanyName(), utils.getPassword());

        String email_actual = driver.findElement(By.id("Email-error")).getText();
        String email_expected = "Email is required.";
        Assert.assertTrue(email_actual.contains(email_expected));
    }

    @Test(priority = 5, description = "User should not be able to registration new account successfully without password")
    public void doInvalidPasswordRegistration() throws IOException, ParseException, InterruptedException {
        registrationPage = new RegistrationPage(driver);
        registrationPage.doRegistrationLink();
        Utils utils = new Utils();
        Utils.replaceJsonFile();
        utils.generateUserInfo();
        utils.setPassword("");
        registrationPage.doRegistration(utils.getFirstName(), utils.getLastName(), utils.getEmail(), utils.getCompanyName(), utils.getPassword());
        utils.saveUserInfo(utils.getFirstName(), utils.getLastName(), utils.getEmail(), utils.getCompanyName(), utils.getPassword());

        String password_actual = driver.findElement(By.id("Password-error")).getText();
        String password_expected = "Password is required.";
        Assert.assertTrue(password_actual.contains(password_expected));
    }

    @Test(priority = 6, description = "User should be able to registration new account successfully when provided all the details")
    public void doUserRegistration() throws IOException, ParseException, InterruptedException {
        registrationPage = new RegistrationPage(driver);
        driver.navigate().refresh();
        Utils utils = new Utils();
        Utils.replaceJsonFile();
        utils.generateUserInfo();
        registrationPage.doRegistration(utils.getFirstName(), utils.getLastName(), utils.getEmail(), utils.getCompanyName(), utils.getPassword());
        utils.saveUserInfo(utils.getFirstName(), utils.getLastName(), utils.getEmail(), utils.getCompanyName(), utils.getPassword());

        String validate_actual = driver.findElement(By.className("result")).getText();
        String validate_expected = "Your registration completed";
        Assert.assertTrue(validate_actual.contains(validate_expected));
        driver.close();
    }

}
