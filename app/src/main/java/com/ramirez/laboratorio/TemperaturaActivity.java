package com.ramirez.laboratorio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.ramirez.laboratorio.api.Temperatura;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TemperaturaActivity extends AppCompatActivity {

    int valorT=0;
    Button btnatras, bpedir;
    TextView vTemperatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);

        vTemperatura = findViewById(R.id.textView7);
        bpedir=findViewById(R.id.bpedir);
        temperatura();

        bpedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temperatura();
                vTemperatura.setText(""+valorT);
            }
        });
        btnatras = findViewById(R.id.btnTempAtras);
        btnatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TemperaturaActivity.this, MainActivity.class));
            }
        });
    }


    public void temperatura(){
        String url="https://io.adafruit.com/api/v2/eder24m3za/feeds/default-dot-temperatura";
        JsonObjectRequest temperatura = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                Temperatura valor = gson.fromJson(response.toString(),Temperatura.class);
                valorT = valor.getNumero();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TemperaturaActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders () throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-AIO-Key", "aio_DhKI53fEM3CpC9Xh3ixFNIBT4kiu");
                return headers;
            }
        };
        Singleton.getInstance(this).addToRequestQue(temperatura);
    }
}