/*
 * Copyright (c) 2020. Aplicación desarrollada por Kevin Alejandro Hernández Rodríguez.
 * Ingeniero de sistemas - Universidad de Ibagué - Colombia
 * E-Mail: kevin.hernandezr8@gmail.com
 */

package com.example.aforapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CategoriaActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnVolumenNoMotorizado, btnVolumenMotorizado, btnOcupacionTPC, btnOcupacionTaxi, btnVelocidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        btnVolumenNoMotorizado = findViewById(R.id.btnVolumenNoMotorizado);
        btnVolumenMotorizado = findViewById(R.id.btnVolumenMotorizado);
        btnOcupacionTPC = findViewById(R.id.btnOcupacionTPC);
        btnOcupacionTaxi = findViewById(R.id.btnOcupacionTaxi);
        btnVelocidad = findViewById(R.id.btnVelocidad);

        btnVolumenNoMotorizado.setOnClickListener(this);
        btnVolumenMotorizado.setOnClickListener(this);
        btnOcupacionTPC.setOnClickListener(this);
        btnOcupacionTaxi.setOnClickListener(this);
        btnVelocidad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnVolumenNoMotorizado) {
            Intent intent = new Intent(this, VolumenNoMotorizadoActivity.class);
            startActivity(intent);
        }

        if (v == btnVolumenMotorizado) {
            Intent intent = new Intent(this, VolumenMotorizadoActivity.class);
            startActivity(intent);
        }

        if (v == btnOcupacionTPC) {
            Intent intent = new Intent(this, OcupacionTPCActivity.class);
            startActivity(intent);
        }

        if (v == btnOcupacionTaxi) {
            Intent intent = new Intent(this, OcupacionTaxiActivity.class);
            startActivity(intent);
        }

        if (v == btnVelocidad) {
            Intent intent = new Intent(this, VelocidadActivity.class);
            startActivity(intent);
        }
    }
}
