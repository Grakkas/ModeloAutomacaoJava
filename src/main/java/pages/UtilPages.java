package pages;

import com.github.javafaker.Faker;
import factory.ConfigFactory;
import factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UtilPages {

    WebDriver driver = DriverFactory.getDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    Actions actions = new Actions(driver);
    Faker faker = new Faker(new Locale("pt", "BR"));

    List<String> tabs = new ArrayList<>(driver.getWindowHandles());
    List<String> produtos = new ArrayList<>();

    public WebDriverWait globalWait(By element) {
        WebDriverWait globalWait = new WebDriverWait(driver, Duration.ofSeconds(ConfigFactory.getIntProperty("timeout.padrao")));
        globalWait.pollingEvery(Duration.ofMillis(ConfigFactory.getIntProperty("timeout.polling")));
        globalWait.withMessage(String.format("Não foi possível localizar o elemento em %s segundos: " + element, ConfigFactory.getIntProperty("timeout.padrao")));
        return globalWait;
    }

    public WebElement waitForElement(By element) {
        return globalWait(element).until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public List<WebElement> waitForElements(By element) {
        return globalWait(element).until(ExpectedConditions.visibilityOfAllElements(driver.findElements(element)));
    }

    public WebElement mouseOverElement(By element) {
        actions.moveToElement(waitForElement(element)).perform();
        return waitForElement(element);
    }

    public WebElement waitForElementTime(By element, Duration duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.pollingEvery(Duration.ofMillis(ConfigFactory.getIntProperty("timeout.polling")));
        wait.withMessage(String.format("Não foi possível localizar o elemento %s no tempo esperado de: %s", element, duration));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void forceSetValue(By element, String texto) {
        actions.moveToElement(waitForElement(element)).click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).sendKeys(texto).perform();
    }

    public boolean isElementFound(By element, Duration duration) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, duration);
            wait.pollingEvery(Duration.ofMillis(ConfigFactory.getIntProperty("timeout.polling")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
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

    public void openNewTab() {
        js.executeScript("window.open()");
    }

    public WebElement scrollToElement(By element) {
        js.executeScript("const element = arguments[0];" + "const rect = element.getBoundingClientRect();" + "window.scrollBy({ " + "   top: rect.top + window.scrollY - (window.innerHeight / 2), " + "   behavior: 'instant' " + "});", waitForElement(element));
        return waitForElement(element);
    }

    public String getNewTempMail() {
        openNewTab();
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.getLast());
        driver.navigate().to(ConfigFactory.getProperty("temp.email.url"));
        String tempMail = waitForElementTime(By.xpath("//input[@id='email'][contains(@value, '@')]"), Duration.ofSeconds(10)).getDomProperty("value");
        driver.switchTo().window(tabs.get(tabs.size() - 2));
        return tempMail;
    }


    public boolean confirmKabumEmail() {
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.getLast());

        int retry = 0;
        while (!isElementFound(By.xpath("//span[contains(text(), 'Seu código é:')]"), Duration.ofSeconds(3))) {
            waitForElement(By.xpath("//button[@data-qa='refresh-button']")).click();
            retry += 1;
            if (retry > 5) {
                throw new TimeoutException("Não foi possivel confirmar o recebimento do e-mail em 15 segundos");
            }
        }
        return true;
    }

    public String openKabumEmail() {
        waitForElement(By.xpath("//span[contains(text(), 'Seu código é:')]")).click();
        return waitForElement(By.xpath("//p[contains(text(), 'Seu código é:')]")).findElement(By.xpath("//strong")).getText();
    }

    public boolean backToKabum() {
        driver.switchTo().window(tabs.get(tabs.size() - 2));
        return true;
    }
}