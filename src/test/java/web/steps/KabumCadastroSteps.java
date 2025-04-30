package web.steps;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.jupiter.api.Assertions;
import pages.web.KabumCadastroPage;
import util.ScenarioContext;

public class KabumCadastroSteps {

    KabumCadastroPage kabumCadastroPage;
    String codigo;

    @Quando("realizar o preenchimento do formulario de pessoa fisica, com dados aleatorios")
    public void realizarOPreenchimentoDoFormularioDePessoaFisicaComDadosAleatorios() {
        kabumCadastroPage = new KabumCadastroPage();
        Assertions.assertTrue(kabumCadastroPage.preencherFormularioPessoaFisica());
        ScenarioContext.addScreenshot("Preenchimento dos dados de pessoa fisica", true);
    }

    @E("continuo o cadastro com o preenchimento de um endereco")
    public void continuoOCadastroComOPreenchimentoDeUmEndereco() {

        Assertions.assertTrue(kabumCadastroPage.continuarCadastroAdicionarEndereco());
        ScenarioContext.addScreenshot("Redirecionamento para a segunda etapa do formulário - adicionar enderedo", true);

        Assertions.assertTrue(kabumCadastroPage.preencherFormularioAdicionarEndereco());
        ScenarioContext.addScreenshot("Preenchimento da segunda etapa do formulário - adicionar enderedo", true);
    }

    @Entao("ao clicar em confirmar cadastro, vejo o aviso para acessar o email ou telefone")
    public void aoClicarEmConfirmarCadastroVejoOAvisoParaAcessarOEmailOuTelefone() {
        Assertions.assertTrue(kabumCadastroPage.concluirCadastroAdicionarEndereco());
        ScenarioContext.addScreenshot("Visualização da mensagem de solicitação do codigo via email ou sms", true);
    }

    @Quando("clicar para receber o codigo via email")
    public void clicar_para_receber_o_codigo_via_email() {
        Assertions.assertTrue(kabumCadastroPage.solicitarCodigoViaEmail());
        ScenarioContext.addScreenshot("Visualização da pagina de digitação do código", false);
    }

    @Entao("vejo o email da kabum com o codigo de ativacao")
    public void vejo_o_email_da_kabum_com_o_codigo_de_ativacao() {
        Assertions.assertTrue(kabumCadastroPage.confirmKabumEmail());
        ScenarioContext.addScreenshot("Visualização da pagina do email recebido", false);

        codigo = kabumCadastroPage.openKabumEmail();
        ScenarioContext.addScreenshot("Visualização do email recebido", false);
        Assertions.assertTrue(kabumCadastroPage.backToKabum());
    }

    @Entao("ao preencher o codigo de confirmacao, com um codigo invalido")
    public void ao_preencher_o_codigo_de_confirmacao_com_um_codigo_invalido() {
        Assertions.assertTrue(kabumCadastroPage.preencherCodigoConfirmacaoInvalido());
        ScenarioContext.addScreenshot("Preenchimento do codigo de confirmacao invalido", false);
        Assertions.assertTrue(kabumCadastroPage.enviarCodigoConfirmacao());
    }

    @Entao("vejo a mensagem de codigo invalido ou expirado")
    public void vejo_a_mensagem_de_codigo_invalido_ou_expirado() {
        Assertions.assertTrue(kabumCadastroPage.confirmarMensagemCodigoConfirmacaoInvalido());
    }

    @Entao("ao preencher o codigo de confirmacao, com o codigo correto")
    public void ao_preencher_o_codigo_de_confirmacao_com_o_codigo_correto() {
        Assertions.assertTrue(kabumCadastroPage.preencherCodigoConfirmacao(codigo));
        ScenarioContext.addScreenshot("Preenchimento do codigo de confirmacao correto", false);
        Assertions.assertTrue(kabumCadastroPage.enviarCodigoConfirmacao());
    }

    @Entao("vejo a mensagem de acesso liberado, onde ao clicar em OK sou redirecionado para a home, já autenticado.")
    public void vejo_a_mensagem_de_acesso_liberado_onde_ao_clicar_em_ok_sou_redirecionado_para_a_home_já_autenticado() {
        Assertions.assertTrue(kabumCadastroPage.confirmarMensagemCodigoConfirmacaoCorreto());
        ScenarioContext.addScreenshot("Mensagem de sucesso do cadastro do cliente", false);
        Assertions.assertTrue(kabumCadastroPage.confirmarRedirecionamentoCadastro());
        ScenarioContext.addScreenshot("Confirmação do redirecionamento com o usuario autenticado", false);
    }
}