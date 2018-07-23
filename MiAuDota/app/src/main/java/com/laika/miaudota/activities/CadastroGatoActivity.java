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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.laika.miaudota.R;
import com.laika.miaudota.comunicacao.GatoComunicacao;
import com.laika.miaudota.models.Animal;
import com.laika.miaudota.models.Gato;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class CadastroGatoActivity extends Fragment {
    RequestQueue queue;
    private EditText nome_gato;
    private EditText idade_gato;
    private EditText peso_gato;
    private RadioGroup sexo_gato;
    private RadioButton sexo_macho_gato;
    private RadioButton sexo_femea_gato;
    private Spinner pelagem_gato;
    private CheckBox vermifugado_gato;
    private CheckBox vacinado_gato;
    private EditText endereço_gato;
    private EditText foto_gato;
    private EditText about_gato;
    private Button btn_cadastrar_gato;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container,savedInstanceState);
        View v = inflater.inflate(R.layout.cadastro_gato, container, false);
        queue = Volley.newRequestQueue(v.getContext());
        nome_gato = (EditText) v.findViewById(R.id.nome_gato);
        idade_gato = (EditText) v.findViewById(R.id.idade_gato);
        peso_gato = (EditText) v.findViewById(R.id.peso_gato);
        sexo_gato = (RadioGroup) v.findViewById(R.id.sexo_gato);
        sexo_macho_gato = (RadioButton) v.findViewById(R.id.sexo_macho_gato);
        sexo_femea_gato = (RadioButton) v.findViewById(R.id.sexo_femea_gato);
        pelagem_gato = (Spinner) v.findViewById(R.id.pelagem_gato);
        vermifugado_gato = (CheckBox) v.findViewById(R.id.vermifugado_gato);
        vacinado_gato = (CheckBox) v.findViewById(R.id.vacinado_gato);
        endereço_gato = (EditText) v.findViewById(R.id.endereco_gato);
        foto_gato = (EditText) v.findViewById(R.id.foto_gato);
        about_gato = (EditText) v.findViewById(R.id.about_gato);
        btn_cadastrar_gato = (Button) v.findViewById(R.id.btn_cadastrar_gato);


        btn_cadastrar_gato.setOnClickListener(clique_botao);
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


    private void envia_request() throws UnsupportedEncodingException {

        Animal gato = new Gato(
                this.nome_gato.getText().toString(),
                (this.sexo_gato.getCheckedRadioButtonId() == this.sexo_macho_gato.getId() ? this.sexo_macho_gato.getText().toString() : this.sexo_femea_gato.getText().toString()),
                String.valueOf(this.pelagem_gato.getSelectedItem()),
                this.about_gato.getText().toString(),
                Integer.parseInt(this.idade_gato.getText().toString()),
                Double.parseDouble(this.peso_gato.getText().toString()),
                this.vermifugado_gato.isChecked(),
                this.vacinado_gato.isChecked(),
                this.endereço_gato.getText().toString(),
                this.foto_gato.getText().toString()
        );


        GatoComunicacao comm = new GatoComunicacao(this.queue);
        comm.cadastrar(gato);
    }
}