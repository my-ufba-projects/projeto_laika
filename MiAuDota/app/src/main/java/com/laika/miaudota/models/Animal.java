package com.laika.miaudota.models;

public abstract class   Animal {

    private String nome;
    private String sexo;
    private String pelagem;
    private String descricao;
    private String endereco;
    private String foto_url;

    private int idade;
    private double peso;

    private boolean vermifugado;
    private boolean vacinado;

    public Animal(){}

    public Animal(String nome, String sexo, String pelagem, String descricao, int idade, double peso,
                  boolean vermifugado, boolean vacinado, String endereco, String foto_url) {

        this.nome = nome;
        this.sexo = sexo;
        this.pelagem = pelagem;
        this.descricao = descricao;
        this.idade = idade;
        this.peso = peso;
        this.vermifugado = vermifugado;
        this.vacinado = vacinado;
        this.endereco = endereco;
        this.foto_url = foto_url;

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

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }

}
