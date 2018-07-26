package com.laika.miaudota.activities;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.laika.miaudota.R;
import com.laika.miaudota.comunicacao.CachorroComunicacao;
import com.laika.miaudota.comunicacao.GatoComunicacao;
import com.laika.miaudota.comunicacao.ICallback;
import com.laika.miaudota.comunicacao.IComunicacao;

import java.util.Objects;

import com.laika.miaudota.outros.IConstants;

public class PerfilActivity extends AppCompatActivity {

    private int id;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FloatingActionButton deletarAnimal;
        queue = Volley.newRequestQueue(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //Esconde a ActionBar
        getSupportActionBar().hide();

        //Recebe os dados
        String nome = Objects.requireNonNull(getIntent().getExtras()).getString("animalNome");
        String sexo = getIntent().getExtras().getString("animalSexo");
        String pelagem = getIntent().getExtras().getString("animalPelagem");
        String descricao = getIntent().getExtras().getString("animalDescricao");
        String endereco = getIntent().getExtras().getString("animalEndereco");
        final String porte = getIntent().getExtras().getString("animalPorte");
        String fotoUrl = getIntent().getExtras().getString("animalFoto");
        int idade = Integer.valueOf(getIntent().getExtras().getString("animalIdade"));
        double peso = Double.valueOf(getIntent().getExtras().getString("animalPeso"));
        Boolean vermifugado = Boolean.valueOf(getIntent().getExtras().getString("animalVermifugado"));
        Boolean vacinado = Boolean.valueOf(getIntent().getExtras().getString("animalVacinado"));
        this.id = getIntent().getExtras().getInt("id");

        //Inicializa as views
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tvSexo = findViewById(R.id.perfil_animal_sexo);
        TextView tvPelagem = findViewById(R.id.perfil_animal_pelagem);
        TextView tvDescricao = findViewById(R.id.perfil_animal_descricao);
        TextView tvEndereco = findViewById(R.id.perfil_animal_endereco);
        TextView tvIdade = findViewById(R.id.perfil_animal_idade);
        TextView tvPeso = findViewById(R.id.perfil_animal_peso);
        TextView tvVermifugado = findViewById(R.id.perfil_animal_vermifugado);
        TextView tvVacinado = findViewById(R.id.perfil_animal_vacinado);
        TextView tvPorte = findViewById(R.id.perfil_animal_porte);

        ImageView ivFoto = findViewById(R.id.aa_thumbnail);

        //Setando os atributos da view
        tvSexo.setText(String.format("%s%s", getString(R.string.sexo_question), sexo));
        tvPelagem.setText(String.format("%s%s", getString(R.string.pelagem_question), pelagem));
        tvDescricao.setText(descricao);
        tvEndereco.setText(String.format("%s%s", getString(R.string.endereco_question), endereco));
        tvIdade.setText(String.format("%s%s%s", getString(R.string.idade_question), String.valueOf(idade), getString(R.string.anos_postquestion)));
        tvPeso.setText(String.format("%s%s%s", getString(R.string.peso_question), String.valueOf(peso), getString(R.string.peso_postquestion)));

        if(vermifugado)
            tvVermifugado.setText(getString(R.string.vermifugado_sim));
        else
            tvVermifugado.setText(getString(R.string.vermifugado_nao));

        if(vacinado)
            tvVacinado.setText(getString(R.string.vacinado_sim));
        else
            tvVacinado.setText(getString(R.string.vacinado_nao));

        if(porte!=null)
            tvPorte.setText(String.format("%s%s", getString(R.string.porte_question), porte));
        else
            tvPorte.setText(IConstants.BLANK);

        collapsingToolbarLayout.setTitle(nome);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        //Definindo a imagem
        Glide.with(this).load(fotoUrl).apply(requestOptions).into(ivFoto);

       deletarAnimal = findViewById(R.id.deletar_animal);

        deletarAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IComunicacao comm;
                if(porte !=null){
                    //cachorro
                    comm = new CachorroComunicacao(queue);
                    comm.deletar(id, new ICallback() {
                        @Override
                        public void onSucess(Object object) {
                            Toast.makeText(getApplicationContext(), "Cão deletado", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFail(Object object) {
                            Toast.makeText(getApplicationContext(), "Erro!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                }else{
                    comm = new GatoComunicacao(queue);
                    comm.deletar(id, new ICallback() {
                        @Override
                        public void onSucess(Object object) {
                            Toast.makeText(getApplicationContext(), "Gato deletado", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFail(Object object) {
                            Toast.makeText(getApplicationContext(), "Erro!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                }
                //Toast.makeText(getApplicationContext(), "Funciona!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override //Quando algum item do menu for selecionado execute a rotina
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.lista_animaisId: //Se clicar em Listar animais, chama a MainActivity
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.cadastrar_animalId: //Se clicar em Cadastrar animal, chama a CadastroActivity
                Toast.makeText(this, IConstants.PAGINA_EM_DESENVOLVIMENTO, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
