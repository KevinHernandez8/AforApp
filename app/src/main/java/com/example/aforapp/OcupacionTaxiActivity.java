/*
 * Copyright (c) 2020. Aplicación desarrollada por Kevin Alejandro Hernández Rodríguez.
 * Ingeniero de sistemas - Universidad de Ibagué - Colombia
 * E-Mail: kevin.hernandezr8@gmail.com
 */

package com.example.aforapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.servicios.Servicios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OcupacionTaxiActivity extends AppCompatActivity implements View.OnClickListener {

    RadioGroup radioGroupOcupacion;
    RadioButton radio0, radio1, radio2, radio3, radio4;
    EditText txtHorario;
    Button btnRegistroTaxi;
    ImageButton btnHoraTaxi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocupacion_taxi);

        inicializarComponentes();
    }

    /**
     * Inicializa todos los componentes de la vista
     */
    private void inicializarComponentes() {
        radioGroupOcupacion = findViewById(R.id.radioGroupOcupacion);
        radio0 = findViewById(R.id.radio0);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radio4 = findViewById(R.id.radio4);
        txtHorario = findViewById(R.id.txtHorario);

        btnRegistroTaxi = findViewById(R.id.btnRegistroTaxi);
        btnRegistroTaxi.setOnClickListener(this);

        btnHoraTaxi = findViewById(R.id.btnHoraTaxi);
        btnHoraTaxi.setOnClickListener(this);
    }

    /**
     * Método que evalúa si todos los campos son válidos
     */
    private boolean validaciones() {
        if (txtHorario.getText().toString().equals("")) {
            Toast.makeText(this, "Horario no puede estár vacío", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (radioGroupOcupacion.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Debe seleccionar un nivel de ocupación", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Deja en blanco los campos después de realizar un registro
     */
    private void reiniciar() {
        radioGroupOcupacion.clearCheck();
        txtHorario.setText("");
    }

    /**
     * Obtiene la hora actual y la asigna al campo de texto "Hora"
     */
    private void setHora() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String dateformatted = dateFormat.format(date);
        txtHorario.setText(dateformatted);
    }

    /**
     * Obtiene el radio button seleccionado
     * @param radioButtonId
     * @return
     */
    private String obtenerNivelOcupacionSeleccionado(int radioButtonId) {
        String valor = "";
        switch (radioButtonId) {
            case R.id.radio0: valor = "0"; break;
            case R.id.radio1: valor = "1"; break;
            case R.id.radio2: valor = "2"; break;
            case R.id.radio3: valor = "3"; break;
            case R.id.radio4: valor = "4+"; break;
        }
        return valor;
    }

    /**
     * Obtiene los datos recolectados en la vista y los retorna como un Map
     */
    public Map obtenerDatos() {
        String[] preferencias =
                Servicios.obtenerPreferencias(getSharedPreferences("generales", Context.MODE_PRIVATE));
        String horario = txtHorario.getText().toString();
        String nivelOcupacion = obtenerNivelOcupacionSeleccionado(radioGroupOcupacion.getCheckedRadioButtonId());

        Map<String, String> mapa = new HashMap<>();

        mapa.put("hoja","Ocupación Taxi");
        mapa.put("aforador", preferencias[0]);
        mapa.put("fecha", preferencias[2]);
        mapa.put("dia", preferencias[3]);
        mapa.put("nivelOcupacion", nivelOcupacion);
        mapa.put("ubicacion", preferencias[1]);
        mapa.put("acceso", preferencias[4]);
        mapa.put("horario", horario);

        return mapa;
    }

    /**
     * Método que envía el HTTP Request para añadir filas a la hoja de calculo en Drive.
     */
    private void registrar() {
        if(!validaciones())
            return;

        Map mapa = obtenerDatos();
        Servicios.enviarRequest(OcupacionTaxiActivity.this, mapa);
        reiniciar();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnHoraTaxi: setHora(); break;
            case R.id.btnRegistroTaxi: registrar(); break;
        }
    }
}
