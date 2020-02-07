package com.example.aforapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtAforador, txtUbicacion, txtAcceso;
    private Button btnIngresar;
    private TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAforador = findViewById(R.id.txtAforador);
        txtUbicacion = findViewById(R.id.txtUbicacion);
        txtAcceso = findViewById(R.id.txtAcceso);

        btnIngresar = findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(this);

        txtInfo = findViewById(R.id.txtInfo);
        txtInfo.setOnClickListener(this);
    }

    private void guardarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("generales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        String aforador = txtAforador.getText().toString().trim();
        String ubicacion = txtUbicacion.getText().toString().trim();
        String acceso = txtAcceso.getText().toString().trim();
        String fecha = obtenerFecha(new Date());
        String dia = obtenerDia(new Date());

        editor.putString("aforador", aforador);
        editor.putString("ubicacion", ubicacion);
        editor.putString("fecha", fecha);
        editor.putString("dia", dia);
        editor.putString("acceso", acceso);

        editor.commit();
        //Toast.makeText(this, aforador + " // " + ubicacion + " // " + acceso, Toast.LENGTH_SHORT).show();
    }

    private boolean validaciones() {
        boolean bandera = false;

        if (txtAforador.getText().toString().equals("")) {
            Toast.makeText(this, "Aforador no puede estar vacío.", Toast.LENGTH_SHORT).show();
            bandera = false;
        } else if (txtUbicacion.getText().toString().equals("")) {
            Toast.makeText(this, "Ubicación no puede estar vacío.", Toast.LENGTH_SHORT).show();
            bandera = false;
        } else if (txtAcceso.getText().toString().equals("")) {
            Toast.makeText(this, "Acceso no puede estar vacío.", Toast.LENGTH_SHORT).show();
            bandera = false;
        } else {
            bandera = true;
        }
        return bandera;
    }

    private String obtenerFecha(Date fecha) {
        return fecha.getDate() + "-" + (fecha.getMonth() + 1) + "-" + (fecha.getYear() + 1900);
    }

    private String obtenerDia(Date fecha) {
        String dia = "";
        switch (fecha.getDay()) {
            case 1: dia = "Lunes"; break;
            case 2: dia = "Martes"; break;
            case 3: dia = "Miércoles"; break;
            case 4: dia = "Jueves"; break;
            case 5: dia = "Viernes"; break;
            case 6: dia = "Sábado"; break;
            case 7: dia = "Domingo"; break;
        }
        return dia;
    }

    @Override
    public void onClick(View v) {
        if(v == btnIngresar) {
            if(validaciones()) {
                guardarPreferencias();
                Intent intent = new Intent(this, CategoriaActivity.class);
                startActivity(intent);
                this.finish();
            }
        }

        if (v == txtInfo) {
            Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);
        }
    }
}
