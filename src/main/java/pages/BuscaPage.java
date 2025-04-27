package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class BuscaPage extends UtilPages {

    private final By inputBusqueNoKabum = By.id("input-busca");
    private final By txtResultadosBusca = By.id("listingCount");
    private final By txtProdutoAdicionadoComSucesso = By.xpath("//strong[text()='Produto adicionado no carrinho']");
    private final By buttonAgoraNao = By.xpath("//span[text()='Agora não']");

    public boolean buscarProduto(String produto) {
        waitForElement(inputBusqueNoKabum).sendKeys(produto + Keys.ENTER);
        waitForElement(txtResultadosBusca);
        return true;
    }

    public boolean adicionarProdutoNoCarrinho(String produto) {
        mouseOverElement(By.xpath(String.format("//a[@data-smarthintproductid='%s'] | //img[contains(@title, '%s')]/..", produto, produto)))
                .findElement(By.xpath("./..//button[text()='COMPRAR']")).click();
        waitForElement(txtProdutoAdicionadoComSucesso);
        produtos.add(produto);
        return true;
    }

    public boolean irParaCarrinho() {
        waitForElement(buttonAgoraNao).click();
        return true;
    }

}
