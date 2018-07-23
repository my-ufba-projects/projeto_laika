package com.laika.miaudota.comunicacao;

import com.laika.miaudota.models.Animal;

import java.util.ArrayList;
import java.util.List;

public interface IComunicacao {

    void cadastrar(Animal animal,final ICallback callback);
    ArrayList<Animal> listar(final ICallback callback);
    void deletar(Animal animal);
}
