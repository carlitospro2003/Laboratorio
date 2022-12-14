package com.ramirez.laboratorio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class CodigoActivity extends AppCompatActivity {

    EditText editCodigo;
    Button btnVerificar;
    SharedPreferences sharedPreferences,sharedPreferences2;
    SharedPreferences.Editor editor;
    String id,email,codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo);

        sharedPreferences2 = getSharedPreferences("email",Context.MODE_PRIVATE);
        email= sharedPreferences2.getString("email",null);
        sharedPreferences = getSharedPreferences("Id", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("Id", null);

        editCodigo = (EditText) findViewById(R.id.editTextCodigo);

        btnVerificar=findViewById(R.id.btnVerificar);
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editCodigo.getText().toString().isEmpty()) Toast.makeText(getApplicationContext(), "Los Campos estan Vacios", Toast.LENGTH_SHORT).show();

                codigo = editCodigo.getText().toString();
                codigoSms();
            }
        });
    }

    public void codigoSms(){
        String url = "http://3.91.195.100/api/login/verificar/usuariosms";

        JSONObject codigos= new JSONObject();
        try {
            codigos.put("codigo",codigo);
            codigos.put("request_id",id);
            codigos.put("email",email);
        }catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest sms = new JsonObjectRequest(Request.Method.POST, url, codigos, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(CodigoActivity.this, "Todo bien", Toast.LENGTH_SHORT).show();
                anotherOne();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CodigoActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        Singleton.getInstance(this).addToRequestQue(sms);
    }

    public void anotherOne(){
        startActivity(new Intent(CodigoActivity.this, MainActivity.class));
    }
}