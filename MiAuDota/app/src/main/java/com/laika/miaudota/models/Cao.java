package com.laika.miaudota.models;

public class Cao extends Animal{

    private String porte;

    public Cao(){}

    public Cao(String porte,String nome, String sexo, String pelagem, String descricao, int idade, double peso,
               boolean vermifugado, boolean vacinado, String endereco, String foto_url) {
        super(nome,sexo,pelagem,descricao,idade,peso,vermifugado,vacinado, endereco, foto_url);
        this.porte = porte;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }
}
