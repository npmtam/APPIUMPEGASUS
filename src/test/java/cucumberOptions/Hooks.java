package cucumberOptions;

import commons.AbstractPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;
import pageObjects.HomePO;
import pageObjects.LoginPO;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Hooks {
    private static AndroidDriver driver;
    private static String packageApp;


    @Before
    public static AndroidDriver openPaxApp(String appName){
        if(appName.equals("mycar")){
            packageApp = "com.mycar.passenger";
        }else if (appName.equals("pegasus")){
            packageApp = "com.qupworld.pegasuspax";
        }
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("udid", "21d5ac3d19037ece");
        cap.setCapability("deviceName", "21d5ac3d19037ece");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "9");
        cap.setCapability("appPackage", packageApp);
        cap.setCapability("appActivity", "com.qup.pegasus.ui.launch.LauncherActivity");
        cap.setCapability("automationName", "appium");
        cap.setCapability("autoGrantPermissions", "true");
        cap.setCapability("skipDeviceInitialization", "true");
        cap.setCapability("skipServerInstallation", "true");
        cap.setCapability("noReset", "true");
        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }

    @After
    public void quitDriver(){
        driver.quit();
    }
}
