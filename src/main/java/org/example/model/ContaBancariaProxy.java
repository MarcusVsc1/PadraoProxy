package org.example.model;

import org.example.interfaces.IContaBancaria;

public class ContaBancariaProxy implements IContaBancaria {

    private ContaBancaria contaBancaria;

    private String nrContaBancaria;

    private String agencia;

    public ContaBancariaProxy(String nrContaBancaria, String agencia) {
        this.nrContaBancaria = nrContaBancaria;
        this.agencia = agencia;
    }

    @Override
    public Double getSaldo(Usuario usuario) {
        if(this.contaBancaria==null) {
            this.contaBancaria = new ContaBancaria(this.nrContaBancaria, this.agencia);
        }
        Boolean temAcesso = usuario.getPapel().equals("Bancário") || validarAcessoViaCartao(usuario);
        if(temAcesso) {return this.contaBancaria.getSaldo(usuario);}
        return null;
    }

    private boolean validarAcessoViaCartao(Usuario usuario) {
        if(!usuario.getPapel().equals("Usuário")) {
            throw new IllegalArgumentException("Usuário de role desconhecida");
        }
        if(!(usuario.getNrContabancaria().equals(this.nrContaBancaria) && usuario.getAgencia().equals(this.agencia))) {
            throw new IllegalArgumentException("Usuário sem acesso a esta conta");
        }
        return true;

    }

    @Override
    public String getNmTitular() {
        if(this.contaBancaria==null) {
            this.contaBancaria = new ContaBancaria(this.nrContaBancaria, this.agencia);
        }
        return this.contaBancaria.getNmTitular();
    }
}
