package factory;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.File;
import java.io.IOException;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static boolean createWebDriver(DriverType driverType) {
        if (getDriver() == null) {
            switch (driverType) {
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized", "--disable-notifications");
                    driver.set(new ChromeDriver(chromeOptions));
                    return true;

                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--start-maximized");
                    firefoxOptions.addPreference("dom.webnotifications.enabled", false);
                    driver.set(new FirefoxDriver(firefoxOptions));
                    return true;

                case EDGE:
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--start-maximized");
                    edgeOptions.addArguments("--disable-notifications");
                    driver.set(new EdgeDriver(edgeOptions));
                    return true;

                case SAFARI:
                    WebDriverManager.safaridriver().setup();
                    SafariOptions safariOptions = new SafariOptions();
                    safariOptions.setUseTechnologyPreview(false);
                    driver.set(new SafariDriver(safariOptions));
                    driver.get().manage().window().maximize();
                    return true;

                case ANDROID:
                    UiAutomator2Options automator2Options = new UiAutomator2Options();
                    automator2Options.autoGrantPermissions();
                    automator2Options.setPlatformName("Android");
                    automator2Options.setAutomationName(ConfigFactory.getProperty("android.device.automation.name"));
                    automator2Options.setDeviceName(ConfigFactory.getProperty("android.device.name"));
                    automator2Options.setApp(new File(ConfigFactory.getProperty("android.device.app")).getAbsolutePath());
                    driver.set(new AndroidDriver(automator2Options));
                    return true;
                default:
                    throw new IllegalArgumentException("Tipo de navegador não suportado: " + driverType);
            }
        }
        return false;
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static boolean closeWebDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
            return true;
        }
        return false;
    }

    public static byte[] takeScreenshot(boolean fullPage) {
        if (fullPage) {
            try {
                return Shutterbug.shootPage(getDriver(), Capture.FULL).getBytes();
            } catch (IOException e) {
                throw new RuntimeException("Não foi possível realizar o print de toda a tela: \n" + e);
            }
        }
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}