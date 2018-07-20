package com.laika.miaudota.activities;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        String nome = getIntent().getExtras().getString("animalNome");
        String sexo = getIntent().getExtras().getString("animalSexo");
        String pelagem = getIntent().getExtras().getString("animalPelagem");
        String descricao = getIntent().getExtras().getString("animalDescricao");
        String endereco = getIntent().getExtras().getString("animalEndereco");
        String porte = getIntent().getExtras().getString("animalPorte");
        String fotoUrl = getIntent().getExtras().getString("animalFoto");
        int idade = Integer.valueOf(getIntent().getExtras().getString("animalIdade"));
        double peso = Double.valueOf(getIntent().getExtras().getString("animalPeso"));
        Boolean vermifugado = Boolean.valueOf(getIntent().getExtras().getString("animalVermifugado"));
        Boolean vacinado = Boolean.valueOf(getIntent().getExtras().getString("animalVacinado"));

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

        TextView tvSexo = findViewById(R.id.aa_animalSexo);
        TextView tvPelagem = findViewById(R.id.aa_animalPelagem);
        TextView tvDescricao = findViewById(R.id.aa_animalDescricao);
        TextView tvEndereco = findViewById(R.id.aa_animalEndereco);
        TextView tvIdade = findViewById(R.id.aa_animalIdade);
        TextView tvPeso = findViewById(R.id.aa_animalPeso);
        TextView tvVermifugado = findViewById(R.id.aa_animalVermifugado);
        TextView tvVacinado = findViewById(R.id.aa_animalVacinado);
        TextView tvPorte = findViewById(R.id.aa_animalPorte);

        ImageView ivFoto = findViewById(R.id.aa_thumbnail);

        //Setando os atributos da view
        tvSexo.setText("Sexo: " + sexo);
        tvPelagem.setText("Pelagem: " + pelagem);
        tvDescricao.setText(descricao);
        tvEndereco.setText("Endereço: " + endereco);
        tvIdade.setText("Idade: " + String.valueOf(idade) + " ano(s)");
        tvPeso.setText("Peso: " + String.valueOf(peso) + " kg(s)");
        tvVermifugado.setText("Vermifugado(a): " + vermifugadoString);
        tvVacinado.setText("Vacinado(a): " + vacinadoString);
        if(porte!=null)
            tvPorte.setText("Porte: " + porte);
        else
            tvPorte.setText("");

        collapsingToolbarLayout.setTitle(nome);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        //Definindo a imagem
        Glide.with(this).load(fotoUrl).apply(requestOptions).into(ivFoto);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.lista_animaisId:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.cadastrar_animalId:
                Toast.makeText(this, "Página de cadastro em desenvolvimento.", Toast.LENGTH_SHORT);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
