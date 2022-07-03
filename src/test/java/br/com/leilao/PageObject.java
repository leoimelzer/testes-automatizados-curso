package br.com.leilao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageObject {
    protected WebDriver driver;

    public PageObject(WebDriver driver){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        if (driver == null){
            this.driver = new ChromeDriver();
        }
        else {
            this.driver = driver;
        }
    }
    public void fechar() {
        driver.close();
    }
}
