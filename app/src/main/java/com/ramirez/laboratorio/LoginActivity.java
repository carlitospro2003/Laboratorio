package com.ramirez.laboratorio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.Manifest.permission;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    int REQUEST_CODE = 200;

    Button pasar;
    EditText email, password;
    TextView reg;
    String Email, Password, tokens, llave, id;
    SharedPreferences login, ids;
    SharedPreferences.Editor editor, idEditor;
    CheckBox guardarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pasar = (Button) findViewById(R.id.btnIniciar);
        email = (EditText) findViewById(R.id.correo);
        password = (EditText) findViewById(R.id.contrasena);

        reg = (TextView) findViewById(R.id.registrate);
        guardarSesion = (CheckBox) findViewById(R.id.guardarSesion);
        login = this.getSharedPreferences("llave", Context.MODE_PRIVATE);
        ids = this.getSharedPreferences("Id", Context.MODE_PRIVATE);
        idEditor = ids.edit();
        editor = login.edit();

        verificarPermisos();

        if (revisarSesion() == true) {
            iniciarSesion();
        }

        pasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Los Campos estan Vacios", Toast.LENGTH_SHORT).show();
                } else if (!isEmail(email.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Correo no Valido", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().length() < 2) {
                    Toast.makeText(LoginActivity.this, "La contraseña debe ser mayor a 6 caracteres", Toast.LENGTH_SHORT).show();
                } else {
                    Password = password.getText().toString();
                    Email = email.getText().toString();
                    usuarioApi(Email, Password);
                }
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


    }

    private void guardarSesion(boolean checked) {
        editor.putBoolean(llave, checked);
        editor.apply();
    }

    private boolean revisarSesion() {
        return this.login.getBoolean(llave, false);
    }


    private void iniciarSesion() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }


    public boolean isEmail(String cadena) {
        boolean resultado;
        if (Patterns.EMAIL_ADDRESS.matcher(cadena).matches()) {
            resultado = true;
        } else {
            resultado = false;
        }
        return resultado;
    }


    public void usuarioApi(String Email, String Password) {
        String URL = "http://3.91.195.100/api/login/make/login";
        JSONObject login = new JSONObject();
        try {
            login.put("email", Email);
            login.put("password", Password);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, login, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                Respuestas token = gson.fromJson(response.toString(), Respuestas.class);
                tokens = token.getToken();
                id = token.getId().toString();

                guardarId(id);
                guardarSesion(guardarSesion.isChecked());
                iniciarSesion();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        Singleton.getInstance(this).addToRequestQue(jsonObjectRequest);
    }


    public void guardarId(String ID) {
        idEditor.putString("Id", ID);
        idEditor.apply();
    }


    private void verificarPermisos() {
        int PermisosSms = ActivityCompat.checkSelfPermission(this, permission.SEND_SMS);
        int PermisosCamara = ActivityCompat.checkSelfPermission(this, permission.CAMERA);
        int PermisosLlamada = ActivityCompat.checkSelfPermission(this, permission.CALL_PHONE);
        int PermisosAudio = ActivityCompat.checkSelfPermission(this, permission.RECORD_AUDIO);
        int PermisosContactos = ActivityCompat.checkSelfPermission(this, permission.READ_CONTACTS);
        if (PermisosSms != PackageManager.PERMISSION_GRANTED || PermisosCamara != PackageManager.PERMISSION_GRANTED ||
        PermisosLlamada != PackageManager.PERMISSION_GRANTED || PermisosAudio != PackageManager.PERMISSION_GRANTED || PermisosContactos != PackageManager.PERMISSION_GRANTED) {
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) requestPermissions(new String[]{permission.SEND_SMS, permission.CALL_PHONE,permission.CAMERA,permission.RECORD_AUDIO,permission.READ_CONTACTS},200);
        }
    }
}