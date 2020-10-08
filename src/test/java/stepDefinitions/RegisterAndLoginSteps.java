package stepDefinitions;

import commons.AbstractPage;
import commons.AbstractTest;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
//import cucumberOptions.Hooks;
import cucumberOptions.Hooks;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import pageObjects.HomePO;
import pageObjects.LoginPO;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;


@RunWith(Cucumber.class)
public class RegisterAndLoginSteps {
    AndroidDriver driver;
    AbstractPage abstractPage;
    LoginPO loginPage;
    HomePO homePage;
    String appTest;

    String packageApp, passCode, defaultCode;

    public RegisterAndLoginSteps() {
        driver = Hooks.openPaxApp("mycar");
        passCode = "7620";
        defaultCode = "1211";

        abstractPage = new AbstractPage(driver);
        loginPage = new LoginPO(driver);
        homePage = new HomePO(driver);

        abstractPage.sendAppPackage();
    }

    @Given("^I logout if currently logged in$")
    public void iLogoutIfCurrentlyLoggedIn() {
        abstractPage.sleepInSecond(1);
        if (loginPage.isCurrentlyLoggedIn()) {
            homePage.logout();
            abstractPage.sleepInSecond(1);
            loginPage.clickToPhoneNumberTextbox();
        } else {
            loginPage.clickToPhoneNumberTextbox();
        }

    }

    @And("^I select the phone code by \"([^\"]*)\" country$")
    public void iSelectThePhoneCodeByCountry(String countryName) {
        loginPage.selectPhoneCode(countryName);
    }

    @Then("^I verify the passenger register successfully$")
    public void iVerifyThePassengerRegisterSuccessfully() {
        loginPage.clickToSkipButton();
        loginPage.clickToYesNoButton("Yes");
        assertTrue(loginPage.isThereHomeButtonPresent());
    }

    @When("^I select \"([^\"]*)\" server and input \"([^\"]*)\" fleet code$")
    public void iSelectServerAndInputFleetCode(String server, String fleetCode) {
//        loginPage.clickToPhoneNumberTextbox();
        loginPage.longPressToDebugArea();
        loginPage.inputToPassCodeTextbox(passCode);
        loginPage.clickToYesNoButton("YES");
        loginPage.selectServer(server, fleetCode);
        assertTrue(loginPage.isLoginPagePresent());
    }

    @Given("^I click to phone number text box$")
    public void iClickToPhoneNumberTextBox() {

        loginPage.clickToPhoneNumberTextbox();
    }

    @And("^I input the phone number$")
    public void i_input_the_phone_number(DataTable customerTable) {
        List<Map<String, String>> phoneNumber = customerTable.asMaps(String.class, String.class);
        loginPage.inputToPhoneNumberTextbox(phoneNumber.get(0).get("phoneNumber"));
    }


    @And("^I agree with Term of use and Privacy policy$")
    public void iAgreeWithTermOfUseAndPrivacyPolicy() {
        loginPage.clickToAgreeToUAndPolicy();
    }

    @And("^I click to continue button$")
    public void iClickToContinueButton() {
        loginPage.clickToLoginButton();
        loginPage.clickToYesNoButton("Yes");
    }

    @And("^I input sms verify code if have$")
    public void iInputSmsVerifyCodeIfHave() {
        loginPage.inputSMSDefaultCode(defaultCode);
    }

    @And("^I verify register message contains \"([^\"]*)\"$")
    public void iVerifyRegisterMessageContains(String textContains) {
        assertTrue(loginPage.isWelcomeMsgContains(textContains));
        loginPage.clickToYesNoButton("OK");
    }

    @And("^I input user information to register$")
    public void iInputUserInformationToRegister(DataTable customerTable) {
        List<Map<String, String>> customer = customerTable.asMaps(String.class, String.class);
        loginPage.inputToRegisterTextboxes("First name", customer.get(0).get("FirstName"));
        loginPage.inputToRegisterTextboxes("Last name", customer.get(0).get("LastName"));
        loginPage.inputToRegisterTextboxes("Email", customer.get(0).get("Email"));
        loginPage.inputToRegisterTextboxes("ID# / National IC#", customer.get(0).get("ID/IC"));
        loginPage.selectGender(customer.get(0).get("Gender"));
    }

    @And("^I click to save button$")
    public void iClickToSaveButton() {
        loginPage.clickToSaveButton();
    }

    @Then("^I verify the passenger login successfully$")
    public void iVerifyThePassengerLoginSuccessfully() {
        assertTrue(loginPage.isThereHomeButtonPresent());
    }


}
