package login;

import commons.AbstractPage;
import commons.AbstractTest;
import io.appium.java_client.android.AndroidDriver;
import org.junit.BeforeClass;
import org.junit.Test;
import pageObjects.HomePO;
import pageObjects.LoginPO;

import static org.junit.Assert.assertTrue;

public class PAX_02_HomeScreen extends AbstractTest {
    AndroidDriver driver;
    public AbstractPage abstractPage;
    public HomePO homePage;
    public LoginPO loginPage;
    String countryName, defaultcode, phoneNumber;

    @BeforeClass
    public void beforeClass(){
        openPax("mycar");
        abstractPage = new AbstractPage(driver);
        loginPage = new LoginPO(driver);
        homePage = new HomePO(driver);

        countryName = "Vietnam";
        defaultcode = "2304";
        phoneNumber = "356822833";


        printLog("Precondition - Step 1: Select server and enter fleet code");
        loginPage.selectServer("beta", "tamqa");

        printLog("Precondition - Step 2: Select country phone code");
        loginPage.selectPhoneCode(countryName);

        printLog("Login - Step 3: Login to Pax app");
        loginPage.inputToPhoneNumberTextbox(phoneNumber);
        loginPage.clickToAgreeToUAndPolicy();
        loginPage.clickToLoginButton();
        loginPage.clickToYesNoButton("Yes");

        printLog("Login - Step 4: Input sms code if require");
        loginPage.inputSMSDefaultCode(defaultcode);

        printLog("Login - Step 5: Verify login successfully");
        assertTrue(loginPage.isThereHomeButtonPresent());
    }

    @Test
    public void TC_01_CheckCarType(){
        printLog("Home - TC01 - Open car type list");

    }



}
