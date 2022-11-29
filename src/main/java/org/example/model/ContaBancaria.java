package org.example.model;

import lombok.*;
import org.example.interfaces.IContaBancaria;
import org.example.repository.BD;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ContaBancaria implements IContaBancaria {

    @NonNull
    private String nrContaBancaria;
    @NonNull
    private String agencia;
    @NonNull
    private String nmTitular;
    @NonNull
    private Double saldo;

    public ContaBancaria(String nrContaBancaria, String agencia) {
        this.nrContaBancaria = nrContaBancaria;
        this.agencia = agencia;
        ContaBancaria obj = BD.getContaBancaria(nrContaBancaria,agencia);
        this.nmTitular = obj.getNmTitular();
        this.saldo = obj.getSaldo();
    }

    @Override
    public Double getSaldo(Usuario usuario) {
        return this.saldo;
    }

    @Override
    public String getNmTitular() {
        return this.nmTitular;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof ContaBancaria)) {
            return false;
        }
        return this.nrContaBancaria.equals(((ContaBancaria) obj).getNrContaBancaria()) &&
                this.agencia.equals(((ContaBancaria) obj).getAgencia());
    }
}
