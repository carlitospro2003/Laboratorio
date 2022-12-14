package com.ramirez.laboratorio;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.ramirez.laboratorio.api.Registrar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AgregaSalonActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor idEditor;
    Button crear;
    EditText usuario;
    EditText nombre, ubicacion, edificio;
    CheckBox s1, s2, s3, s4, s5, s6;
    String id,nombres,ubicaciones,edificios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrega_salon);

        sharedPreferences = getSharedPreferences("Id", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("Id", null);
        nombre =(EditText) findViewById(R.id.nombre);
        ubicacion=(EditText) findViewById(R.id.ubicacion);
        edificio=(EditText) findViewById(R.id.edificio);
        /*s1 = findViewById(R.id.s1);
        s2 = findViewById(R.id.s2);
        s3 = findViewById(R.id.s3);
        s4 = findViewById(R.id.s4);
        s5 = findViewById(R.id.s5);
        s6 = findViewById(R.id.s6);
        s1.setChecked(true);s2.setChecked(true);s3.setChecked(true);s4.setChecked(true);
        s5.setChecked(true);s6.setChecked(true);*/

        crear=(Button) findViewById(R.id.crear);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nombre.equals(" ") || ubicacion.equals(" ") || edificio.equals(" ")){
                    Toast.makeText(getApplicationContext(), "Los Campos estan Vacios", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
                edificios=edificio.getText().toString();
                nombres=nombre.getText().toString();
                ubicaciones=ubicacion.getText().toString();
                recuperar();
            }
        });
    }

    private void recuperar() {
        String URL="http://3.91.195.100/api/function1/crear/salon";
        JSONObject edificiosO = new JSONObject();
        try {
            edificiosO.put("nombre", nombres);
            edificiosO.put("ubicacion",ubicaciones);
            edificiosO.put("edificio", edificios);
            edificiosO.put("usuario", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, edificiosO, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                startActivity(new Intent(AgregaSalonActivity.this, MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("vol", error.toString());
            }
        });
        Singleton.getInstance(this).addToRequestQue(jsonObjectRequest);
    }
}
      /*  s1 = findViewById(R.id.s1);
        s2 = findViewById(R.id.s2);
        s3 = findViewById(R.id.s3);
        s4 = findViewById(R.id.s4);
        s5 = findViewById(R.id.s5);
        s6 = findViewById(R.id.s6);
        s1.setChecked(true);s2.setChecked(true);s3.setChecked(true);s4.setChecked(true);
        s5.setChecked(true);s6.setChecked(true);
        crear = findViewById(R.id.crear);
        nombre = findViewById(R.id.nombre);
        ubicacion = findViewById(R.id.ubicacion);
        edificio = findViewById(R.id.edificio);
        usuario=findViewById(R.id.usuario);
        Bundle parametros = this.getIntent().getExtras();
        if(!parametros.getString("id").isEmpty()){
            nombre.setText(parametros.getString("id"));
            recuperar();

    }iniciar();
}

    private void recuperar() {
        Integer id=getSharedPreferences("credenciales",Context.MODE_PRIVATE).getInt("id",0);
        JsonObjectRequest JsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getSharedPreferences("credenciales",Context.MODE_PRIVATE).getString("url","http://0.0.0.0/")+"salon/"+id.toString()+"/"+nombre.getText().toString(),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(AgregaSalonActivity.this,"Exitoso",Toast.LENGTH_SHORT).show();
                        CountDownTimer t = new CountDownTimer(2000,1000) {
                            @Override
                            public void onTick(long l) {
                                Long d=l/1000;
                            }
                            @Override
                            public void onFinish() {
                                try{
                                    ubicacion.setText(response.getString("ubicacion"));
                                    nombre.setEnabled(false);
                                    ubicacion.setEnabled(false);
                                    edificio.setEnabled(false);
                                    if(!response.getString("sensor1").equalsIgnoreCase("name")){
                                        s1.setChecked(true);
                                        s1.setEnabled(false);
                                    }
                                    if(!response.getString("sensor2").equalsIgnoreCase("name")){
                                        s2.setChecked(true);
                                        s2.setEnabled(false);
                                    }
                                    if(!response.getString("sensor3").equalsIgnoreCase("name")){
                                        s3.setChecked(true);
                                        s3.setEnabled(false);
                                    }
                                    if(!response.getString("sensor4").equalsIgnoreCase("name")){
                                        s4.setChecked(true);
                                        s4.setEnabled(false);
                                    }
                                    if(!response.getString("sensor5").equalsIgnoreCase("name")){
                                        s5.setChecked(true);
                                        s5.setEnabled(false);
                                    }
                                    if(!response.getString("sensor6").equalsIgnoreCase("name")){
                                        s6.setChecked(true);
                                        s6.setEnabled(false);
                                    }
                                    crear.setText("Agregar sensores");
                                    crear.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View view){
                                            if(nombre.getText().toString().isEmpty() || ubicacion.getText().toString().isEmpty() || edificio.getText().toString().isEmpty()){
                                                Toast.makeText(AgregaSalonActivity.this,"Llene todos los campos",Toast.LENGTH_SHORT).show();
                                            }else if(s1.isChecked() || s2.isChecked() || s3.isChecked() || s4.isChecked() || s5.isChecked() || s6.isChecked()){
                                                agregarSensor();
                                            }else {
                                                Toast.makeText(AgregaSalonActivity.this,"Seleccione al menos un sensor",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }catch(JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        crear.setEnabled(true);
                        if(error.networkResponse.statusCode==400){
                            Toast.makeText(AgregaSalonActivity.this,"Nombre ya utilizado",Toast.LENGTH_SHORT).show();
                        }else if(error.networkResponse.statusCode==401){
                            Toast.makeText(AgregaSalonActivity.this,"No se encontro el usuario",Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(AgregaSalonActivity.this,"Error"+String.valueOf(
                                            error.networkResponse.statusCode),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }){
            public Map<String,String> getHeaders() throws AuthFailureError{
                Map<String,String> headers = new HashMap<>();
                headers.put("Authorization","Bearer "+getSharedPreferences("credenciales",
                        Context.MODE_PRIVATE).getString("token","NULL"));
                headers.put("aio_key",getSharedPreferences("credenciales",MODE_PRIVATE).
                        getString("arduino","none"));
                return headers;
            }
        };
        Singleton.getInstance(this).addToRequestQue(JsonObjectRequest);
    }

    private void agregarSensor() {
    }

    public void iniciar() {
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nombre.getText().toString().isEmpty() || ubicacion.getText().toString().isEmpty() || edificio.getText().toString().isEmpty()) {
                    Toast.makeText(AgregaSalonActivity.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
                } else if (s1.isChecked() || s2.isChecked() || s3.isChecked() || s4.isChecked() || s5.isChecked() || s6.isChecked()) {
                    crear.setEnabled(false);
                    agregarsalon();
                } else {
                    Toast.makeText(AgregaSalonActivity.this, "Seleccione al menos un sensor", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
        public void agregarsalon() {
            JSONObject salon = new JSONObject(), sensores = new JSONObject(), datos = new JSONObject();
            try {
                salon.put("nombre", nombre.getText().toString());
                salon.put("ubicacion", ubicacion.getText().toString());
                salon.put("edificio", edificio.getText().toString());
                sensores.put("sensor1", s1.isChecked());
                sensores.put("sensor2", s2.isChecked());
                sensores.put("sensor3", s3.isChecked());
                sensores.put("sensor4", s4.isChecked());
                sensores.put("sensor5", s5.isChecked());
                sensores.put("sensor6", s6.isChecked());
                datos.put("sensores", sensores);
                datos.put("salon", salon);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Integer id = getSharedPreferences("credenciales", MODE_PRIVATE).getInt("id", 0);
            JsonObjectRequest JsonObjectRequest = new JsonObjectRequest(Request.Method.POST, getSharedPreferences("credenciales", MODE_PRIVATE).getString(
                    "url", "http://0.0.0.0/") + "create/" + id.toString(),
                    datos,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(AgregaSalonActivity.this, "Salon agregado", Toast.LENGTH_SHORT).show();
                            CountDownTimer t = new CountDownTimer(2000, 1000) {
                                @Override
                                public void onTick(long l) {
                                    Long d = l / 1000;
                                }

                                @Override
                                public void onFinish() {
                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(i);
                                }
                            }.start();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            crear.setEnabled(true);
                            if (error.networkResponse.statusCode == 400) {
                                Toast.makeText(AgregaSalonActivity.this, "Nombre ya utilizado", Toast.LENGTH_SHORT).show();
                            } else if (error.networkResponse.statusCode == 401) {
                                Toast.makeText(AgregaSalonActivity.this, "No se encontro el usuario", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AgregaSalonActivity.this, "Error" + String.valueOf(
                                                error.networkResponse.statusCode),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }) {
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Bearer " + getSharedPreferences("credenciales",
                            Context.MODE_PRIVATE).getString("token", "NULL"));
                    headers.put("aio_key", getSharedPreferences("credenciales", MODE_PRIVATE).
                            getString("arduino", "none"));
                    return headers;
                }
            };
            Singleton.getInstance(this).addToRequestQue(JsonObjectRequest);
        }
    }*/