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
import com.ramirez.laboratorio.api.Movimiento;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MovimientoActivity extends AppCompatActivity {

    Button pedirMovimiento;
    int valorT;
    TextView MovimientoValor;
    Button btnatrasmov;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);

        btnatrasmov = findViewById(R.id.btnAtrasPrese);

        movimiento();

        btnatrasmov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MovimientoActivity.this, MainActivity.class));
            }
        });
        MovimientoValor=findViewById(R.id.textView);
        pedirMovimiento=findViewById(R.id.PedirMovimiento);
        pedirMovimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movimiento();
            }
        });
    }

    public void movimiento(){
        String url="https://io.adafruit.com/api/v2/eder24m3za/feeds/default-dot-movimiento";
        JsonObjectRequest movimiento = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                Movimiento valor = gson.fromJson(response.toString(),Movimiento.class);
                valorT = valor.getMovimientoNumero();
                if(valorT==1) MovimientoValor.setText("Hay una persona");
                else MovimientoValor.setText("Sin personas");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MovimientoActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-AIO-Key","aio_DhKI53fEM3CpC9Xh3ixFNIBT4kiu");
                return headers;
            }
        };
        Singleton.getInstance(this).addToRequestQue(movimiento);
    }
}