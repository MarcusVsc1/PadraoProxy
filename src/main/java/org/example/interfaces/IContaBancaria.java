package org.example.interfaces;

import org.example.model.Usuario;

public interface IContaBancaria {

    public Double getSaldo(Usuario usuario);

    public String getNmTitular();

}