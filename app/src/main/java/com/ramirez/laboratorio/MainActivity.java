package com.ramirez.laboratorio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    LinearLayout temp, mov, magne, humo, corri, salones, perfi, mas, led;

    Button salir;
    Switch prender;
    String sesion;
    SharedPreferences sharedPreferences,login;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //verificarPermisos();
        temp = findViewById(R.id.LYtermo);
        mov = findViewById(R.id.LYMovi);
        magne = findViewById(R.id.LYMagne);
        humo = findViewById(R.id.LYHumo);
        corri = findViewById(R.id.LYCorriente);
        salones = findViewById(R.id.LYSalones);
        //perfi= findViewById(R.id.LYPerfil);
        mas = findViewById(R.id.LYCrearSalones);
        led = findViewById(R.id.LYLuz);
        salir = findViewById(R.id.btncerrar);

        login = this.getSharedPreferences("llave",Context.MODE_PRIVATE);


        /*sharedPreferences = getSharedPreferences("llave",Context.MODE_PRIVATE);
        id = sharedPreferences.getString("Id", null);*/

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login.edit().clear().commit();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });


        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TemperaturaActivity.class));
            }
        });

        mov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MovimientoActivity.class));
            }
        });

        magne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MagneticoActivity.class));
            }
        });

        corri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CorrienteActivity.class));
            }
        });

        humo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HumoActivity.class));
            }
        });

        salones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SalonesActivity.class));
            }
        });

        /*perfi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PerfilActivity.class));
            }
        });*/

        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AgregaSalonActivity.class));
            }
        });

        led.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LedActivity.class));
            }
        });
    }

    private void verificarPermisos() {
        int PermisosSms = ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        int PermisosCamara = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int PermisosLlamada = ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        int PermisosAudio = ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        int PermisosContactos = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (PermisosSms != PackageManager.PERMISSION_GRANTED || PermisosCamara != PackageManager.PERMISSION_GRANTED ||
                PermisosLlamada != PackageManager.PERMISSION_GRANTED || PermisosAudio != PackageManager.PERMISSION_GRANTED || PermisosContactos != PackageManager.PERMISSION_GRANTED) {
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) requestPermissions(new String[]{Manifest.permission.SEND_SMS, Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_CONTACTS},200);
        }
    }


}