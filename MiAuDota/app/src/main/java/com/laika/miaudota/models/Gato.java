package com.laika.miaudota.models;

// Classe Gato herda atributos e métodos da Classe Abstrata Animal

public class Gato extends Animal{

    public Gato(){}

    public Gato(String nome, String sexo, String pelagem, String descricao, int idade, double peso,
                boolean vermifugado, boolean vacinado, String endereco, String fotoUrl) {

        super(nome, sexo, pelagem, descricao, idade, peso, vermifugado, vacinado, endereco, fotoUrl);

    }

}