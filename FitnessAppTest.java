package house;
////thi is for fitnesss app
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class FitnessAppTest {
    
    public static void main(String[] args) throws MalformedURLException {
        
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Xiaomi M2006C3LII");
        caps.setCapability("appPackage", "com.example.healthfitnesstrackerapp");
        caps.setCapability("appActivity", "com.example.healthfitnesstrackerapp.SplashAcitivy"); 
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        long startTime = System.currentTimeMillis(); 

   
        WebElement logo = driver.findElement(By.id("com.example.healthfitnesstrackerapp:id/splashlogo"));
        if (logo.isDisplayed()) {
            System.out.println("Logo is visible.");
        } else {
            System.out.println("Logo not showing.");
        }

        
        while (true) {
            try {
                logo.isDisplayed(); 
            } catch (Exception e) {
                break; 
            }
            
            if ((System.currentTimeMillis() - startTime) >= 3000) {
                break; 
            }
        }

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Splash screen was displayed for: " + (duration / 1000.0) + " seconds");

        WebElement onboardingScreen = driver.findElement(By.id("com.example.healthfitnesstrackerapp:id/logogyn"));
        if (onboardingScreen.isDisplayed()) {
            System.out.println("Onboarding screen is displayed successfully.");
        } else {
            System.out.println("Onboarding screen is not visible.");
        }

        ////////////////////main activity
        for (int i = 0; i < 3; i++) {
            swipeLeft(driver);
            System.out.println("Swiped to slide " + (i + 2));
        }
        
        // Verify navigation to dashboard after completing all slides
        checkDashboardNavigation(driver, "after completing all slides");
        
        WebElement skipButton = driver.findElement(By.id("com.example.healthfitnesstrackerapp:id/btnSkip"));
        skipButton.click();
        System.out.println("Skip button clicked.");
        checkDashboardNavigation(driver, "using the Skip button");
        driver.quit();
    }
    
    private static void swipeLeft(AppiumDriver driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), width * 3 / 4, height));
        swipe.addAction(finger.createPointerDown(0));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), width / 4, height));
        swipe.addAction(finger.createPointerUp(0));

        driver.perform(Arrays.asList(swipe));
    }

    private static void checkDashboardNavigation(AppiumDriver driver, String method) {
        System.out.println(isElementPresent(driver, "com.example.healthfitnesstrackerapp:id/tvSteps") 
                ? "Successfully navigated to the dashboard " + method + "." 
                : "Navigation to dashboard failed " + method + ".");
    }

    private static boolean isElementPresent(AppiumDriver driver, String elementId) {
        try {
            return driver.findElement(By.id(elementId)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private static void waitForElementToDisappear(AppiumDriver driver, String elementId) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 5000) { // Wait up to 5 seconds
            if (!isElementPresent(driver, elementId)) {
                return;
            }
        }
    }
}
