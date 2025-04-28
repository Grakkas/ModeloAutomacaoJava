package pages;

import factory.CpfFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;

public class CadastroPage extends UtilPages {

    private final By inputNomeCompleto = By.xpath("//input[@data-testid='complete-name-input']");
    private final By inputCPF = By.xpath("//input[@data-testid='cpf-input']");
    private final By selectGenero = By.id("SelectGender");
    private final By inputDataNascimento = By.xpath("//input[@data-testid='birth-date-input']");
    private final By inputCelular = By.xpath("//input[@data-testid='mobile-number-input']");
    private final By inputEmail = By.xpath("//input[@data-testid='email-input']");
    private final By inputCrieSuaSenha = By.xpath("//input[@data-testid='password-input']");
    private final By inputConfirmeSuaSenha = By.xpath("//input[@data-testid='confirm-password-input']");
    private final By selectOndeVoceConheceKabum = By.id("SelectBox");
    private final By buttonContinuar = By.xpath("//button[@data-testid='button-form-continue']");

    private final By textAdicionarEndereco = By.xpath("//h2[text()='Adicionar endereço']");
    private final By inputCEP = By.xpath("//input[@data-testid='zipcode-input']");
    private final By inputIdentificacao = By.xpath("//input[@data-testid='address-identification-input']");
    private final By inputNumero = By.xpath("//input[@data-testid='address-number-input']");
    private final By inputComplemento = By.xpath("//input[@data-testid='address-complement-input']");
    private final By inputPontoReferencia = By.xpath("//input[@data-testid='address-reference-input']");
    private final By buttonConcluirCadastro = By.xpath("//button[@data-testid='register-submit-button']");

    private final By textAcesseSuaConta = By.xpath("//h1[text()='Acesse sua conta']");
    private final By buttonEnviarCodigoEmail = By.xpath("//button[@data-testid='email-method-button']");
    private final By buttonEnviarCodigoSMS = By.xpath("//button[@data-testid='sms-method-button']");
    private final By txtCodigoEnviadoComSucesso = By.xpath("//p[contains(text(), 'Código de ativação enviado com sucesso.')]");
    private final By inputCodigoDeConfirmacao = By.xpath("//input[@data-testid='mfa-code-confirmation']");
    private final By buttonConfirmarCodigo = By.xpath("//span[text()='CONFIRMAR']");
    private final By textCodigoConfirmacaoInvalido = By.xpath("//p[contains(text(), 'Código de ativação inválido ou expirado.')]");
    private final By textCodigoConfirmacaoValido = By.xpath("//p[contains(text(), 'Usuário ativado com sucesso.')]");
    private final By textAcessoLiberado = By.xpath("//p[contains(text(), 'Agora você já pode continuar a sua navegação e aproveitar todas as nossas ofertas ninjas.')]");
    private final By buttonContinuarAcessoLiberado = By.xpath("//button[contains(text(), 'CONTINUAR')]");
    private final By linkMinhaContaHeader = By.id("linkMinhaContaHeader");


    public CadastroPage() {
        waitForElement(inputNomeCompleto);
        waitForElement(inputCPF);
        waitForElement(inputDataNascimento);
        waitForElement(inputCelular);
        waitForElement(inputEmail);
        waitForElement(inputCrieSuaSenha);
        waitForElement(inputConfirmeSuaSenha);
        waitForElement(buttonContinuar);
    }

    public boolean preencherFormularioPessoaFisica() {
        waitForElement(inputNomeCompleto).sendKeys(String.format("%s %s", faker.name().firstName(), faker.name().lastName() + Keys.TAB));
        waitForElement(inputCPF).sendKeys(CpfFactory.generateCPF() + Keys.TAB);
        waitForElement(selectGenero).click();
        waitForElement(selectGenero).findElement(By.xpath("//span[text()='Homem']")).click();
        waitForElement(inputDataNascimento).click();
        waitForElement(inputDataNascimento).sendKeys(new SimpleDateFormat("dd/MM/yyyy").format(faker.date().birthday()));
        waitForElement(inputCelular).click();
        waitForElement(inputCelular).sendKeys(String.format("%s9%s", faker.number().numberBetween(11, 19), faker.number().digits(8)) + Keys.TAB);
        waitForElement(inputEmail).sendKeys(getNewTempMail());
        waitForElement(inputCrieSuaSenha).sendKeys("SenhaSegura123!@#");
        waitForElement(inputConfirmeSuaSenha).sendKeys("SenhaSegura123!@#");
        waitForElement(selectOndeVoceConheceKabum).click();
        waitForElement(selectOndeVoceConheceKabum).findElement(By.xpath("//span[text()='Google']")).click();
        return true;
    }

    public boolean continuarCadastroAdicionarEndereco() {
        waitForElement(buttonContinuar).click();
        waitForElementTime(textAdicionarEndereco, Duration.ofSeconds(20));
        waitForElement(inputCEP);
        waitForElement(inputIdentificacao);
        waitForElement(inputNumero);
        waitForElement(inputComplemento);
        waitForElement(inputPontoReferencia);
        waitForElement(buttonConcluirCadastro);
        return true;
    }

    public boolean preencherFormularioAdicionarEndereco() {
        scrollToElement(inputCEP).click();
        waitForElement(inputCEP).sendKeys("01018-020" + Keys.TAB);
        sleep(1000);

        waitForElement(inputIdentificacao).sendKeys(faker.animal().name());
        waitForElement(inputNumero).sendKeys(faker.random().nextInt(1, 999).toString());
        waitForElement(inputComplemento).sendKeys(faker.address().secondaryAddress().replace(".", ""));
        return true;
    }

    public boolean concluirCadastroAdicionarEndereco() {
        waitForElement(buttonConcluirCadastro).click();
        waitForElement(textAcesseSuaConta);
        waitForElement(buttonEnviarCodigoEmail);
        waitForElement(buttonEnviarCodigoSMS);
        return true;
    }

    public boolean solicitarCodigoViaEmail() {
        waitForElement(buttonEnviarCodigoEmail).click();
        waitForElement(txtCodigoEnviadoComSucesso);
        waitForElement(inputCodigoDeConfirmacao);
        waitForElement(buttonConfirmarCodigo);
        return true;
    }

    public boolean preencherCodigoConfirmacaoInvalido() {
        forceSetValue(inputCodigoDeConfirmacao, faker.number().digits(6));
        return true;
    }

    public boolean enviarCodigoConfirmacao() {
        waitForElement(buttonConfirmarCodigo).click();
        return true;
    }

    public boolean confirmarMensagemCodigoConfirmacaoInvalido() {
        return isElementFound(textCodigoConfirmacaoInvalido, Duration.ofSeconds(5));
    }

    public boolean preencherCodigoConfirmacao(String codigo) {
        waitForElement(inputCodigoDeConfirmacao).sendKeys(codigo);
        return true;
    }

    public boolean confirmarMensagemCodigoConfirmacaoCorreto() {
        waitForElementTime(textCodigoConfirmacaoValido, Duration.ofSeconds(20));
        waitForElement(textAcessoLiberado);
        waitForElement(buttonContinuarAcessoLiberado);
        return true;
    }

    public boolean confirmarRedirecionamentoCadastro() {
        waitForElement(buttonContinuarAcessoLiberado).click();
        waitForElement(linkMinhaContaHeader);
        return true;
    }
}
