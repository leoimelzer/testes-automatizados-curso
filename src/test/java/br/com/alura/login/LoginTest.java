package br.com.alura.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    private WebDriver driver;
    private static final String URL = "http://localhost:8080/login";

    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    }

    @BeforeEach
    public void beforeEach(){
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @AfterEach
    public void afterEach(){
        this.driver.close();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos(){
        driver.findElement(By.id("username")).sendKeys("fulano");
        driver.findElement(By.id("password")).sendKeys("pass");
        driver.findElement(By.id("submit")).click();

        Assert.assertFalse(driver.getCurrentUrl().equals(URL));
        Assert.assertEquals("fulano", driver.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    public void naoDeveriaEfetuarLoginComDadosInvalidos(){
        driver.findElement(By.id("username")).sendKeys("invalido");
        driver.findElement(By.id("password")).sendKeys("123123");
        driver.findElement(By.id("submit")).click();

        Assert.assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/login?error"));
        Assert.assertTrue(driver.getPageSource().contains("Usuário e senha inválidos."));
        Assert.assertThrows(NoSuchElementException.class, () -> driver.findElement(By.id("usuario-logado")));
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado(){
        this.driver.get("http://localhost:8080/leiloes/2");

        Assert.assertTrue(driver.getCurrentUrl().equals(URL));
        Assert.assertFalse(driver.getPageSource().contains("Dados do Leilão"));

    }
}
