package com.laika.miaudota.comunicacao;

import com.laika.miaudota.models.Animal;

import java.util.ArrayList;
import java.util.List;

public interface IComunicacao {

    void cadastrar(Animal animal,final ICallback callback);
    void listar(final ICallback callback);
    void deletar(int id, final ICallback callback);

}
