package com.laika.miaudota.models;

public class Cao extends Animal{
    //herda da classe animal e especializa com o porte

    private String porte;

    public Cao(){}

    public Cao(String nome, String sexo, String pelagem, String descricao, int idade, double peso,
               boolean vermifugado, boolean vacinado, String endereco, String fotoUrl, String porte) {

        super(nome,sexo,pelagem,descricao,idade,peso,vermifugado,vacinado, endereco, fotoUrl);
        this.porte = porte;

    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }
}