package steps;

import io.cucumber.java.pt.Dado;
import org.junit.jupiter.api.Assertions;
import pages.CadastroPage;
import pages.HomePage;
import util.ScenarioContext;

public class KabumHomeSteps {

    HomePage homePage;
    CadastroPage cadastroPage;
    String codigo;

    @Dado("que estou na tela de cadastro")
    public void que_estou_na_tela_de_cadastro() {
        homePage = new HomePage();
        Assertions.assertTrue(homePage.irParaCadastro(), "Não foi possível confirmar o redirecionamento do usuário para a tela de cadastro");
        ScenarioContext.addScreenshot("Confirmacao de redirecionamento para a pagina de cadastro", false);
    }

    @Dado("que estou autenticado como novo cliente")
    public void queEstouAutenticadoComoNovoCliente() {
        homePage = new HomePage();
        Assertions.assertTrue(homePage.irParaCadastro(), "Não foi possível confirmar o redirecionamento do usuário para a tela de cadastro");
        ScenarioContext.addScreenshot("Confirmacao de redirecionamento para a pagina de cadastro", false);

        cadastroPage = new CadastroPage();
        Assertions.assertTrue(cadastroPage.preencherFormularioPessoaFisica());
        ScenarioContext.addScreenshot("Preenchimento dos dados de pessoa fisica", true);

        Assertions.assertTrue(cadastroPage.continuarCadastroAdicionarEndereco());
        ScenarioContext.addScreenshot("Redirecionamento para a segunda etapa do formulário - adicionar endereco", true);

        Assertions.assertTrue(cadastroPage.preencherFormularioAdicionarEndereco());
        ScenarioContext.addScreenshot("Preenchimento da segunda etapa do formulário - adicionar endereco", true);

        Assertions.assertTrue(cadastroPage.concluirCadastroAdicionarEndereco());
        ScenarioContext.addScreenshot("Visualização da mensagem de solicitação do código via email ou sms", true);

        Assertions.assertTrue(cadastroPage.solicitarCodigoViaEmail());
        ScenarioContext.addScreenshot("Visualização da página de digitação do código", false);

        Assertions.assertTrue(cadastroPage.confirmKabumEmail());
        ScenarioContext.addScreenshot("Visualização da página do email recebido", false);

        codigo = cadastroPage.openKabumEmail();
        ScenarioContext.addScreenshot("Visualização do email recebido", false);

        Assertions.assertTrue(cadastroPage.backToKabum());
        Assertions.assertTrue(cadastroPage.preencherCodigoConfirmacao(codigo));
        ScenarioContext.addScreenshot("Preenchimento do código de confirmação correto", false);

        Assertions.assertTrue(cadastroPage.enviarCodigoConfirmacao());
        Assertions.assertTrue(cadastroPage.confirmarMensagemCodigoConfirmacaoCorreto());
        ScenarioContext.addScreenshot("Mensagem de sucesso do cadastro do cliente", false);

        Assertions.assertTrue(cadastroPage.confirmarRedirecionamentoCadastro());
        ScenarioContext.addScreenshot("Confirmação do redirecionamento com o usuário autenticado", false);
    }


}