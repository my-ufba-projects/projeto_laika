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
import com.laika.miaudota.comunicacao.CachorroComunicacao;
import com.laika.miaudota.models.Animal;
import com.laika.miaudota.models.Cao;

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
        Animal cachorro = new Cao(
                this.nome_cachorro.getText().toString(),
                (this.sexo_cachorro.getCheckedRadioButtonId()== this.sexo_macho_cachorro.getId()?this.sexo_macho_cachorro.getText().toString():this.sexo_femea_cachorro.getText().toString()),
                String.valueOf(this.pelagem_cachorro.getSelectedItem()),
                this.about_cachorro.getText().toString(),
                Integer.parseInt(this.idade_cachorro.getText().toString()),
                Double.parseDouble(this.peso_cachorro.getText().toString()),
                this.vermifugado_cachorro.isChecked(),
                this.vacinado_cachorro.isChecked(),
                this.endereço_cachorro.getText().toString(),
                this.foto_cachorro.getText().toString(),
                String.valueOf(this.porte_cachorro.getSelectedItem())
        );


        CachorroComunicacao comm = new CachorroComunicacao(this.queue);
        comm.cadastrar(cachorro);
    }
}
