package com.laika.miaudota.comunicacao;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.laika.miaudota.models.Animal;
import com.laika.miaudota.outros.IConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GatoComunicacao implements IComunicacao {
    private RequestQueue queue;
    private List<Animal> listaAnimal;

    public GatoComunicacao(){
        this.listaAnimal = new ArrayList<Animal>();
    }

    public GatoComunicacao(RequestQueue queue){
        this.queue = queue;
        this.listaAnimal = new ArrayList<Animal>();
    }
    @Override
    public void cadastrar(final Animal animal,final ICallback callback) {
        StringRequest postRequest = new StringRequest(Request.Method.POST, IConstants.URL, new Response.Listener<String>() {
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
    public void listar(final ICallback callback) {

    }

    @Override
    public void deletar(int id, final ICallback callback) {
        StringRequest request = new StringRequest(Request.Method.DELETE, IConstants.URL + id,
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
