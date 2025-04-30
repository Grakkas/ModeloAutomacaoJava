package web.steps;

import io.cucumber.java.pt.Dado;
import org.junit.jupiter.api.Assertions;
import pages.web.KabumCadastroPage;
import pages.web.KabumHomePage;
import util.ScenarioContext;

public class KabumHomeSteps {

    KabumHomePage kabumHomePage;
    KabumCadastroPage kabumCadastroPage;
    String codigo;

    @Dado("que estou na tela de cadastro")
    public void que_estou_na_tela_de_cadastro() {
        kabumHomePage = new KabumHomePage();
        Assertions.assertTrue(kabumHomePage.irParaCadastro(), "Não foi possível confirmar o redirecionamento do usuário para a tela de cadastro");
        ScenarioContext.addScreenshot("Confirmacao de redirecionamento para a pagina de cadastro", false);
    }

    @Dado("que estou autenticado como novo cliente")
    public void queEstouAutenticadoComoNovoCliente() {
        kabumHomePage = new KabumHomePage();
        Assertions.assertTrue(kabumHomePage.irParaCadastro(), "Não foi possível confirmar o redirecionamento do usuário para a tela de cadastro");
        ScenarioContext.addScreenshot("Confirmacao de redirecionamento para a pagina de cadastro", false);

        kabumCadastroPage = new KabumCadastroPage();
        Assertions.assertTrue(kabumCadastroPage.preencherFormularioPessoaFisica());
        ScenarioContext.addScreenshot("Preenchimento dos dados de pessoa fisica", true);

        Assertions.assertTrue(kabumCadastroPage.continuarCadastroAdicionarEndereco());
        ScenarioContext.addScreenshot("Redirecionamento para a segunda etapa do formulário - adicionar endereco", true);

        Assertions.assertTrue(kabumCadastroPage.preencherFormularioAdicionarEndereco());
        ScenarioContext.addScreenshot("Preenchimento da segunda etapa do formulário - adicionar endereco", true);

        Assertions.assertTrue(kabumCadastroPage.concluirCadastroAdicionarEndereco());
        ScenarioContext.addScreenshot("Visualização da mensagem de solicitação do código via email ou sms", true);

        Assertions.assertTrue(kabumCadastroPage.solicitarCodigoViaEmail());
        ScenarioContext.addScreenshot("Visualização da página de digitação do código", false);

        Assertions.assertTrue(kabumCadastroPage.confirmKabumEmail());
        ScenarioContext.addScreenshot("Visualização da página do email recebido", false);

        codigo = kabumCadastroPage.openKabumEmail();
        ScenarioContext.addScreenshot("Visualização do email recebido", false);

        Assertions.assertTrue(kabumCadastroPage.backToKabum());
        Assertions.assertTrue(kabumCadastroPage.preencherCodigoConfirmacao(codigo));
        ScenarioContext.addScreenshot("Preenchimento do código de confirmação correto", false);

        Assertions.assertTrue(kabumCadastroPage.enviarCodigoConfirmacao());
        Assertions.assertTrue(kabumCadastroPage.confirmarMensagemCodigoConfirmacaoCorreto());
        ScenarioContext.addScreenshot("Mensagem de sucesso do cadastro do cliente", false);

        Assertions.assertTrue(kabumCadastroPage.confirmarRedirecionamentoCadastro());
        ScenarioContext.addScreenshot("Confirmação do redirecionamento com o usuário autenticado", false);
    }


}