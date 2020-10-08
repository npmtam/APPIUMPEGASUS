package commons;

import interfaces.AbstractPageUI;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AbstractPage {
    AndroidDriver driver;
    Actions actions;
    WebDriverWait waitExplicit;
    WebElement element;
    AndroidElement androidElement;
    List<WebElement> elements;
    By by;
    public long longTimeout = 30;
    public long shortTimeout = 3;
    public static String appPackageId, appName;
    public String appPackage = "mycar";
    AbstractPageUI abstractUI;

    public AbstractPage(AndroidDriver driver) {
        this.driver = driver;
        abstractUI = new AbstractPageUI();
    }

    public void sleepInSecond(long numberInSecond) {
        try {
            Thread.sleep(numberInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendAppPackage() {
        if (appPackage.equals("pegasus")) {
            appPackageId = "com.qupworld.pegasuspax:id/";
            appName = "Pegasus";
        } else if (appPackage.equals("mycar")) {
            appPackageId = "com.mycar.passenger:id/";
            appName = "MyCar";
        }

    }

    public void sendKeyToElementById(String locator, String textValue) {
        locator = String.format(locator, appPackageId);
        element = driver.findElement(By.id(locator));
        element.clear();
        element.sendKeys(textValue);
    }

    public void sendKeyToElementByXpath(String xpathLocator, String textValue, String attributeValue) {
        xpathLocator = String.format(xpathLocator, attributeValue);
        element = driver.findElement(By.xpath(xpathLocator));
        element.clear();
        element.sendKeys(textValue);
    }

    public void clickToElementById(String locator) {
        locator = String.format(locator, appPackageId);
        element = driver.findElement(By.id(locator));
        waitElementToBeClickableByLocator(driver, element).click();
    }

    public void clickToElementByXpath(String xpathLocator) {
        element = driver.findElement(By.xpath(xpathLocator));
        waitElementToBeClickableByLocator(driver, element).click();
    }

    public void clickToElementByXpath(String xpathLocator, String attributeValue) {
        xpathLocator = String.format(xpathLocator, attributeValue);
        element = driver.findElement(By.xpath(xpathLocator));
        waitElementToBeClickableByLocator(driver, element).click();
    }

    public void longPressToElementById(String locator) {
        locator = String.format(locator, appPackageId);
        element = driver.findElement(By.id(locator));
        actions = new Actions(driver);
        actions.clickAndHold(element);
        actions.perform();
    }

    public String getTextElementById(String locator) {
        locator = String.format(locator, appPackageId);
        element = driver.findElement(By.id(locator));
        String actualText = element.getText();
        return actualText;
    }

    public String getTextElementByXpath(String xpathLocator, String attributeValue){
        xpathLocator = String.format(xpathLocator, attributeValue);
        element = driver.findElement(By.xpath(xpathLocator));
        return element.getText();

    }

    // Internal methods
    public static WebElement waitElementToBeClickableByLocator(
            WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean checkElementPresentById(String locator) {
        driver.manage().timeouts().implicitlyWait(shortTimeout, TimeUnit.SECONDS);
        locator = String.format(locator, appPackageId);
        elements = driver.findElements(By.id(locator));
        driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
        if (elements.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkElementDisplayedByXpath(String xpathLocator){
        driver.manage().timeouts().implicitlyWait(shortTimeout, TimeUnit.SECONDS);
        element = driver.findElement(By.xpath(xpathLocator));
        return element.isDisplayed();
    }

    public boolean checkElementIsNotPresentById(String locator) {
        driver.manage().timeouts().implicitlyWait(shortTimeout, TimeUnit.SECONDS);
        locator = String.format(locator, appPackageId);
        elements = driver.findElements(By.id(locator));
        driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
        if (elements.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkElementPresentByXpath(String xpathLocator, String attributeValue) {
        driver.manage().timeouts().implicitlyWait(shortTimeout, TimeUnit.SECONDS);
        xpathLocator = String.format(xpathLocator, attributeValue);
        elements = driver.findElements(By.xpath(xpathLocator));
        driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
        if (elements.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkElementPresentByXpath(String xpathLocator){
        driver.manage().timeouts().implicitlyWait(shortTimeout, TimeUnit.SECONDS);
        elements = driver.findElements(By.xpath(xpathLocator));
        driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
        if(elements.size()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkElementDisplayedById(AndroidDriver driver, String locator, int time) {
        try {
            By elementId = By.id(locator);
            WebDriverWait wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.visibilityOfElementLocated(elementId));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementDisplayedById(String locator) {
        locator = String.format(locator, appPackageId);
        element = driver.findElementById(locator);
        return element.isDisplayed();
    }

    public boolean isElementTextContainsById(String locator, String expectedText) {
        locator = String.format(locator, appPackageId);
        element = driver.findElement(By.id(locator));
        String actualMsg = element.getText();
        return actualMsg.contains(expectedText);
    }

    public void checkBannerAndClose() {
        boolean bannerDisplay = checkElementPresentById(abstractUI.BANNER);
        if (bannerDisplay == true) {
            String locator = String.format(abstractUI.BANNER_CLOSE_BUTTON, appPackageId);
            element = driver.findElement(By.id(locator));
            element.click();
        }
    }

    public void actionMove(int xmoveFrom, int ymoveFrom, int xmoveTo, int ymoveTo) {
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(xmoveFrom, ymoveFrom));
        action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)));
        action.moveTo(PointOption.point(xmoveTo, ymoveTo));
        action.release();
        action.perform();
        sleepInSecond(1);
    }

    public void hideKeyBoard() {
        driver.hideKeyboard();
    }

    public void doubleTapToElement(String locator) {
        locator = String.format(locator, appPackageId);
        element = driver.findElement(By.id(locator));
        TouchActions action = new TouchActions(driver);
        action.doubleTap(element);
        action.perform();
    }

    public void waitToLoadData(String locator) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        element = driver.findElement(By.id(locator));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickToDynamicButton(String textOfButton) {
        clickToElementByXpath(AbstractPageUI.DYNAIMIC_BUTTON, textOfButton);
    }

    public void takeScreenshot(String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("C:/Users/tam.nguyen/Desktop/Screenshot/Appium" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

