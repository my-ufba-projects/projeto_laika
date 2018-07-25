package com.laika.miaudota.models;

// Classe Abstrata Animal, sendo especializada pelas classes Cao e Gato

public abstract class Animal {

    private String nome;
    private String sexo;
    private String pelagem;
    private String descricao;
    private String endereco;
    private String fotoUrl;
    private int id;
    private int idade;
    private double peso;

    private boolean vermifugado;
    private boolean vacinado;

    public Animal(){}

    public Animal(String nome, String sexo, String pelagem, String descricao, int idade, double peso,
                  boolean vermifugado, boolean vacinado, String endereco, String fotoUrl) {

        this.nome = nome;
        this.sexo = sexo;
        this.pelagem = pelagem;
        this.descricao = descricao;
        this.idade = idade;
        this.peso = peso;
        this.vermifugado = vermifugado;
        this.vacinado = vacinado;
        this.endereco = endereco;
        this.fotoUrl = fotoUrl;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPelagem() {
        return pelagem;
    }

    public void setPelagem(String pelagem) {
        this.pelagem = pelagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public boolean isVermifugado() {
        return vermifugado;
    }

    public void setVermifugado(boolean vermifugado) {
        this.vermifugado = vermifugado;
    }

    public boolean isVacinado() {
        return vacinado;
    }

    public void setVacinado(boolean vacinado) {
        this.vacinado = vacinado;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
