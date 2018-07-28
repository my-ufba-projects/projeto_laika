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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.laika.miaudota.R;
import com.laika.miaudota.comunicacao.GatoComunicacao;
import com.laika.miaudota.comunicacao.ICallback;
import com.laika.miaudota.models.Animal;
import com.laika.miaudota.models.Gato;
import com.laika.miaudota.outros.IConstants;

import java.io.UnsupportedEncodingException;

public class CadastroGatoActivity extends Fragment {

    private RequestQueue queue;
    private EditText nomeGato;
    private EditText idadeGato;
    private EditText pesoGato;
    private RadioGroup sexoGato;
    private RadioButton sexoMachoGato;
    private RadioButton sexoFemeaGato;
    private Spinner pelagemGato;
    private CheckBox vermifugadoGato;
    private CheckBox vacinadoGato;
    private EditText enderecoGato;
    private EditText fotoGato;
    private EditText aboutGato;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container,savedInstanceState);
        View v = inflater.inflate(R.layout.cadastro_gato, container, false);

        Button btnCadastrarGato;
        //dados de cadastro
        queue = Volley.newRequestQueue(v.getContext());
        nomeGato = (EditText) v.findViewById(R.id.nome_gato);
        idadeGato = (EditText) v.findViewById(R.id.idade_gato);
        pesoGato = (EditText) v.findViewById(R.id.peso_gato);
        sexoGato = (RadioGroup) v.findViewById(R.id.sexo_gato);
        sexoMachoGato = (RadioButton) v.findViewById(R.id.sexo_macho_gato);
        sexoFemeaGato = (RadioButton) v.findViewById(R.id.sexo_femea_gato);
        pelagemGato = (Spinner) v.findViewById(R.id.pelagem_gato);
        vermifugadoGato = (CheckBox) v.findViewById(R.id.vermifugado_gato);
        vacinadoGato = (CheckBox) v.findViewById(R.id.vacinado_gato);
        enderecoGato = (EditText) v.findViewById(R.id.endereco_gato);
        fotoGato = (EditText) v.findViewById(R.id.foto_gato);
        aboutGato = (EditText) v.findViewById(R.id.about_gato);

        btnCadastrarGato = (Button) v.findViewById(R.id.btn_cadastrar_gato);
        btnCadastrarGato.setOnClickListener(clique_botao);

        return v;
    }

    private View.OnClickListener clique_botao = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            try{
                enviaRequest();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    };


    private void enviaRequest() throws UnsupportedEncodingException {
        //request para ser usado no OnClickListener
        //preenche os dados de cadastro
        Animal gato = new Gato(
                this.nomeGato.getText().toString(),
                (this.sexoGato.getCheckedRadioButtonId() == this.sexoMachoGato.getId() ? this.sexoMachoGato.getText().toString() : this.sexoFemeaGato.getText().toString()),
                String.valueOf(this.pelagemGato.getSelectedItem()),
                this.aboutGato.getText().toString(),
                Integer.parseInt(this.idadeGato.getText().toString()),
                Double.parseDouble(this.pesoGato.getText().toString()),
                this.vermifugadoGato.isChecked(),
                this.vacinadoGato.isChecked(),
                this.enderecoGato.getText().toString(),
                this.fotoGato.getText().toString()
        );


        GatoComunicacao comm = new GatoComunicacao(this.queue);
        comm.cadastrar(gato, new ICallback() {
            @Override
            public void onSucess(Object object) {
                Toast.makeText(getActivity(), IConstants.GATO_CADASTRADO_SUCESS, Toast.LENGTH_LONG).show();
                getActivity().finish();
            }

            @Override
            public void onFail(Object object) {

            }
        });
    }
}