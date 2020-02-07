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
import android.widget.TextView;
import android.widget.Toast;

import com.example.servicios.Servicios;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class VolumenMotorizadoActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMenosTPC, btnMenosTaxi, btnMenosMoto, btnMenosAuto, btnMenosEscolar, btnMenosOtro, btnMenosCamion,
        btnMasTPC, btnMasTaxi, btnMasMoto, btnMasAuto, btnMasEscolar, btnMasOtro, btnMasCamion;

    TextView txtTPC, txtTaxi, txtMoto, txtAuto, txtEscolar, txtOtro, txtCamion;

    Button btnRegistrarMotorizado;

    String[] horario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volumen_motorizado);

        inicializarBotones();
        inicializarTextos();
        obtenerHorario();
    }

    /**
     * Inicializa todos los botones de la vista
     */
    private void inicializarBotones() {
        btnMenosTPC = findViewById(R.id.btnMenosTPC);
        btnMenosTaxi = findViewById(R.id.btnMenosTaxi);
        btnMenosMoto = findViewById(R.id.btnMenosMoto);
        btnMenosAuto = findViewById(R.id.btnMenosAuto);
        btnMenosEscolar = findViewById(R.id.btnMenosEscolar);
        btnMenosOtro = findViewById(R.id.btnMenosOtro);
        btnMenosCamion = findViewById(R.id.btnMenosCamion);

        btnMasTPC = findViewById(R.id.btnMasTPC);
        btnMasTaxi = findViewById(R.id.btnMasTaxi);
        btnMasMoto = findViewById(R.id.btnMasMoto);
        btnMasAuto = findViewById(R.id.btnMasAuto);
        btnMasEscolar = findViewById(R.id.btnMasEscolar);
        btnMasOtro = findViewById(R.id.btnMasOtro);
        btnMasCamion = findViewById(R.id.btnMasCamion);

        btnMenosTPC.setOnClickListener(this);
        btnMenosTaxi.setOnClickListener(this);
        btnMenosMoto.setOnClickListener(this);
        btnMenosAuto.setOnClickListener(this);
        btnMenosEscolar.setOnClickListener(this);
        btnMenosOtro.setOnClickListener(this);
        btnMenosCamion.setOnClickListener(this);

        btnMasTPC.setOnClickListener(this);
        btnMasTaxi.setOnClickListener(this);
        btnMasMoto.setOnClickListener(this);
        btnMasAuto.setOnClickListener(this);
        btnMasEscolar.setOnClickListener(this);
        btnMasOtro.setOnClickListener(this);
        btnMasCamion.setOnClickListener(this);

        btnRegistrarMotorizado = findViewById(R.id.btnRegistrarMotorizado);
        btnRegistrarMotorizado.setOnClickListener(this);
    }

    /**
     * Inicializa todos los contadores de la vista
     */
    private void inicializarTextos() {
        txtTPC = findViewById(R.id.txtTPC);
        txtTaxi = findViewById(R.id.txtTaxi);
        txtMoto = findViewById(R.id.txtMoto);
        txtAuto = findViewById(R.id.txtAuto);
        txtEscolar = findViewById(R.id.txtEscolar);
        txtOtro = findViewById(R.id.txtOtro);
        txtCamion = findViewById(R.id.txtCamion);
    }

    /**
     * Metodo que disminuye en uno (1) el contador
     * @param contador Contador que será modificado
     */
    private void disminuirContador(TextView contador) {
        int numero = Integer.parseInt(contador.getText().toString());
        if (numero > 0) {
            numero--;
            contador.setText(String.valueOf(numero));
        } else {
            Toast.makeText(this, "No puede ser menor a cero.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Metodo que aumenta en uno (1) el contador
     * @param contador Contador que será modificado
     */
    private void aumentarContador(TextView contador) {
        int numero = Integer.parseInt(contador.getText().toString());
        numero++;
        contador.setText(String.valueOf(numero));
    }

    private int subtotalTP() {
        int tpc = Integer.parseInt(txtTPC.getText().toString());
        int taxi = Integer.parseInt(txtTaxi.getText().toString());

        return taxi + tpc;
    }

    private int subtotalPrivado() {
        int moto = Integer.parseInt(txtMoto.getText().toString());
        int auto = Integer.parseInt(txtAuto.getText().toString());
        int escolar = Integer.parseInt(txtEscolar.getText().toString());
        int otro = Integer.parseInt(txtOtro.getText().toString());
        int camion = Integer.parseInt(txtCamion.getText().toString());

        return moto + auto + escolar + otro + camion;
    }

    /**
     * Deja en blanco los campos después de realizar un registro
     */
    private void reiniciar() {
        txtTPC.setText("0");
        txtTaxi.setText("0");
        txtMoto.setText("0");
        txtAuto.setText("0");
        txtEscolar.setText("0");
        txtOtro.setText("0");
        txtCamion.setText("0");
        obtenerHorario();
    }

    /**
     * Obtiene el horario en el que se encuentra aforando (Ej. 6:15 - 6:30) luego de 1.5 minutos
     */
    private void obtenerHorario() {
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        horario = Servicios.obtenerHorario();
//                        System.out.println(horario[0] + "\n" + horario[1]);
                    }
                }, 90000
        );
    }

    /**
     * Obtiene los datos recolectados en la vista y los retorna como un Map
     */
    private Map obtenerDatos() {
        String[] preferencias =
                Servicios.obtenerPreferencias(getSharedPreferences("generales", Context.MODE_PRIVATE));
        String tpc = txtTPC.getText().toString();
        String taxi = txtTaxi.getText().toString();
        String moto = txtMoto.getText().toString();
        String auto = txtAuto.getText().toString();
        String escolar = txtEscolar.getText().toString();
        String otro = txtOtro.getText().toString();
        String camion = txtCamion.getText().toString();
        String subtotalTP = String.valueOf(subtotalTP());
        String subtotalPrivado = String.valueOf(subtotalPrivado());
        String subtotalMotorizado = String.valueOf(subtotalPrivado() + subtotalTP());

        Map<String, String> mapa = new HashMap<>();

        mapa.put("hoja","Volumen Motorizado");
        mapa.put("aforador", preferencias[0]);
        mapa.put("ubicacion", preferencias[1]);
        mapa.put("fecha", preferencias[2]);
        mapa.put("dia", preferencias[3]);
        mapa.put("acceso", preferencias[4]);
        mapa.put("desde", horario[0]);
        mapa.put("hasta", horario[1]);

        mapa.put("tpc", tpc);
        mapa.put("taxi", taxi);
        mapa.put("subtotalTP", subtotalTP);
        mapa.put("moto", moto);
        mapa.put("auto", auto);
        mapa.put("escolar", escolar);
        mapa.put("otro", otro);
        mapa.put("camion", camion);
        mapa.put("subtotalPrivado", subtotalPrivado);
        mapa.put("subtotalMotorizado", subtotalMotorizado);

        return mapa;
    }

    private void registrar() {
        if(horario == null) {
            Toast.makeText(this, "Espera un poco, un poquito más.", Toast.LENGTH_LONG).show();
            return;
        }
        Map mapa = obtenerDatos();
        Servicios.enviarRequest(VolumenMotorizadoActivity.this, mapa);
        reiniciar();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnMenosTPC: disminuirContador(txtTPC); break;
            case R.id.btnMenosTaxi: disminuirContador(txtTaxi); break;
            case R.id.btnMenosMoto: disminuirContador(txtMoto); break;
            case R.id.btnMenosAuto: disminuirContador(txtAuto); break;
            case R.id.btnMenosEscolar: disminuirContador(txtEscolar); break;
            case R.id.btnMenosOtro: disminuirContador(txtOtro); break;
            case R.id.btnMenosCamion: disminuirContador(txtCamion); break;

            case R.id.btnMasTPC: aumentarContador(txtTPC); break;
            case R.id.btnMasTaxi: aumentarContador(txtTaxi); break;
            case R.id.btnMasMoto: aumentarContador(txtMoto); break;
            case R.id.btnMasAuto: aumentarContador(txtAuto); break;
            case R.id.btnMasEscolar: aumentarContador(txtEscolar); break;
            case R.id.btnMasOtro: aumentarContador(txtOtro); break;
            case R.id.btnMasCamion: aumentarContador(txtCamion); break;

            case R.id.btnRegistrarMotorizado: registrar(); break;
        }
    }
}
