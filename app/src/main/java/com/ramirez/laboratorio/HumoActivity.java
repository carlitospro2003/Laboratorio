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
import com.ramirez.laboratorio.api.Temperatura;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HumoActivity extends AppCompatActivity {

    int valorT;
    Button humito,pedirHumo;
    TextView humoValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humo);

        pedirHumo=findViewById(R.id.PedirHumo);

        humo();
        pedirHumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                humo();
                humoValor.setText(""+valorT);
            }
        });

        humito = findViewById(R.id.button1Humo);
        humoValor=findViewById(R.id.textView4Humo);

        humito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HumoActivity.this, MainActivity.class));
            }
        });
    }

    public void humo(){
        String url="https://io.adafruit.com/api/v2/eder24m3za/feeds/default-dot-humo";
        JsonObjectRequest humo = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                Humo valor = gson.fromJson(response.toString(),Humo.class);
                valorT = valor.getHumoNumero();

                if(valorT==1) humoValor.setText("AHHH hay gas");
                else humoValor.setText("Sin gas");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HumoActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-AIO-Key","aio_DhKI53fEM3CpC9Xh3ixFNIBT4kiu");
                return headers;
            }
        };
        Singleton.getInstance(this).addToRequestQue(humo);
    }
}