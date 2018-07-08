package com.laika.miaudota;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.laika.miaudota.R;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
    }

    //Esconde a ActionBar
    getSupportActionBar().hide();

    //Recebe os dados
    private String image_url = getIntent().getExtras().getString("animal_img") ;
    private String nome = getIntent().getExtras().getString(key: "animal_nome");
    private String sexo = getIntent().getExtras().getString(key: "animal_sexo");
    private String pelagem = getIntent().getExtras().getString(key: "animal_pelagem");
    private String descricao = getIntent().getExtras().getString(key: "animal_descricao");
    private String endereco = getIntent().getExtras().getString(key: "animal_endereco");
    private int idade = getIntent().getExtras().getInt(key: "animal_idade");
    private double peso = getIntent().getExtras().getDouble(key: "animal_peso");
    private String vermifugado;
    private String vacinado;
    private String porte;

    //Tratamento do atributo vermifugado
    if(getIntent().getExtras().getBoolean(key: "animal_vermifugado"))
        vermifugado = "Sim";
    else
        vermifugado = "Não";

    //Tratamento do atributo vacinado
    if(getIntent().getExtras().getBoolean(key: "animal_vacinado"))
    vacinado = "Sim";
    else
    vacinado = "Não";

    //Inicializa as views
    CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
    collapsingToolbarLayout.setTitleEnabled(true);

    ImageView img = findViewById(R.id.aa_thumbnail);
    TextView tv_nome = findViewById(R.id.aa_animal_nome);
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
    tv_nome.setText(nome);
    tv_sexo.setText(sexo);
    tv_pelagem.setText(pelagem);
    tv_descricao.setText(descricao);
    tv_endereco.setText(endereco);
    tv_idade.setText(Integer.toString(idade));
    tv_peso.setText(Double.toString(peso));
    tv_vermifugado.setText(vermifugado);
    tv_vacinado.setText(vacinado);
    tv_porte.setText(porte);

    collapsingToolbarLayout.setTitle(name);

    RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    //Definindo a imagem
    Glide.with(this).load(image_url).apply(requestOptions).into(img);

}
