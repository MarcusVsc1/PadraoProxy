package org.example.repository;

import org.example.model.ContaBancaria;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BD {

    private static List<ContaBancaria> contas = new ArrayList<>();

    public static void addContaBancaria (ContaBancaria contaBancaria) {
        contas.add(contaBancaria);
        contas = contas.stream().distinct().collect(Collectors.toList());
    }

    public static ContaBancaria getContaBancaria (String nrContaBancaria, String agencia) {
        return contas.stream().filter(contaBancaria ->
                contaBancaria.getNrContaBancaria().equals(nrContaBancaria) &&
                contaBancaria.getAgencia().equals(agencia))
                .findFirst().orElse(null);
    }

}
