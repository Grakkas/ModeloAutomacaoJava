package web.steps;

import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.jupiter.api.Assertions;
import pages.web.KabumCarrinhoPage;
import util.ScenarioContext;

public class KabumCarrinhoSteps {

    KabumCarrinhoPage kabumCarrinhoPage = new KabumCarrinhoPage();

    @Entao("confirmo que todos os produtos estao no carrinho")
    public void confirmoQueTodosOsProdutosEstaoNoCarrinho() {
        Assertions.assertTrue(kabumCarrinhoPage.confirmarProdutos());
    }

    @Quando("selecionar o meu endereco, assim como o frete mais barato")
    public void selecionarOMeuEnderecoAssimComoOFreteMaisBarato() {
        Assertions.assertTrue(kabumCarrinhoPage.selecionarEndereco());
        Assertions.assertTrue(kabumCarrinhoPage.selecionarFreteMaisBarato());
        ScenarioContext.addScreenshot("Selecão do endereco e opcao de frete mais barato", false);
    }

    @Entao("confirmo que ao clicar em ir para pagamento, fui redirecionado para preencher as informacoes de pagamento")
    public void confirmoQueAoClicarEmIrParaPagamentoFuiRedirecionadoParaPreencherAsInformacoesDePagamento() {
        Assertions.assertTrue(kabumCarrinhoPage.seguirParaPagamento());
        ScenarioContext.addScreenshot("Tela de selecao de pagamento", false);

        Assertions.assertTrue(kabumCarrinhoPage.retornarCarrinho());
        ScenarioContext.addScreenshot("Retorno a tela do carrinho para a exclusao dos itens", false);

        Assertions.assertTrue(kabumCarrinhoPage.excluirCarrinho());
        ScenarioContext.addScreenshot("Modal de confirmacao de exclusão dos itens", false);

        Assertions.assertTrue(kabumCarrinhoPage.confirmarCarrinhoVazio());
        ScenarioContext.addScreenshot("Confirmação da exclusão dos produtos, desculpa Kabum", false);
    }
}