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
import android.widget.Button;
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

import static com.laika.miaudota.outros.Config.*;

public class PerfilActivity extends AppCompatActivity {

    private FloatingActionButton deletar_animal;
    private int id;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        queue = Volley.newRequestQueue(getApplicationContext());
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
        final String porte = getIntent().getExtras().getString("animalPorte");
        String fotoUrl = getIntent().getExtras().getString("animalFoto");
        int idade = Integer.valueOf(getIntent().getExtras().getString("animalIdade"));
        double peso = Double.valueOf(getIntent().getExtras().getString("animalPeso"));
        Boolean vermifugado = Boolean.valueOf(getIntent().getExtras().getString("animalVermifugado"));
        Boolean vacinado = Boolean.valueOf(getIntent().getExtras().getString("animalVacinado"));
        this.id = getIntent().getExtras().getInt("id");
        String vermifugadoString;
        String vacinadoString;

        //Tratamento do atributo vermifugado
        if (vermifugado)
            vermifugadoString = SIM;
        else
            vermifugadoString = NAO;

        //Tratamento do atributo vacinado
        if (vacinado)
            vacinadoString = SIM;
        else
            vacinadoString = NAO;

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
        tvSexo.setText(SEXO + ESPACO + sexo);
        tvPelagem.setText(PELAGEM + ESPACO + pelagem);
        tvDescricao.setText(descricao);
        tvEndereco.setText(ENDERECO + ESPACO + endereco);
        tvIdade.setText(IDADE + ESPACO + String.valueOf(idade) + ESPACO + ANOS);
        tvPeso.setText(PESO + ESPACO + String.valueOf(peso) + ESPACO + KG);
        tvVermifugado.setText(VERMIFUGADO + ESPACO + vermifugadoString);
        tvVacinado.setText(VACINADO + ESPACO + vacinadoString);
        if(porte!=null)
            tvPorte.setText(PORTE + ESPACO + porte);
        else
            tvPorte.setText(BLANK);

        collapsingToolbarLayout.setTitle(nome);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        //Definindo a imagem
        Glide.with(this).load(fotoUrl).apply(requestOptions).into(ivFoto);

       deletar_animal = (FloatingActionButton) findViewById(R.id.deletar_animal);

        deletar_animal.setOnClickListener(new View.OnClickListener() {
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
                Toast.makeText(this, PAGINA_EM_DESENVOLVIMENTO, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
