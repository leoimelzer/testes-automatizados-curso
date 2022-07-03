package br.com.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private LoginPage paginaDeLogin;

    @BeforeEach
    public void beforeEach(){
        paginaDeLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach(){
        paginaDeLogin.fechar();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos(){
        paginaDeLogin.preencheFormularioDeLogin("fulano","pass");
        paginaDeLogin.efetuarLogin();

        Assert.assertFalse(paginaDeLogin.isPaginaDeLogin());
        Assert.assertEquals("fulano", paginaDeLogin.getNomeDeUsuarioLogado());
    }

    @Test
    public void naoDeveriaEfetuarLoginComDadosInvalidos(){
        paginaDeLogin.preencheFormularioDeLogin("invalido","123123");
        paginaDeLogin.efetuarLogin();

        Assert.assertTrue(paginaDeLogin.isPaginaDeLoginComDadosInvalidos());
        Assert.assertNull(paginaDeLogin.getNomeDeUsuarioLogado());
        Assert.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos"));
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado(){
        paginaDeLogin.navegaParaPaginaDeLances();

        Assert.assertTrue(paginaDeLogin.isPaginaDeLogin());
        Assert.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));

    }
}
