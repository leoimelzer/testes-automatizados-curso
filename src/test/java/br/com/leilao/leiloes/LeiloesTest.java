package br.com.leilao.leiloes;

import br.com.leilao.login.LoginPage;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeiloesTest {
    private LeiloesPage paginaDeLeiloes;
    private CadastroLeilaoPage paginaDeCadastro;

    @BeforeEach
    public void beforeEach(){
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
        paginaDeLeiloes = paginaDeLogin.efetuarLogin();
        paginaDeCadastro = paginaDeLeiloes.carregarFormulario();

    }

    @AfterEach
    public void afterEach(){
        paginaDeLeiloes.fechar();
    }

    @Test
    public void deveriaCadastrarLeilao(){
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia " + hoje;
        String valor = "500.00";

        paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(nome,valor,hoje);
        Assert.assertTrue(paginaDeLeiloes.isLeilaoCadastro(nome,valor,hoje));
    }

    @Test
    public void deveriaValidarCadastroDeLeilao(){
        paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao("","","");

        Assert.assertFalse(paginaDeCadastro.isPaginaAtual());
        Assert.assertTrue(paginaDeLeiloes.isPaginaAtual());
        Assert.assertTrue(paginaDeCadastro.isMensagensDeValidacaoVisiveis());
    }
}
