package paginas;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaginaLogin extends PaginaBase {

    @FindBy(id = "user-name")
    @AndroidFindBy(accessibility = "test-Username")
    @iOSXCUITFindBy(accessibility = "test-Username")
    private WebElement inputUsuario;

    @FindBy(id = "password")
    @AndroidFindBy(accessibility = "test-Password")
    @iOSXCUITFindBy(accessibility = "test-Password")
    private WebElement inputSenha;

    @FindBy(id = "login-button")
    @AndroidFindBy(accessibility = "test-LOGIN")
    @iOSXCUITFindBy(accessibility = "test-LOGIN")
    private WebElement btnEntrar;

    @FindBy(xpath = "//h3[text() = 'Epic sadface: Username and password do not match any user in this service']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sorry, this user has been locked out.']")
    @iOSXCUITFindBy(xpath = "//*[@text='Sorry, this user has been locked out.']")
    private WebElement txtUsuarioSenhaInvalida;

    @FindBy(xpath = "//h3[text() = 'Epic sadface: Sorry, this user has been locked out.']")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Username and password do not match any user in this service.']")
    @iOSXCUITFindBy(xpath = "//*[@text='Username and password do not match any user in this service.']")
    private WebElement txtUsuarioBloqueado;

    @FindBy(xpath = "//a[@data-test='shopping-cart-link']")
    @AndroidFindBy(accessibility = "test-Cart")
    @iOSXCUITFindBy(accessibility = "test-Cart")
    private WebElement btnCarrinho;

    public PaginaLogin() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void realizarLogin(String usuario, String senha) {
        preencherElemento(inputUsuario, usuario);
        preencherElemento(inputSenha, senha);
        clicarElemento(btnEntrar);
    }

    public void validarLogin(String validacao) {
        if (validacao.equals("sucesso")) {
            aguardarElemento(btnCarrinho);
        } else if (validacao.contains("invalido")) {
            aguardarElemento(txtUsuarioSenhaInvalida);
        } else if (validacao.contains("bloqueio")) {
            aguardarElemento(txtUsuarioBloqueado);
        }
    }
}