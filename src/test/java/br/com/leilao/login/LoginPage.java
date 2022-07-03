package br.com.leilao.login;

import br.com.leilao.PageObject;
import br.com.leilao.leiloes.LeiloesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class LoginPage extends PageObject {

    private static final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage(){
        super(null);
        driver.get(URL_LOGIN);
    }

    public void preencheFormularioDeLogin(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public LeiloesPage efetuarLogin() {
        driver.findElement(By.id("submit")).click();
        return new LeiloesPage(driver);
    }

    public boolean isPaginaDeLogin() {
        return driver.getCurrentUrl().equals(URL_LOGIN);
    }

    public String getNomeDeUsuarioLogado() {
        try{
            return driver.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e){
            return null;
        }
    }

    public void navegaParaPaginaDeLances() {
        driver.get("http://localhost:8080/leiloes/2");
    }

    public boolean contemTexto(String text) {
        return driver.getPageSource().contains(text);
    }

    public boolean isPaginaDeLoginComDadosInvalidos() {
        return driver.getCurrentUrl().equals(URL_LOGIN + "?error");
    }
}
