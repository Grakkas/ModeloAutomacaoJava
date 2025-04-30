package pages.mobile;

import com.github.javafaker.Faker;
import factory.ConfigFactory;
import factory.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BasePage {

    protected AppiumDriver driver = (AppiumDriver) DriverFactory.getDriver();
    protected JavascriptExecutor js = (JavascriptExecutor) driver;
    protected Actions actions = new Actions(driver);
    protected Faker faker = new Faker(new Locale("pt", "BR"));

    protected List<String> tabs;
    protected List<String> listProdutos = new ArrayList<>();


    public BasePage() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public WebDriverWait globalWait(WebElement element) {
        WebDriverWait globalWait = new WebDriverWait(driver, Duration.ofSeconds(ConfigFactory.getIntProperty("timeout.padrao")));
        globalWait.pollingEvery(Duration.ofMillis(ConfigFactory.getIntProperty("timeout.polling")));
        globalWait.withMessage(String.format("Não foi possível localizar o elemento em %s segundos: " + element, ConfigFactory.getIntProperty("timeout.padrao")));
        return globalWait;
    }

    public WebElement waitForElement(WebElement element) {
        return globalWait(element).until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> waitForElements(WebElement element) {
        return globalWait(element).until(ExpectedConditions.visibilityOfAllElements((element)));
    }

    public WebElement waitForElementTime(WebElement element, Duration duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.pollingEvery(Duration.ofMillis(ConfigFactory.getIntProperty("timeout.polling")));
        wait.withMessage(String.format("Não foi possível localizar o elemento %s no tempo esperado de: %s", element, duration));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isElementFound(WebElement element, Duration duration) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, duration);
            wait.pollingEvery(Duration.ofMillis(ConfigFactory.getIntProperty("timeout.polling")));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(Duration.ofMillis(millis));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}