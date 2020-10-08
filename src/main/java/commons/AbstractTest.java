package commons;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AbstractTest {
    public AndroidDriver driver;
    String packageApp;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    public AndroidDriver openPax(String appName) {
        if (appName.equalsIgnoreCase("mycar")){
            packageApp = "com.mycar.passenger";
        }else if (appName.equalsIgnoreCase("pegasus")){
            packageApp = "com.qupworld.pegasuspax";
        }
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "21d5ac3d19037ece");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "9");
        cap.setCapability("appPackage", packageApp);
        cap.setCapability("appActivity", "com.qup.pegasus.ui.launch.LauncherActivity");
        cap.setCapability("automationName", "UiAutomator2");
        cap.setCapability("autoGrantPermissions", "true");
        cap.setCapability("skipDeviceInitialization", "false");
        cap.setCapability("skipServerInstallation", "false");
        cap.setCapability("noReset", "false");
        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    public void printLog(String logContent){
        System.out.println(dtf.format(now) + ":  " + logContent);
    }

    public int randomNumber2Digit() {
        Random random = new Random();
        return random.nextInt(99);
    }
}
