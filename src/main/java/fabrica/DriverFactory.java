package fabrica;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private static RemoteWebDriver driver = null;

    public static void iniciarDriver(DriverType tipoDriver) {
        String url = "https://saucedemo.com";

        switch (tipoDriver) {
            case CHROME:
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.get(url);
                break;
            case SAFARI:
                SafariOptions options2 = new SafariOptions();
                driver = new SafariDriver(options2);
                driver.manage().window().maximize();
                driver.get(url);
                break;
            case ANDROID:
                try {
                    UiAutomator2Options options = new UiAutomator2Options();
                    options.setPlatformName("Android");
                    options.setPlatformVersion("10");
                    options.setDeviceName("Pixel 8 API 29");
                    options.setApp(System.getProperty("user.dir") + "/src/main/resources/apps/Saucedemo_android.apk");
                    options.setAppWaitActivity("com.swaglabsmobileapp.MainActivity");
                    options.autoGrantPermissions();
                    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case IOS:
                try {
                    XCUITestOptions options = new XCUITestOptions();
                    options.setAutomationName("XCUITest");
                    options.setPlatformName("iOS");
                    options.setPlatformVersion("17.5");
                    options.setDeviceName("iPhone 15");
                    options.setUdid("4B03B29D-798A-41C1-9EF6-8EBDC228C048");
                    options.setApp(System.getProperty("user.dir") + "/src/main/resources/apps/Saucedemo_ios.app");
                    driver = new IOSDriver(new URL("http://127.0.0.1:4723/"), options);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    public static void fecharDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static RemoteWebDriver getDriver() {
        return driver;
    }
}
