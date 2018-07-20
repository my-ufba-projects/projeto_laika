package com.laika.miaudota.comunicacao;

import com.laika.miaudota.models.Animal;

public class CachorroComunicacao implements Comunicacao {
    @Override
    public boolean cadastrar(Animal animal) {
        return false;
    }

    @Override
    public boolean atualizar(Animal animal) {
        return false;
    }

    @Override
    public boolean deletar(Animal animal) {
        return false;
    }

    @Override
    public Animal listar() {
        return null;
    }
}
