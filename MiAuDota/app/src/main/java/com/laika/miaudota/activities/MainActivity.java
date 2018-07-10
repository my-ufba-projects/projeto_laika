package com.laika.miaudota.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
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

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL = "https://projeto-laika2.herokuapp.com/animais/?format=json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Animal> listaAnimal;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaAnimal = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();
    }

    private void jsonrequest(){

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>(){

            @Override
            public void onResponse(JSONArray response){

                JSONObject jsonObject = null;

                for(int i=0; i<response.length(); i++){

                    try{

                        jsonObject = response.getJSONObject(i);
                        Animal animal  = new Animal();
                        animal.setNome(jsonObject.getString("name"));
                        animal.setSexo(jsonObject.getString("sexo"));
                        animal.setPelagem(jsonObject.getString("pelagem"));
                        animal.setDescricao(jsonObject.getString("descricao"));
                        animal.setEndereco(jsonObject.getString("endereco"));
                        //animal.setFoto_url(jsonObject.getString("foto_url"));
                        animal.setIdade(jsonObject.getInt("idade"));
                        animal.setPeso(jsonObject.getDouble("peso"));
                        animal.setVermifugado(jsonObject.getBoolean("vermifugado"));
                        animal.setVacinado(jsonObject.getBoolean("vacinado"));
                        listaAnimal.add(animal);

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

    private void setuprecyclerview(List<Animal> listaAnimal){

        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, listaAnimal);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);

    }



}
