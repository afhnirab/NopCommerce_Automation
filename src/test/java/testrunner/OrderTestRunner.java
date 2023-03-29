package testrunner;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.OrderPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class OrderTestRunner extends Setup {
    OrderPage orderPage;
    @Test(priority = 1, description = "User should not be able to add product in card using zero number")
    public void placeOrderInvalidQuantity() throws InterruptedException {
        orderPage = new OrderPage(driver);
        String quantity = "0";
        orderPage.doQuantity(quantity);
        driver.navigate().refresh();
    }

    @Test(priority = 2, description = "User shall be able to add product in card using positive number")
    public void placeOrderValidQuantity() throws InterruptedException {
        String quantity = "2";
        orderPage.doQuantity(quantity);
        orderPage.doCart();
    }
    @Test(priority = 3, description = "User should not be able to continue without providing any inputs")
    public void placeOrderBlankFields() throws IOException {
        Utils utils = new Utils();
        Utils.replaceJsonGuestFile();
        utils.generateGuestUserInfo();
        utils.setFirstName("");
        utils.setLastName("");
        utils.setEmail("");
        utils.setCity("");
        utils.setAddress("");
        utils.setZip("");
        utils.setPhoneNumber("");
        orderPage.doOrder(utils.getFirstName(), utils.getLastName(), utils.getEmail(), utils.getCity(), utils.getAddress(), utils.getZip(), utils.getPhoneNumber());
        driver.navigate().refresh();
    }

    @Test(priority = 4, description = "User should not be able to place order as a guest user without first name")
    public void placeOrderBlankFirstName() throws IOException {
        Utils utils = new Utils();
        Utils.replaceJsonGuestFile();
        utils.generateGuestUserInfo();
        utils.setFirstName("");
        orderPage.doOrder(utils.getFirstName(), utils.getLastName(), utils.getEmail(), utils.getCity(), utils.getAddress(), utils.getZip(), utils.getPhoneNumber());

        String validate_actual = driver.findElements(By.className("field-validation-error")).get(0).getText();
        String validate_expected = "First name is required.";
        Assert.assertTrue(validate_actual.contains(validate_expected));
        driver.navigate().refresh();
    }

    @Test(priority = 5, description = "User should not be able to place order as a guest user without last name")
    public void placeOrderBlankLastName() throws IOException {
        Utils utils = new Utils();
        Utils.replaceJsonGuestFile();
        utils.generateGuestUserInfo();
        utils.setLastName("");
        orderPage.doOrder(utils.getFirstName(), utils.getLastName(), utils.getEmail(), utils.getCity(), utils.getAddress(), utils.getZip(), utils.getPhoneNumber());

        String validate_actual = driver.findElements(By.className("field-validation-error")).get(0).getText();
        String validate_expected = "Last name is required.";
        Assert.assertTrue(validate_actual.contains(validate_expected));
        driver.navigate().refresh();
    }

    @Test(priority = 6, description = "User should not be able to place order as a guest user using invalid email pattern")
    public void placeOrderInvalidEmail() throws IOException {
        Utils utils = new Utils();
        Utils.replaceJsonGuestFile();
        utils.generateGuestUserInfo();
        utils.setEmail("demo");
        orderPage.doOrder(utils.getFirstName(), utils.getLastName(), utils.getEmail(), utils.getCity(), utils.getAddress(), utils.getZip(), utils.getPhoneNumber());

        String validate_actual = driver.findElement(By.className("field-validation-error")).getText();
        String validate_expected = "Wrong email";
        Assert.assertTrue(validate_actual.contains(validate_expected));
        driver.navigate().refresh();
    }

    @Test(priority = 7, description = "User shall be able to complete order when all the checkout requirements are filled")
    public void placeOrderValid() throws IOException, ParseException, InterruptedException {
        Utils utils = new Utils();
        Utils.replaceJsonGuestFile();
        utils.generateGuestUserInfo();
        orderPage.doOrder(utils.getFirstName(), utils.getLastName(), utils.getEmail(), utils.getCity(), utils.getAddress(), utils.getZip(), utils.getPhoneNumber());
        utils.saveGuestUserInfo(utils.getFirstName(), utils.getLastName(), utils.getEmail(), utils.getCity(), utils.getAddress(), utils.getZip(), utils.getPhoneNumber());
        orderPage.doShipping();
        utils.generateCreditCardInfo();
        orderPage.demoCredit(utils.getFirstName(), utils.getCreditCard(), utils.getCardNumber());
        orderPage.doConfirm();
        driver.quit();
    }
}
