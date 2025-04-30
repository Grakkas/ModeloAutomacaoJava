package web.steps;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;
import org.junit.jupiter.api.Assertions;
import pages.web.KabumBuscaPage;
import pages.web.KabumCarrinhoPage;
import util.ScenarioContext;

public class KabumBuscaSteps {

    KabumBuscaPage kabumBuscaPage;
    KabumCarrinhoPage kabumCarrinhoPage;

    @Quando("buscar o produto {string} adicionando o mesmo no carrinho")
    public void buscarOProdutoAdicionandoOMesmoNoCarrinho(String produto) {
        kabumBuscaPage = new KabumBuscaPage();
        Assertions.assertTrue(kabumBuscaPage.buscarProduto(produto));
        ScenarioContext.addScreenshot("Busca do produto à ser adicionado no carrinho", false);

        Assertions.assertTrue(kabumBuscaPage.adicionarProdutoNoCarrinho(produto));
        ScenarioContext.addScreenshot("Confirmação da adição do produto no carrinho", false);
    }

    @E("clicar em agora nao, para seguir ao carrinho")
    public void clicarEmAgoraNaoParaSeguirAoCarrinho() {
        Assertions.assertTrue(kabumBuscaPage.irParaCarrinho());
        kabumCarrinhoPage = new KabumCarrinhoPage();

        Assertions.assertTrue(kabumCarrinhoPage.confirmarTelaCarrinho());
        ScenarioContext.addScreenshot("Confirmação do redirecionamento à tela de carrinho", false);
    }
}
