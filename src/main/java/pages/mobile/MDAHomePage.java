package pages.mobile;

import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebElement;

public class MDAHomePage extends BasePage {

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/menuIV")
    @iOSXCUITFindBy(xpath = "")
    WebElement menuHamburguer;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Login Menu Item']")
    @iOSXCUITFindBy(xpath = "")
    WebElement subLogIn;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/nameET")
    @iOSXCUITFindBy(xpath = "")
    WebElement inputUsername;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/passwordET")
    @iOSXCUITFindBy(xpath = "")
    WebElement inputPassword;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/loginBtn")
    @iOSXCUITFindBy(xpath = "")
    WebElement buttonLogin;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/username1TV")
    @iOSXCUITFindBy(xpath = "")
    WebElement linkUsuario1;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/username2TV")
    @iOSXCUITFindBy(xpath = "")
    WebElement linkUsuario2;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/username3TV")
    @iOSXCUITFindBy(xpath = "")
    WebElement linkUsuario3;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Logout Menu Item']")
    @iOSXCUITFindBy(xpath = "")
    WebElement subLogout;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.saucelabs.mydemoapp.android:id/itemTV' and @text='Catalog']")
    @iOSXCUITFindBy(xpath = "")
    WebElement subCatalog;

    public void confirmarTelaPrincipal() {
        waitForElement(menuHamburguer);
    }

    public void abrirMenuHamburguer() {
        waitForElement(menuHamburguer).click();
        waitForElement(subCatalog);
    }

    public void abrirSubLogin() {
        waitForElement(subLogIn).click();
        waitForElement(inputUsername);
        waitForElement(inputPassword);
        waitForElement(buttonLogin);
        waitForElement(linkUsuario1);
        waitForElement(linkUsuario2);
        waitForElement(linkUsuario3);
    }

    public void preencherLoginComAutocomplete() {
        waitForElement(linkUsuario1).click();
        if (!waitForElement(inputUsername).getText().contains("@")) {
            throw new InvalidElementStateException("O usuario não foi autopreenchido");
        }

        if (!waitForElement(inputPassword).getText().contains("•")) {
            throw new InvalidElementStateException("A senha não foi autopreenchido");
        }
    }

    public void clicarLogin() {
        waitForElement(buttonLogin).click();
    }

    public void confirmarLogout() {
        waitForElement(subLogout);
        waitForElement(subCatalog).click();
    }
}
