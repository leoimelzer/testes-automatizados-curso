package br.com.leilao.leiloes;

import br.com.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeiloesPage extends PageObject {
    private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";
    private static final String URL_LEILOES = "http://localhost:8080/leiloes";

    public LeiloesPage(WebDriver driver){
        super(driver);
    }

    public CadastroLeilaoPage carregarFormulario() {
        driver.get(URL_CADASTRO_LEILAO);
        return new CadastroLeilaoPage(driver);
    }

    public boolean isLeilaoCadastro(String nome, String valor, String data) {
        WebElement linhaDaTabela = driver.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
        WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaDataAbertura = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValor = linhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));

        return colunaNome.getText().equals(nome)
                && colunaDataAbertura.getText().equals(data)
                && colunaValor.getText().equals(valor);
    }

    public boolean isPaginaAtual() {
        return driver.getCurrentUrl().equals(URL_LEILOES);
    }
}
