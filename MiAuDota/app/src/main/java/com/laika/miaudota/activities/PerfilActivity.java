package com.laika.miaudota.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.laika.miaudota.R;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);


        //Esconde a ActionBar
        getSupportActionBar().hide();

        //Recebe os dados
        String nome = getIntent().getExtras().getString("animal_nome");
        String sexo = getIntent().getExtras().getString("animal_sexo");
        String pelagem = getIntent().getExtras().getString("animal_pelagem");
        String descricao = getIntent().getExtras().getString("animal_descricao");
        String endereco = getIntent().getExtras().getString("animal_endereco");
        String porte = getIntent().getExtras().getString("animal_porte");
        //String foto_url = getIntent().getExtras().getString("animal_foto");
        int idade = Integer.valueOf(getIntent().getExtras().getString("animal_idade"));
        double peso = Double.valueOf(getIntent().getExtras().getString("animal_peso"));
        Boolean vermifugado = Boolean.valueOf(getIntent().getExtras().getString("animal_vermifugado"));
        Boolean vacinado = Boolean.valueOf(getIntent().getExtras().getString("animal_vacinado"));

        String vermifugadoString;
        String vacinadoString;

        //Tratamento do atributo vermifugado
        if (vermifugado)
            vermifugadoString = "Sim";
        else
            vermifugadoString = "Não";

        //Tratamento do atributo vacinado
        if (vacinado)
            vacinadoString = "Sim";
        else
            vacinadoString = "Não";

        //Inicializa as views
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        //ImageView iv_foto = findViewById(R.id.aa_thumbnail);
        //TextView tv_nome = findViewById(R.id.aa_animal_nome);
        TextView tv_sexo = findViewById(R.id.aa_animal_sexo);
        TextView tv_pelagem = findViewById(R.id.aa_animal_pelagem);
        TextView tv_descricao = findViewById(R.id.aa_animal_descricao);
        TextView tv_endereco = findViewById(R.id.aa_animal_endereco);
        TextView tv_idade = findViewById(R.id.aa_animal_idade);
        TextView tv_peso = findViewById(R.id.aa_animal_peso);
        TextView tv_vermifugado = findViewById(R.id.aa_animal_vermifugado);
        TextView tv_vacinado = findViewById(R.id.aa_animal_vacinado);
        TextView tv_porte = findViewById(R.id.aa_animal_porte);

        //Setando os atributos da view
        //tv_nome.setText(nome);
        tv_sexo.setText("Sexo: " + sexo);
        tv_pelagem.setText("Pelagem: " + pelagem);
        tv_descricao.setText(descricao);
        tv_endereco.setText("Endereço: " + endereco);
        tv_idade.setText("Idade: " + String.valueOf(idade));
        tv_peso.setText("Peso: " + String.valueOf(peso));
        tv_vermifugado.setText("Vermifugado: " + vermifugadoString);
        tv_vacinado.setText("Vacinado: " + vacinadoString);
        tv_porte.setText("Porte: " + porte);

        collapsingToolbarLayout.setTitle(nome);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        //Definindo a imagem
        //Glide.with(this).load(foto_url).apply(requestOptions).into(iv_foto);


    }
}
