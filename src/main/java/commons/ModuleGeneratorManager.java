package commons;

import io.appium.java_client.android.AndroidDriver;
import pageObjects.HomePO;
import pageObjects.LoginPO;

public class ModuleGeneratorManager {

    public static HomePO getHomePage(AndroidDriver driver){
        return new HomePO(driver);
    }

    public static LoginPO getLoginPage (AndroidDriver driver){
        return new LoginPO(driver);
    }
}
