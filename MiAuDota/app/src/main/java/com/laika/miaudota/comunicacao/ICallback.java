package com.laika.miaudota.comunicacao;

import com.android.volley.toolbox.JsonArrayRequest;
import com.laika.miaudota.models.Animal;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public interface ICallback {
    void onSucess();
}
