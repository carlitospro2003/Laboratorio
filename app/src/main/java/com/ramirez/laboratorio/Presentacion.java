package com.ramirez.laboratorio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Presentacion extends AppCompatActivity {

    int REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_presentacion);

        //ANIMACION
        Animation animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        ImageView cuete = findViewById(R.id.cuete);
        TextView credito = findViewById(R.id.credito);

        cuete.setAnimation(animacion2);
        credito.setAnimation(animacion1);
        
        //verificarPermisos();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Presentacion.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }

    /*private void verificarPermisos() {
        int PermisosSms = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        int PermisosCamara = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int PermisosLlamar = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (PermisosSms == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permiso Concendido", Toast.LENGTH_SHORT).show();

        }else {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, REQUEST_CODE);
            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);
        }


    }*/
}