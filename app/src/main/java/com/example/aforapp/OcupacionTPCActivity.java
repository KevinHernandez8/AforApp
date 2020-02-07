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

public class OcupacionTPCActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtHora, txtRuta;
    RadioGroup radioGroupTipo, radioGroupNivel;
    RadioButton radioMinibus, radioBuseta, radioBuseton, radioA, radioB, radioC, radioE, radioF;
    Button btnRegistrar;
    ImageButton btnHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocupacion_tpc);

        inicializarComponentes();
    }

    /**
     * Inicializa todos los componentes de la vista
     */
    private void inicializarComponentes() {
        txtHora = findViewById(R.id.txtHora);
        txtRuta = findViewById(R.id.txtRuta);
        radioGroupTipo = findViewById(R.id.radioGroupTipo);
        radioGroupNivel = findViewById(R.id.radioGroupNivel);
        radioMinibus = findViewById(R.id.radioMinibus);
        radioBuseta = findViewById(R.id.radioBuseta);
        radioBuseton = findViewById(R.id.radioBuseton);
        radioA = findViewById(R.id.radioA);
        radioB = findViewById(R.id.radioB);
        radioC = findViewById(R.id.radioC);
        radioE = findViewById(R.id.radioE);
        radioF = findViewById(R.id.radioF);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(this);

        btnHora = findViewById(R.id.btnHora);
        btnHora.setOnClickListener(this);
    }

    /**
     * Obtiene la hora actual y la asigna al campo de texto "Hora"
     */
    private void setHora() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String dateformatted = dateFormat.format(date);
        txtHora.setText(dateformatted);
    }

    /**
     * Deja en blanco todos los componentes de la vista
     */
    private void reiniciar() {
        txtHora.setText("");
        txtRuta.setText("");
        radioGroupTipo.clearCheck();
        radioGroupNivel.clearCheck();
    }

    /**
     * Método que evalúa si los campos son válidos
     * @return
     */
    private boolean validaciones() {
        if(txtHora.getText().toString().equals("")) {
            Toast.makeText(this, "Hora no puede estár vacío.", Toast.LENGTH_SHORT).show();
            return false;
        }
        
        if (txtRuta.getText().toString().equals("")) {
            Toast.makeText(this, "Ruta no puede estár vacío.", Toast.LENGTH_SHORT).show();
            return false;
        }
        
        if (radioGroupTipo.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Debe seleccionar un tipo de vehículo.", Toast.LENGTH_SHORT).show();
            return false;
        }
        
        if (radioGroupNivel.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Debe seleccionar un nivel de ocupación", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Obtiene el radio button seleccionado del grupo "Nivel de ocupación"
     * @param radioButtonId
     * @return
     */
    private String obtenerNivelOcupacionSeleccionado(int radioButtonId) {
        String valor = "";
        switch (radioButtonId) {
            case R.id.radioA: valor = "A"; break;
            case R.id.radioB: valor = "B"; break;
            case R.id.radioC: valor = "C"; break;
            case R.id.radioE: valor = "E"; break;
            case R.id.radioF: valor = "F"; break;
        }
        return valor;
    }

    /**
     * Obtiene el radio button seleccionado del grupo "Tipo de vehículo"
     * @param radioButtonId
     * @return
     */
    private String obtenerTipoVehiculoSeleccionado(int radioButtonId) {
        String valor = "";
        switch (radioButtonId) {
            case R.id.radioMinibus: valor = "Minibus"; break;
            case R.id.radioBuseta: valor = "Buseta"; break;
            case R.id.radioBuseton: valor = "Busetón"; break;
        }
        return valor;
    }

    /**
     * Obtiene los datos recolectados en la vista y los retorna como un Map
     */
    public Map obtenerDatos() {
        String[] preferencias =
                Servicios.obtenerPreferencias(getSharedPreferences("generales", Context.MODE_PRIVATE));
        String hora = txtHora.getText().toString();
        String ruta = txtRuta.getText().toString();
        String tipo = obtenerTipoVehiculoSeleccionado(radioGroupTipo.getCheckedRadioButtonId());
        String nivel = obtenerNivelOcupacionSeleccionado(radioGroupNivel.getCheckedRadioButtonId());

        Map<String, String> mapa = new HashMap<>();

        mapa.put("hoja","Ocupación TPC");
        mapa.put("aforador", preferencias[0]);
        mapa.put("ubicacion", preferencias[1]);
        mapa.put("acceso", preferencias[4]);
        mapa.put("fecha", preferencias[2]);
        mapa.put("dia", preferencias[3]);
        mapa.put("hora", hora);
        mapa.put("ruta", ruta);
        mapa.put("tipo", tipo);
        mapa.put("nivel", nivel);

        return mapa;
    }

    /**
     * Método que envía el HTTP Request para añadir filas a la hoja de calculo en Drive.
     */
    private void registrar() {
        if(!validaciones())
            return;

        Map mapa = obtenerDatos();
        Servicios.enviarRequest(OcupacionTPCActivity.this, mapa);
        reiniciar();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnHora: setHora(); break;
            case R.id.btnRegistrar: registrar(); break;
        }
    }
}
