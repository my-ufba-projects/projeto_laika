package com.laika.miaudota.comunicacao;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.laika.miaudota.models.Animal;
import com.laika.miaudota.models.Gato;
import com.laika.miaudota.outros.IConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GatoComunicacao implements IComunicacao {
    //agregação, atributos com tipos que não são primitivos
    private RequestQueue queue;
    private List<Animal> listaAnimal;
    private Activity activity;

    public GatoComunicacao(){
        this.listaAnimal = new ArrayList<Animal>();
    }

    public GatoComunicacao(Activity activity){
        this.listaAnimal = new ArrayList<Animal>();
        this.activity = activity;
    }
    //recebe a lista de animais
    public GatoComunicacao(RequestQueue queue){
        this.queue = queue;
        this.listaAnimal = new ArrayList<Animal>();
    }
    @Override
    //cadastrar um animal no banco
    public void cadastrar(final Animal animal,final ICallback callback) {
        StringRequest postRequest = new StringRequest(Request.Method.POST, IConstants.URL_JSON_GATOS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSucess(null);
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
                parametros.put("porte", "null");
                return parametros;
            }
        };
        queue.add(postRequest);
    }

    @Override
    //request para listar os animais e diferenciar gato de cachorro através do porte
    public void listar(final ICallback callback) {
        JsonArrayRequest request = new JsonArrayRequest(IConstants.URL_JSON_GATOS, new Response.Listener<JSONArray>(){

            @Override
            public void onResponse(JSONArray response){
                JSONObject jsonObject = null;
                System.out.println("Teste");
                for(int i=0; i<response.length(); i++){
                    try{
                        jsonObject = response.getJSONObject(i);
                        Gato gato = new Gato();
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
                        gato.setId(jsonObject.getInt("id"));
                        listaAnimal.add(gato);
                        System.out.println(gato.getNome());


                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }

                //fim do request
                callback.onSucess(listaAnimal);
                //setuprecyclerview(listaAnimal);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){

            }
        });
        queue = Volley.newRequestQueue(this.activity);
        queue.add(request);

    }

    @Override
    public void deletar(int id, final ICallback callback) {
        String x = IConstants.URL + id + IConstants.JSON_EXTENSION;
        StringRequest request = new StringRequest(Request.Method.DELETE, x,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSucess(null);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onFail(null);
                    }
                });
        queue.add(request);
    }
}
