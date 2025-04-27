package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarrinhoPage extends UtilPages {

    By selectQualEndereco = By.id("address");
    By textCarregando = By.xpath("//span[contains(.,'CARREGANDO')]");
    By buttonIrParaPagamento = By.xpath("//span[text()='Ir para o pagamento']");
    By listOpcoesFrete = By.xpath("//label[contains(@class, 'shippingOptions')]");
    By textFormaDePagamento = By.xpath("//h2[text()='Forma de Pagamento']");
    By buttonVoltar = By.xpath("//span[text()='VOLTAR']");
    By buttonRemoverTodosProdutos = By.id("removerTodosProdutos");
    By textConfirmacaoRemoverProdutos = By.xpath("//p[contains(text(), 'Você tem certeza que deseja remover todos os produtos do carrinho?')]");
    By buttonConfirmacaoRemoverProdutos = By.xpath("//span[text()='Sim']");
    By textCarrinhoVazio = By.xpath("//b[text()='O seu carrinho está vazio.']");


    public boolean confirmarTelaCarrinho() {
        waitForElement(selectQualEndereco);
        waitForElement(buttonIrParaPagamento);
        return true;
    }

    public boolean confirmarProdutos() {
        for (String produto : produtos) {
            waitForElement(By.xpath(String.format("//div[@data-smarthintproductid='%s'] | //a[contains(text(), '%s')]", produto, produto)));
        }
        return true;
    }

    public boolean selecionarEndereco() {
        actions.click(waitForElement(By.id("address"))).pause(500).keyDown(Keys.ARROW_DOWN).keyUp(Keys.ARROW_DOWN).keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
        return true;
    }

    public boolean selecionarFreteMaisBarato() {
        List<BigDecimal> precos = new ArrayList<>();
        for (WebElement opcaoFrete : waitForElements(listOpcoesFrete)) {
            precos.add(new BigDecimal(opcaoFrete.findElement(By.xpath(".//span[contains(text(), 'R$')]")).getText().replaceAll("\\D+", "")));
        }
        waitForElements(listOpcoesFrete).get(precos.indexOf(Collections.min(precos))).click();
        waitForElementTime(buttonIrParaPagamento, Duration.ofSeconds(10));
        return true;
    }

    public boolean seguirParaPagamento() {
        while (isElementFound(textCarregando, Duration.ofSeconds(5))) {
            sleep(500);
        }
        waitForElement(buttonIrParaPagamento).click();
        waitForElement(textFormaDePagamento);
        return true;
    }

    public boolean retornarCarrinho() {
        waitForElement(buttonVoltar).click();
        waitForElementTime(buttonRemoverTodosProdutos, Duration.ofSeconds(15));
        return true;
    }

    public boolean excluirCarrinho() {
        waitForElement(buttonRemoverTodosProdutos).click();
        waitForElement(textConfirmacaoRemoverProdutos);
        return true;
    }

    public boolean confirmarCarrinhoVazio() {
        waitForElement(buttonConfirmacaoRemoverProdutos).click();
        waitForElement(textCarrinhoVazio);
        return true;
    }
}
