package paginas;

import fabrica.DriverFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaginaBase {

    protected RemoteWebDriver driver = DriverFactory.getDriver();

    public PaginaBase() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    protected WebElement aguardarElemento(WebElement elemento) {
        Wait<WebDriver> driverWait = new WebDriverWait(driver, Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(300)).withMessage("Elemento não localizado em " + 10 + " segundos: \n" + elemento);
        driverWait.until(ExpectedConditions.visibilityOf(elemento));
        return elemento;
    }

    protected void preencherElemento(WebElement elemento, String texto) {
        aguardarElemento(elemento).sendKeys(texto);
    }

    protected void clicarElemento(WebElement elemento) {
        aguardarElemento(elemento).click();
    }

    protected void aguardar(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception ignore) {
            //Ignorando, pois às vezes é necessario pausar alguns segundos (mesmo que seja o processador)
        }
    }
}
