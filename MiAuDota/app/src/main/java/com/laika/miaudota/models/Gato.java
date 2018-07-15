package com.laika.miaudota.models;

public class Gato extends Animal{

    public Gato(){}

    public Gato(String nome, String sexo, String pelagem, String descricao, int idade, double peso,
                boolean vermifugado, boolean vacinado, String endereco, String foto_url) {

        super(nome, sexo, pelagem, descricao, idade, peso, vermifugado, vacinado, endereco, foto_url);

    }

}