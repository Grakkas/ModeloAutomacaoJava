package pages.web;

import org.openqa.selenium.By;

import java.time.Duration;

public class KabumHomePage extends BasePage {

    private final By imgProduto = By.className("imageCard");
    private final By buttonFecharBanner = By.xpath("//button[@aria-label='Fechar banner']");
    private final By linkCadastro = By.id("linkCadastroHeader");
    private final By txtCriarConta = By.xpath("//h2[text()='Criar conta']");
    private final By radioPessoaFisica = By.xpath("//p[text()='Pessoa Física']");

    public KabumHomePage() {
        waitForElement(imgProduto);
        if (isElementFound(buttonFecharBanner, Duration.ofSeconds(3))) {
            waitForElement(buttonFecharBanner).click();
        }
    }

    public boolean irParaCadastro() {
        waitForElement(linkCadastro).click();
        waitForElement(txtCriarConta);
        waitForElement(radioPessoaFisica);
        return true;
    }
}
