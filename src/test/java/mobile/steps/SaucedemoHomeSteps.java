package mobile.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import pages.mobile.MDAHomePage;
import util.ScenarioContext;

public class SaucedemoHomeSteps {

    private MDAHomePage myDemoAppHomePage;

    @Dado("que estou na tela principal")
    public void queEstouNaTelaPrincipal() {
        myDemoAppHomePage = new MDAHomePage();
        myDemoAppHomePage.confirmarTelaPrincipal();
        ScenarioContext.addScreenshot("Confirmar que o usuario se encontra na tela home", false);
    }

    @Quando("abrir o menu de hamburguer")
    public void abrirOMenuDeHamburguer() {
        myDemoAppHomePage.abrirMenuHamburguer();
        ScenarioContext.addScreenshot("Confirmar a expansao do menu de hamburguer", false);
    }

    @E("clicar no submenu de login, confirmando o redirecionamento para a tela de login")
    public void clicarNoSubmenuDeLoginConfirmandoORedirecionamentoParaATelaDeLogin() {
        myDemoAppHomePage.abrirSubLogin();
        ScenarioContext.addScreenshot("Redirecionamento apos clicar no submenu Log In", false);
    }

    @Quando("clicar no primeiro link de usuario, confirmo que os campos de usuario e senha foram autopreenchidos")
    public void clicarNoPrimeiroLinkDeUsuarioConfirmoQueOsCamposDeUsuarioESenhaForamAutopreenchidos() {
        myDemoAppHomePage.preencherLoginComAutocomplete();
        ScenarioContext.addScreenshot("Validação dos campos preenchidos automaticamente ao clicar no link", false);
    }

    @E("ao clicar no botao entrar")
    public void aoClicarNoBotaoEntrar() {
        myDemoAppHomePage.clicarLogin();
    }

    @Entao("confirmo que fui autenticado com sucesso")
    public void confirmoQueFuiAutenticadoComSucesso() {
        myDemoAppHomePage.confirmarTelaPrincipal();
        ScenarioContext.addScreenshot("Confirmação do redirecionamento com usuário autenticado", false);

        myDemoAppHomePage.abrirMenuHamburguer();
        myDemoAppHomePage.confirmarLogout();
        ScenarioContext.addScreenshot("Confirmação de alteração no menu logout de Log In para Logout", false);
    }
}
