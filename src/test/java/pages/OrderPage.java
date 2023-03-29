package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
public class OrderPage {
    Actions actions;
    @FindBy(linkText = "Electronics")
    public WebElement linkElectronics;
    @FindBy(linkText = "Cell phones")
    public WebElement linkCellPhone;
    @FindBy(linkText = "Nokia Lumia 1020")
    public WebElement linkNokiaLumia;
    @FindBy(id = "product_enteredQuantity_20")
    public WebElement txtQuantity;
    @FindBy(id = "add-to-cart-button-20")
    public WebElement btnAddtoCar;
    @FindBy(linkText = "shopping cart")
    public WebElement linkCart;
    @FindBy(id = "termsofservice")
    public WebElement checkBox;
    @FindBy(id = "checkout")
    public WebElement btnCheckout;
    @FindBy(className = "checkout-as-guest-button")
    public WebElement btnGuest;
    @FindBy(tagName = "input")
    public List <WebElement> txtUserFields;
    @FindBy(id = "BillingNewAddress_CountryId")
    public WebElement optionCountry;
    @FindBy(id = "BillingNewAddress_StateProvinceId")
    public WebElement optionState;
    @FindBy(className = "new-address-next-step-button")
    public List <WebElement> btnContinue;
    @FindBy(id = "shippingoption_1")
    public WebElement btnShipping;
    @FindBy(className = "shipping-method-next-step-button")
    public WebElement btnShippingContinue;
    @FindBy(id = "paymentmethod_1")
    public WebElement btnCard;
    @FindBy(className = "payment-method-next-step-button")
    public WebElement btnCardContinue;
    @FindBy(id = "CardholderName")
    public WebElement txtCardName;
    @FindBy(id = "CardNumber")
    public WebElement txtCardnumber;
    @FindBy(id = "ExpireMonth")
    public WebElement optionMonth;
    @FindBy(id = "CardCode")
    public WebElement txtCode;
    @FindBy(className = "payment-info-next-step-button")
    public WebElement btnPaymentContinue;
    @FindBy(className = "confirm-order-next-step-button")
    public WebElement btnConfirm;

    public OrderPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

    public void doQuantity(String quantity) throws InterruptedException {
        actions.moveToElement(linkElectronics).perform();
        linkCellPhone.click();
        linkNokiaLumia.click();
        txtQuantity.sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        txtQuantity.sendKeys(quantity);
        btnAddtoCar.click();
        Thread.sleep(3000);
    }

    public void doCart() throws InterruptedException {
        linkCart.click();
        checkBox.click();
        Thread.sleep(1000);
        btnCheckout.click();
        btnGuest.click();
        Thread.sleep(1000);
    }
    public void doOrder(String firstName, String lastName, String email, String city, String address, String zip, String phoneNumber) {
        txtUserFields.get(4).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        txtUserFields.get(4).sendKeys(firstName);
        txtUserFields.get(5).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        txtUserFields.get(5).sendKeys(lastName);
        txtUserFields.get(6).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        txtUserFields.get(6).sendKeys(email);

        Select selectCountry = new Select(optionCountry);
        selectCountry.selectByVisibleText("United States of America");
        Select selectState = new Select(optionState);
        selectState.selectByValue("1677");
        txtUserFields.get(8).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        txtUserFields.get(8).sendKeys(city);
        txtUserFields.get(9).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        txtUserFields.get(9).sendKeys(address);
        txtUserFields.get(11).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        txtUserFields.get(11).sendKeys(zip);
        txtUserFields.get(12).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        txtUserFields.get(12).sendKeys(phoneNumber);

        btnContinue.get(0).click();
    }

    public void doShipping() throws InterruptedException {
        btnShipping.click();
        btnShippingContinue.click();
        btnCard.click();
        btnCardContinue.click();
        Thread.sleep(2000);
    }

    public void demoCredit(String fullname, String cardNumber, String cardCode) throws InterruptedException {
        txtCardName.sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        txtCardName.sendKeys(fullname);
        txtCardnumber.sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        txtCardnumber.sendKeys(cardNumber);
        Select selectMonth = new Select(optionMonth);
        selectMonth.selectByValue("9");
        txtCode.sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        txtCode.sendKeys(cardCode);
        btnPaymentContinue.click();
    }
    public void doConfirm() throws InterruptedException {
        btnConfirm.click();
        Thread.sleep(3000);
    }
}
