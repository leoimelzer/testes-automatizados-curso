package br.com.alura.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {

    private final WebDriver driver;
    private static final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL_LOGIN);
    }

    public void fechar() {
        driver.close();
    }

    public void preencheFormularioDeLogin(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void efetuaLogin() {
        driver.findElement(By.id("submit")).click();
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
