package steps;

import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;
import org.junit.jupiter.api.Assertions;
import pages.BuscaPage;
import pages.CarrinhoPage;
import util.ScenarioContext;

public class KabumBuscaSteps {

    BuscaPage buscaPage;
    CarrinhoPage carrinhoPage;

    @Quando("buscar o produto {string} adicionando o mesmo no carrinho")
    public void buscarOProdutoAdicionandoOMesmoNoCarrinho(String produto) {
        buscaPage = new BuscaPage();
        Assertions.assertTrue(buscaPage.buscarProduto(produto));
        ScenarioContext.addScreenshot("Busca do produto à ser adicionado no carrinho", false);

        Assertions.assertTrue(buscaPage.adicionarProdutoNoCarrinho(produto));
        ScenarioContext.addScreenshot("Confirmação da adição do produto no carrinho", false);
    }

    @E("clicar em agora nao, para seguir ao carrinho")
    public void clicarEmAgoraNaoParaSeguirAoCarrinho() {
        Assertions.assertTrue(buscaPage.irParaCarrinho());
        carrinhoPage = new CarrinhoPage();

        Assertions.assertTrue(carrinhoPage.confirmarTelaCarrinho());
        ScenarioContext.addScreenshot("Confirmação do redirecionamento à tela de carrinho", false);
    }
}
