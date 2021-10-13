package com.example.bicipoint;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

public class Login extends AppCompatActivity {

    public Intent intent;
    public EditText correo, contrasena;
    public Button inicio, registro, google, facebook;
    public MaterialToolbar toolbar;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // EditText
        correo = findViewById(R.id.lg_Correo);
        contrasena = findViewById(R.id.lg_contrasena);

        // Botones
        inicio = findViewById(R.id.lg_btn_inicio);
        registro = findViewById(R.id.lg_btn_registro);
        google = findViewById(R.id.lg_btn_google);
        facebook = findViewById(R.id.lg_btn_facebook);

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Register", Toast.LENGTH_SHORT).show();
            }
        });

    }
}