package com.ramirez.laboratorio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.ramirez.laboratorio.api.Registrar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    RadioButton btnFemenino, btnMasculino;
    TextView Crear;
    EditText Nombre, Correo, Contrasena, Confirmarcontra, Telefono;
    Button Registrarbtn;
    String nombre,correo,contra,confircontra,telefono,sexo,emails,ids;
    SharedPreferences SPemial, SPid;
    SharedPreferences.Editor idEditor,mailEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Crear=(TextView)findViewById(R.id.nuevoUsuario);

        Nombre=(EditText) findViewById(R.id.nameTextField);
        Correo=(EditText)findViewById(R.id.UsuarioSingUpTextField);
        Contrasena=(EditText)findViewById(R.id.contraTextField);
        Confirmarcontra=(EditText)findViewById(R.id.ConfirmarContraTextField);
        Registrarbtn=(Button) findViewById(R.id.btnRegistrar);
        Telefono=(EditText)findViewById(R.id.editTextPhone);
        btnFemenino=(RadioButton) findViewById(R.id.radioButtonFemenino);
        btnMasculino=(RadioButton)findViewById(R.id.radioButtonMasculino);

        SPid = this.getSharedPreferences("Id", Context.MODE_PRIVATE);
        SPemial = this.getSharedPreferences("email",Context.MODE_PRIVATE);
        idEditor = SPid.edit();
        mailEditor = SPemial.edit();


        Registrarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre = Nombre.getText().toString();
                correo = Correo.getText().toString();
                contra = Contrasena.getText().toString();
                confircontra = Confirmarcontra.getText().toString();
                telefono = Telefono.getText().toString();
                onRadioButtonClicked();

                if (nombre.equals("") || correo.equals("") || contra.equals("") || confircontra.equals("") || telefono.equals("")){
                    Toast.makeText(getApplicationContext(), "Los Campos estan Vacios", Toast.LENGTH_SHORT).show();
                }
                ejecutarservicio("http://3.91.195.100/api/login/make/usuario");
               // iniciarRegistro();
            }
        });
    }

    public void onRadioButtonClicked() {
        if(btnMasculino.isChecked()==true){
            sexo="masculino";
        } else if(btnFemenino.isChecked()==true){
            sexo="femenino";
        } else {
            Toast.makeText(this, "Selecciona el sexo", Toast.LENGTH_SHORT).show();
        }
    }

    private void ejecutarservicio(String URL) {
        JSONObject usuario = new JSONObject();
        try {
            usuario.put("name", nombre);
            usuario.put("email",correo);
            usuario.put("password", confircontra);
            usuario.put("telefono", telefono);
            usuario.put("sexo", sexo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, usuario, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                Registrar email = gson.fromJson(response.toString(),Registrar.class);
                emails=email.getEmail();
                ids=email.getEmail();
                guardarId(ids);
                guardarEmail(emails);
                iniciarCode();
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

    private void iniciarCode(){
        startActivity(new Intent(RegisterActivity.this, CodigoActivity.class));
    }

    public void guardarId(String ID){
        idEditor.putString("Id",ID);
        idEditor.apply();
    }

    public void guardarEmail(String email){
        mailEditor.putString("email",email);
        mailEditor.apply();
    }
}