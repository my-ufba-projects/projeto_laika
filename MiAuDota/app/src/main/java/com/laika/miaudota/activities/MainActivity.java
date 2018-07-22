package com.laika.miaudota.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonArrayRequest;
import com.laika.miaudota.adapters.RecyclerViewAdapter;
import com.laika.miaudota.R;
import com.laika.miaudota.models.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private final String JSON_URL = "https://projeto-laika2.herokuapp.com/animais.json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private ArrayList<Animal> listaAnimal;
    private RecyclerView recyclerView;
    private Button btn_criar;

    //Declaração do adaptador para o filtro da busca
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_criar = (Button) findViewById(R.id.btn_cadastrar);
        btn_criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);

            }
        });
        listaAnimal = new ArrayList<Animal>();
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();

        //Atribuição do adaptador
        adapter = new RecyclerViewAdapter(this, listaAnimal);
        recyclerView.setAdapter(adapter);
    }



    private void jsonrequest(){

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>(){

            @Override
            public void onResponse(JSONArray response){

                JSONObject jsonObject = null;
                System.out.println("Teste");
                for(int i=0; i<response.length(); i++){
                    try{
                        jsonObject = response.getJSONObject(i);
                        if((jsonObject.getString("porte").equalsIgnoreCase("null"))) {
                            Gato gato  = new Gato();
                            gato.setNome(jsonObject.getString("nome"));
                            gato.setSexo(jsonObject.getString("sexo"));
                            gato.setPelagem(jsonObject.getString("pelagem"));
                            gato.setDescricao(jsonObject.getString("descricao"));
                            gato.setEndereco(jsonObject.getString("endereco"));
                            gato.setFotoUrl(jsonObject.getString("foto_url"));
                            gato.setIdade(jsonObject.getInt("idade"));
                            gato.setPeso(jsonObject.getDouble("peso"));
                            gato.setVermifugado(jsonObject.getBoolean("vermifugado"));
                            gato.setVacinado(jsonObject.getBoolean("vacinado"));
                            listaAnimal.add(gato);
                            System.out.println(gato.getNome());
                        }
                        else{
                            Cao cao = new Cao();
                            cao.setNome(jsonObject.getString("nome"));
                            cao.setSexo(jsonObject.getString("sexo"));
                            cao.setPelagem(jsonObject.getString("pelagem"));
                            cao.setDescricao(jsonObject.getString("descricao"));
                            cao.setEndereco(jsonObject.getString("endereco"));
                            cao.setFotoUrl(jsonObject.getString("foto_url"));
                            cao.setIdade(jsonObject.getInt("idade"));
                            cao.setPeso(jsonObject.getDouble("peso"));
                            cao.setVermifugado(jsonObject.getBoolean("vermifugado"));
                            cao.setVacinado(jsonObject.getBoolean("vacinado"));
                            cao.setPorte(jsonObject.getString("porte"));
                            listaAnimal.add(cao);
                            System.out.println(cao.getNome());
                        }

                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                setuprecyclerview(listaAnimal);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }

    private void setuprecyclerview(ArrayList<Animal> listaAnimal){

        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, listaAnimal);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity, menu);

        //Mapeamento e declaração do SearchView
        MenuItem menuItem = menu.findItem(R.id.pesquisa_animaisId);
        SearchView searchView = ( SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.lista_animaisId:
                Toast.makeText(this, "Você já está visualizando o item selecionado.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cadastrar_animalId:
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    //Implementação do método concernente ao evento onQueryTextChange do SearchView
    @Override
    public boolean onQueryTextChange(String newText) {
        String pesquisa = newText.toLowerCase();
        List<Animal> newMData = new ArrayList<>(); //Nova lista onde serão adicionados somente os objetos que correspondem à pesquisa

        //Percorre o ArrayList adicionando os animais que se enquadram na pesquisa
        for(Animal animal: listaAnimal){
            String idade = String.valueOf(animal.getIdade());
            String peso = String.valueOf(animal.getPeso());
            String vermifugado = "Não";
            String vacinado = "Não";

            //Tratamento dos atributos booleanos (vermifugado e vacinado)
            if(animal.isVermifugado())
                vermifugado = "Sim";

            if(animal.isVacinado())
                vacinado = "Sim";

            if(animal.getSexo().toLowerCase().contains(pesquisa) ||
                    animal.getDescricao().toLowerCase().contains(pesquisa) ||
                    animal.getEndereco().toLowerCase().contains(pesquisa) ||
                    animal.getNome().toLowerCase().contains(pesquisa) ||
                    animal.getPelagem().toLowerCase().contains(pesquisa) ||
                    idade.toLowerCase().contains(pesquisa) ||
                    peso.toLowerCase().contains(pesquisa) ||
                    vermifugado.toLowerCase().contains(pesquisa) ||
                    vacinado.toLowerCase().contains(pesquisa)
                    ){

                newMData.add(animal);
            }
        }

        //Atualiza o adapter e seta o recyclerView com o resultado da pesquisa
        adapter.updateList(newMData);
        recyclerView.setAdapter(adapter);

        return true;
    }
}
