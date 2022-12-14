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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.ramirez.laboratorio.api.Humo;
import com.ramirez.laboratorio.api.Magnetico;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MagneticoActivity extends AppCompatActivity {
    Button magnetico,pedirMagnetico;
    int valorT;
    TextView magneticoValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetico);

        magnetico();
        magnetico = findViewById(R.id.buttonMagne1);
        magnetico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MagneticoActivity.this, MainActivity.class));
            }
        });
        pedirMagnetico = findViewById(R.id.PedirMagnetico);
        magneticoValor = findViewById(R.id.textView11);
        pedirMagnetico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                magnetico();
                //magneticoValor.setText(""+valorT);
            }
        });
    }

    public void magnetico(){
        String url="https://io.adafruit.com/api/v2/eder24m3za/feeds/default-dot-magnetico";
        JsonObjectRequest magnetico = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                Magnetico valor = gson.fromJson(response.toString(),Magnetico.class);
                valorT = valor.getMagneticoNumero();
                if(valorT==0) magneticoValor.setText("Puerta abierta");
                else magneticoValor.setText("Puerta cerrada");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MagneticoActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-AIO-Key","aio_DhKI53fEM3CpC9Xh3ixFNIBT4kiu");
                return headers;
            }
        };
        Singleton.getInstance(this).addToRequestQue(magnetico);
    }
}