package com.ramirez.laboratorio;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalonesActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor idEditor;
    public List<Salones> salones;
    String id;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_recipiente);


    sharedPreferences = getSharedPreferences("Id", Context.MODE_PRIVATE);
    id = sharedPreferences.getString("Id", null);
    Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show();
    verSalones();
    }

    private void verSalones() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv1);

        String url="http://3.91.195.100/api/function3/consultar/usuariosalon/"+id;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                PreSalones res = gson.fromJson(response.toString(),PreSalones.class);
                List<Salones>  listasalones;
                listasalones = res.getSalones();
                SalonesAdaptador adaptador = new SalonesAdaptador(listasalones);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(adaptador);
                recyclerView.setHasFixedSize(true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error",error.toString());
            }
        });
        Singleton.getInstance(this).addToRequestQue(request);
    }
}
