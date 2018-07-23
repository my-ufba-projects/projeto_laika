package com.laika.miaudota.comunicacao;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.laika.miaudota.activities.MainActivity;
import com.laika.miaudota.models.Gato;
import com.laika.miaudota.outros.Config;
import com.laika.miaudota.models.Animal;
import com.laika.miaudota.models.Cao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachorroComunicacao implements IComunicacao {
    RequestQueue queue;
    private ArrayList<Animal> listaAnimal;

    public CachorroComunicacao(){
        this.listaAnimal = new ArrayList<Animal>();
    }

    public CachorroComunicacao(RequestQueue queue){
        this.queue = queue;
        this.listaAnimal = new ArrayList<Animal>();
    }
    @Override
    public void cadastrar(final Animal animal,final ICallback callback) {
        StringRequest postRequest = new StringRequest(Request.Method.POST, Config.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSucess();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("nome", animal.getNome());
                parametros.put("idade", ""+animal.getIdade());
                parametros.put("sexo", animal.getSexo());
                parametros.put("pelagem", animal.getPelagem());
                parametros.put("peso", ""+animal.getPeso());
                parametros.put("vermifugado", ""+animal.isVermifugado());
                parametros.put("vacinado", ""+ animal.isVacinado());
                parametros.put("descricao", animal.getDescricao());
                parametros.put("endereco", animal.getEndereco());
                parametros.put("foto_url", animal.getFotoUrl());
                parametros.put("porte", ((Cao) animal).getPorte());
                return parametros;
            }
        };
        queue.add(postRequest);

    }

    @Override
    public ArrayList<Animal> listar(ICallback callback) {
        return null;
    }


    @Override
    public void deletar(Animal animal) {
    }

}
