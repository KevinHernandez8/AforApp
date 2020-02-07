/*
 * Copyright (c) 2020. Aplicación desarrollada por Kevin Alejandro Hernández Rodríguez.
 * Ingeniero de sistemas - Universidad de Ibagué - Colombia
 * E-Mail: kevin.hernandezr8@gmail.com
 */

package com.example.aforapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servicios.Servicios;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class VelocidadActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnIniciar, btnDetener;
    TextView txtTimerValue;
    Handler customHandler = new Handler();

    long startTime = 0l, timeInMilliseconds = 0l, timeSwapBuff = 0l, updateTime = 0l;

    RadioGroup radioGroupTipoVelocidad;
    RadioButton radioAuto, radioMoto, radioBus, radioTaxi, radioBici;
    Button btnRegistrarVelocidad;

    Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updateTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updateTime / 1000);
            secs %= 60;
            int milliseconds = (int) (updateTime % 1000);
            txtTimerValue.setText(String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocidad);

        btnIniciar = findViewById(R.id.btnIniciar);
        btnDetener = findViewById(R.id.btnDetener);
        txtTimerValue = findViewById(R.id.timerValue);
        radioGroupTipoVelocidad = findViewById(R.id.radioGroupTipoVelocidad);
        radioAuto = findViewById(R.id.radioAuto);
        radioMoto = findViewById(R.id.radioMoto);
        radioBus = findViewById(R.id.radioBus);
        radioTaxi = findViewById(R.id.radioTaxi);
        radioBici = findViewById(R.id.radioBici);
        btnRegistrarVelocidad = findViewById(R.id.btnRegistrarVelocidad);

        btnRegistrarVelocidad.setOnClickListener(this);
        btnIniciar.setOnClickListener(this);
        btnDetener.setOnClickListener(this);
    }

    private void iniciarCronometro() {
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);
    }

    private void detenerCronometro() {
        timeSwapBuff += timeInMilliseconds;
        customHandler.removeCallbacks(updateTimerThread);
    }

    /**
     * Método que evalúa si todos los campos son válidos
     */
    private boolean validaciones() {
        if (timeInMilliseconds == 0) {
            Toast.makeText(this, "El tiempo no puede ser cero.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (radioGroupTipoVelocidad.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Debe seleccionar un tipo de vehículo", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void reiniciar() {
        startTime = 0l;
        timeInMilliseconds = 0l;
        timeSwapBuff = 0l;
        updateTime = 0l;
        txtTimerValue.setText("00:000");
        radioGroupTipoVelocidad.clearCheck();
    }

    private String obtenerTipoVehiculoSeleccionado(int radioButtonId) {
        String valor = "";
        switch (radioButtonId) {
            case R.id.radioAuto: valor = "Auto"; break;
            case R.id.radioMoto: valor = "Moto"; break;
            case R.id.radioBus: valor = "Bus"; break;
            case R.id.radioTaxi: valor = "Taxi"; break;
            case R.id.radioBici: valor = "Bici"; break;
        }
        return valor;
    }

    private Map obtenerDatos() {
        String[] preferencias =
                Servicios.obtenerPreferencias(getSharedPreferences("generales", Context.MODE_PRIVATE));
        String tipoVehiculo = obtenerTipoVehiculoSeleccionado(radioGroupTipoVelocidad.getCheckedRadioButtonId());
        String tiempo = txtTimerValue.getText().toString().replace(":",".");
        Date hoy = new Date();
        String horario = hoy.getHours() + ":" + hoy.getMinutes();
        double segundos = Double.parseDouble(tiempo);
        double velocidad = 10 / segundos * 3.6;
        Map<String, String> mapa = new HashMap<>();

        mapa.put("hoja","Velocidad");
        mapa.put("aforador", preferencias[0]);
        mapa.put("fecha", preferencias[2]);
        mapa.put("dia", preferencias[3]);
        mapa.put("tipoVehiculo", tipoVehiculo);
        mapa.put("ubicacion", preferencias[1]);
        mapa.put("acceso", preferencias[4]);
        mapa.put("horario", horario);
        mapa.put("distancia", "10");
        mapa.put("tiempo", String.valueOf(segundos).replace(".", ","));
        mapa.put("velocidad", new DecimalFormat("##.###").format(velocidad).replace(".", ","));

        return mapa;
    }

    private void registrar() {
        if (!validaciones())
            return;
        Map mapa = obtenerDatos();
        Servicios.enviarRequest(VelocidadActivity.this, mapa);
        reiniciar();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnRegistrarVelocidad: registrar(); break;
            case R.id.btnIniciar: iniciarCronometro(); break;
            case R.id.btnDetener: detenerCronometro(); break;
        }
    }
}
