package org.example.model;

import lombok.*;

@Getter
@Setter
@Builder
public class Usuario {

    private String papel;
    private Cartao cartaoBanco;

    public String getNrContabancaria() {
        if(this.cartaoBanco != null) {
            return this.cartaoBanco.getNrContaBancaria();
        } else {return null;}
    }

    public String getAgencia() {
        if(this.cartaoBanco != null) {
            return this.cartaoBanco.getAgencia();
        } else {return null;}
    }

}
