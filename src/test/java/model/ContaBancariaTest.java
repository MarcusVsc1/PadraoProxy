package model;

import org.example.model.Cartao;
import org.example.model.ContaBancaria;
import org.example.model.ContaBancariaProxy;
import org.example.model.Usuario;
import org.example.repository.BD;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ContaBancariaTest {

    @Before
    public void setUp() {
        BD.addContaBancaria(new ContaBancaria("12345-X","9091","Mr. Gawker",4500D));
        BD.addContaBancaria(new ContaBancaria("76543-9","1234","Walter White",75000000D));
    }

    @Test
    public void getNmTitularTest() {
        ContaBancariaProxy contaBancaria = new ContaBancariaProxy("12345-X","9091");
        assertEquals("Mr. Gawker",contaBancaria.getNmTitular());
    }

    @Test
    public void getSaldoRoleIndevidaTest() {
        try {
            Usuario usuario = Usuario.builder()
                    .papel("Agiota")
                    .build();
            ContaBancariaProxy contaBancaria = new ContaBancariaProxy("12345-X","9091");
            contaBancaria.getSaldo(usuario);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Usuário de role desconhecida",e.getMessage());
        }
    }

    @Test
    public void getSaldoUsuarioSemAcessoTest() {
        try {
            Cartao cartao = Cartao.builder()
                    .nrContaBancaria("76543-9")
                    .agencia("1234")
                    .build();
            Usuario usuario = Usuario.builder()
                    .papel("Usuário")
                    .cartaoBanco(cartao)
                    .build();
            ContaBancariaProxy contaBancaria = new ContaBancariaProxy("12345-X","9091");
            contaBancaria.getSaldo(usuario);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Usuário sem acesso a esta conta",e.getMessage());
        }
    }

    @Test
    public void getSaldoRoleBancarioTest() {
        Usuario usuario = Usuario.builder()
                .papel("Bancário")
                .build();
        ContaBancariaProxy contaBancaria = new ContaBancariaProxy("76543-9","1234");
        assertEquals(75000000,contaBancaria.getSaldo(usuario));
    }


}
