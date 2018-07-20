package com.laika.miaudota.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.laika.miaudota.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class CadastroCachorroActivity extends Fragment{
    RequestQueue queue;
    private EditText nome_cachorro;
    private EditText idade_cachorro;
    private EditText peso_cachorro;
    private RadioGroup sexo_cachorro;
    private RadioButton sexo_macho_cachorro;
    private RadioButton sexo_femea_cachorro;
    private Spinner pelagem_cachorro;
    private Spinner porte_cachorro;
    private CheckBox vermifugado_cachorro;
    private CheckBox vacinado_cachorro;
    private EditText endereço_cachorro;
    private EditText foto_cachorro;
    private EditText about_cachorro;
    private Button btn_cadastrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container,savedInstanceState);
        View v = inflater.inflate(R.layout.cadastro_cachorro, container, false);
        queue = Volley.newRequestQueue(v.getContext());
        nome_cachorro = (EditText) v.findViewById(R.id.nome_cachorro);
        idade_cachorro = (EditText) v.findViewById(R.id.idade_cachorro);
        peso_cachorro = (EditText) v.findViewById(R.id.peso_cachorro);
        sexo_cachorro = (RadioGroup) v.findViewById(R.id.sexo_cachorro);
        sexo_macho_cachorro = (RadioButton) v.findViewById(R.id.sexo_macho_cachorro);
        sexo_femea_cachorro = (RadioButton) v.findViewById(R.id.sexo_femea_cachorro);
        pelagem_cachorro = (Spinner) v.findViewById(R.id.pelagem_cachorro);
        porte_cachorro = (Spinner) v.findViewById(R.id.porte_cachorro);
        vermifugado_cachorro = (CheckBox) v.findViewById(R.id.vermifugado_cachorro);
        vacinado_cachorro = (CheckBox) v.findViewById(R.id.vacinado_cachoro);
        endereço_cachorro = (EditText) v.findViewById(R.id.endereco_cachorro);
        foto_cachorro = (EditText) v.findViewById(R.id.foto_cachorro);
        about_cachorro = (EditText) v.findViewById(R.id.about_cachorro);
        btn_cadastrar = (Button) v.findViewById(R.id.btn_cadastrar_cachorro);


        btn_cadastrar.setOnClickListener(clique_botao);
        return v;
    }

    private View.OnClickListener clique_botao = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            try{
                envia_request();
            }catch(Exception e){
                System.out.println(e);
            }
        }
    };


    private void envia_request(){

        final String nome = this.nome_cachorro.getText().toString();
        final String idade = this.idade_cachorro.getText().toString();
        final String peso = this.peso_cachorro.getText().toString();
        final String sexo;
        int selecionado_sexo = this.sexo_cachorro.getCheckedRadioButtonId();
        if(selecionado_sexo == this.sexo_macho_cachorro.getId())
            sexo = this.sexo_macho_cachorro.getText().toString();
        else
            sexo = this.sexo_femea_cachorro.getText().toString();
        final String pelagem = String.valueOf(this.pelagem_cachorro.getSelectedItem());
        final String porte = String.valueOf(this.porte_cachorro.getSelectedItem());
        final Boolean vermifugado = this.vermifugado_cachorro.isChecked();
        final Boolean vacinado = this.vacinado_cachorro.isChecked();
        final String endereco = this.endereço_cachorro.getText().toString();
        final String foto_cachorro = this.foto_cachorro.getText().toString();
        final String about_cachorro = this.about_cachorro.getText().toString();


        String url = "https://projeto-laika2.herokuapp.com/animais/";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(), "Cachorro cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Não foi possível cadastrar o cachorro!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("nome", nome);
                parametros.put("idade",idade);
                parametros.put("sexo", sexo);
                parametros.put("pelagem", pelagem);
                parametros.put("peso", peso);
                parametros.put("vermifugado", String.valueOf(vermifugado));
                parametros.put("vacinado", String.valueOf(vacinado));
                parametros.put("descricao", about_cachorro);
                parametros.put("endereco", endereco);
                parametros.put("foto_url", foto_cachorro);
                parametros.put("porte",porte);
                return parametros;
            }
        };
        queue.add(postRequest);
    }
}
