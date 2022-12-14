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
import com.ramirez.laboratorio.api.Corriente;
import com.ramirez.laboratorio.api.Humo;
import com.ramirez.laboratorio.api.Potencia;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CorrienteActivity extends AppCompatActivity {

    int valorT,valorP;
    Button atrasCorriente, pedirCorriente;
    TextView corriente, potencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corriente);
        pedirCorriente=findViewById(R.id.bCorriente);
        corriente=findViewById(R.id.textViewResCorriente);
        atrasCorriente = findViewById(R.id.buttonCorriente);
        potencia=findViewById(R.id.textViewResPotencia);

        corriente();
        potencia();

        pedirCorriente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                corriente();
                potencia();
                corriente.setText(""+valorT);
                potencia.setText(""+valorP);
            }
        });

        atrasCorriente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CorrienteActivity.this, MainActivity.class));
            }
        });
    }

    public void corriente(){
        String url="https://io.adafruit.com/api/v2/eder24m3za/feeds/default-dot-imax";
        JsonObjectRequest corriente = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                Corriente valor = gson.fromJson(response.toString(),Corriente.class);
                valorT = valor.getCorrienteNumero();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CorrienteActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-AIO-Key", "aio_DhKI53fEM3CpC9Xh3ixFNIBT4kiu");
                return headers;
            }
        };
        Singleton.getInstance(this).addToRequestQue(corriente);
    }


    public void potencia(){
        String url="https://io.adafruit.com/api/v2/eder24m3za/feeds/default-dot-potencia";
        JsonObjectRequest corriente = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                Potencia valor = gson.fromJson(response.toString(), Potencia.class);
                valorP = valor.getPotenciaNumero();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CorrienteActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-AIO-Key", "aio_DhKI53fEM3CpC9Xh3ixFNIBT4kiu");
                return headers;
            }
        };
        Singleton.getInstance(this).addToRequestQue(corriente);
    }
}