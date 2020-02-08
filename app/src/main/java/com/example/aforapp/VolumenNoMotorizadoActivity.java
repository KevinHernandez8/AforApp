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

public class VolumenNoMotorizadoActivity extends AppCompatActivity implements View.OnClickListener {

    // Botones sección "Peatones - Femenino"
    Button btnMenosFemeninoNino, btnMasFemeninoNino, btnMenosFemeninoAdulto, btnMasFemeninoAdulto,
            btnMenosFemeninoAdultoMayor, btnMasFemeninoAdultoMayor, btnMenosFemeninoMovilidadCondicionada,
            btnMasFemeninoMovilidadCondicionada;

    // Botones sección "Peatones - Masculino"
    Button btnMenosMasculinoNino, btnMasMasculinoNino,
            btnMenosMasculinoAdulto, btnMasMasculinoAdulto, btnMenosMasculinoAdultoMayor,
            btnMasMasculinoAdultoMayor, btnMenosMasculinoMovilidadCondicionada,
            btnMasMasculinoMovilidadCondicionada;

    // Botones sección "Ciclistas - Femenino"
    Button btnMenosFemeninoNinoCiclista, btnMasFemeninoNinoCiclista, btnMenosFemeninoAdultoCiclista,
            btnMasFemeninoAdultoCiclista, btnMenosFemeninoAdultoMayorCiclista,
            btnMasFemeninoAdultoMayorCiclista;

    // Botones sección "Ciclistas - Masculino"
    Button btnMenosMasculinoNinoCiclista, btnMasMasculinoNinoCiclista, btnMenosMasculinoAdultoCiclista,
            btnMasMasculinoAdultoCiclista, btnMenosMasculinoAdultoMayorCiclista,
            btnMasMasculinoAdultoMayorCiclista;

    // Contadores sección "Peatones - Femenino"
    TextView txtFemeninoNino, txtFemeninoAdulto, txtFemeninoAdultoMayor, txtFemeninoMovilidadCondicionada;

    // Contadores sección "Peatones - Masculino"
    TextView txtMasculinoNino, txtMasculinoAdulto, txtMasculinoAdultoMayor, txtMasculinoMovilidadCondicionada;

    // Contadores sección "Ciclistas - Femenino"
    TextView txtFemeninoNinoCiclista, txtFemeninoAdultoCiclista, txtFemeninoAdultoMayorCiclista;

    // Contadores sección "Ciclistas - Masculino"
    TextView txtMasculinoNinoCiclista, txtMasculinoAdultoCiclista, txtMasculinoAdultoMayorCiclista;

    Button btnRegistrarNoMotorizado;

    String[] horario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volumen_no_motorizado);

        inicializarBotones();
        inicializarTextos();
        obtenerHorario();
    }

    /**
     * Inicializa los botones de la vista
     */
    private void inicializarBotones() {
        btnMenosFemeninoNino = findViewById(R.id.btnMenosFemeninoNino);
        btnMasFemeninoNino = findViewById(R.id.btnMasFemeninoNino);
        btnMenosFemeninoAdulto = findViewById(R.id.btnMenosFemeninoAdulto);
        btnMasFemeninoAdulto = findViewById(R.id.btnMasFemeninoAdulto);
        btnMenosFemeninoAdultoMayor = findViewById(R.id.btnMenosFemeninoAdultoMayor);
        btnMasFemeninoAdultoMayor = findViewById(R.id.btnMasFemeninoAdultoMayor);
        btnMenosFemeninoMovilidadCondicionada = findViewById(R.id.btnMenosFemeninoMovilidadCondicionada);
        btnMasFemeninoMovilidadCondicionada = findViewById(R.id.btnMasFemeninoMovilidadCondicionada);

        btnMenosMasculinoNino = findViewById(R.id.btnMenosMasculinoNino);
        btnMasMasculinoNino = findViewById(R.id.btnMasMasculinoNino);
        btnMenosMasculinoAdulto = findViewById(R.id.btnMenosMasculinoAdulto);
        btnMasMasculinoAdulto = findViewById(R.id.btnMasMasculinoAdulto);
        btnMenosMasculinoAdultoMayor = findViewById(R.id.btnMenosMasculinoAdultoMayor);
        btnMasMasculinoAdultoMayor = findViewById(R.id.btnMasMasculinoAdultoMayor);
        btnMenosMasculinoMovilidadCondicionada = findViewById(R.id.btnMenosMasculinoMovilidadCondicionada);
        btnMasMasculinoMovilidadCondicionada = findViewById(R.id.btnMasMasculinoMovilidadCondicionada);

        btnMenosFemeninoNinoCiclista = findViewById(R.id.btnMenosFemeninoNinoCiclista);
        btnMasFemeninoNinoCiclista = findViewById(R.id.btnMasFemeninoNinoCiclista);
        btnMenosFemeninoAdultoCiclista = findViewById(R.id.btnMenosFemeninoAdultoCiclista);
        btnMasFemeninoAdultoCiclista = findViewById(R.id.btnMasFemeninoAdultoCiclista);
        btnMenosFemeninoAdultoMayorCiclista = findViewById(R.id.btnMenosFemeninoAdultoMayorCiclista);
        btnMasFemeninoAdultoMayorCiclista = findViewById(R.id.btnMasFemeninoAdultoMayorCiclista);

        btnMenosMasculinoNinoCiclista = findViewById(R.id.btnMenosMasculinoNinoCiclista);
        btnMasMasculinoNinoCiclista = findViewById(R.id.btnMasMasculinoNinoCiclista);
        btnMenosMasculinoAdultoCiclista = findViewById(R.id.btnMenosMasculinoAdultoCiclista);
        btnMasMasculinoAdultoCiclista = findViewById(R.id.btnMasMasculinoAdultoCiclista);
        btnMenosMasculinoAdultoMayorCiclista = findViewById(R.id.btnMenosMasculinoAdultoMayorCiclista);
        btnMasMasculinoAdultoMayorCiclista = findViewById(R.id.btnMasMasculinoAdultoMayorCiclista);

        btnMenosFemeninoNino.setOnClickListener(this);
        btnMasFemeninoNino.setOnClickListener(this);
        btnMenosFemeninoAdulto.setOnClickListener(this);
        btnMasFemeninoAdulto.setOnClickListener(this);
        btnMenosFemeninoAdultoMayor.setOnClickListener(this);
        btnMasFemeninoAdultoMayor.setOnClickListener(this);
        btnMenosFemeninoMovilidadCondicionada.setOnClickListener(this);
        btnMasFemeninoMovilidadCondicionada.setOnClickListener(this);

        btnMenosMasculinoNino.setOnClickListener(this);
        btnMasMasculinoNino.setOnClickListener(this);
        btnMenosMasculinoAdulto.setOnClickListener(this);
        btnMasMasculinoAdulto.setOnClickListener(this);
        btnMenosMasculinoAdultoMayor.setOnClickListener(this);
        btnMasMasculinoAdultoMayor.setOnClickListener(this);
        btnMenosMasculinoMovilidadCondicionada.setOnClickListener(this);
        btnMasMasculinoMovilidadCondicionada.setOnClickListener(this);

        btnMenosFemeninoNinoCiclista.setOnClickListener(this);
        btnMasFemeninoNinoCiclista.setOnClickListener(this);
        btnMenosFemeninoAdultoCiclista.setOnClickListener(this);
        btnMasFemeninoAdultoCiclista.setOnClickListener(this);
        btnMenosFemeninoAdultoMayorCiclista.setOnClickListener(this);
        btnMasFemeninoAdultoMayorCiclista.setOnClickListener(this);

        btnMenosMasculinoNinoCiclista.setOnClickListener(this);
        btnMasMasculinoNinoCiclista.setOnClickListener(this);
        btnMenosMasculinoAdultoCiclista.setOnClickListener(this);
        btnMasMasculinoAdultoCiclista.setOnClickListener(this);
        btnMenosMasculinoAdultoMayorCiclista.setOnClickListener(this);
        btnMasMasculinoAdultoMayorCiclista.setOnClickListener(this);

        btnRegistrarNoMotorizado = findViewById(R.id.btnRegistrarNoMotorizado);
        btnRegistrarNoMotorizado.setOnClickListener(this);
    }

    /**
     * Inicializa los contadores de la vista
     */
    private void inicializarTextos() {
        txtFemeninoNino = findViewById(R.id.txtFemeninoNino);
        txtFemeninoAdulto = findViewById(R.id.txtFemeninoAdulto);
        txtFemeninoAdultoMayor = findViewById(R.id.txtFemeninoAdultoMayor);
        txtFemeninoMovilidadCondicionada = findViewById(R.id.txtFemeninoMovilidadCondicionada);

        txtMasculinoNino = findViewById(R.id.txtMasculinoNino);
        txtMasculinoAdulto = findViewById(R.id.txtMasculinoAdulto);
        txtMasculinoAdultoMayor = findViewById(R.id.txtMasculinoAdultoMayor);
        txtMasculinoMovilidadCondicionada = findViewById(R.id.txtMasculinoMovilidadCondicionada);

        txtFemeninoNinoCiclista = findViewById(R.id.txtFemeninoNinoCiclista);
        txtFemeninoAdultoCiclista = findViewById(R.id.txtFemeninoAdultoCiclista);
        txtFemeninoAdultoMayorCiclista = findViewById(R.id.txtFemeninoAdultoMayorCiclista);

        txtMasculinoNinoCiclista = findViewById(R.id.txtMasculinoNinoCiclista);
        txtMasculinoAdultoCiclista = findViewById(R.id.txtMasculinoAdultoCiclista);
        txtMasculinoAdultoMayorCiclista = findViewById(R.id.txtMasculinoAdultoMayorCiclista);
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

    /**
     * Retorna la sumatoria de los peatones femeninos
     */
    private int subtotalFemeninoPeatones() {
        int ninas = Integer.parseInt(txtFemeninoNino.getText().toString());
        int adultas = Integer.parseInt(txtFemeninoAdulto.getText().toString());
        int mayores = Integer.parseInt(txtFemeninoAdultoMayor.getText().toString());
        int movilidad = Integer.parseInt(txtFemeninoMovilidadCondicionada.getText().toString());

        return ninas + adultas + mayores + movilidad;
    }

    /**
     * Retorna la sumatoria de los peatones masculinos
     */
    private int subtotalMasculinoPeatones() {
        int ninos = Integer.parseInt(txtMasculinoNino.getText().toString());
        int adultos = Integer.parseInt(txtMasculinoAdulto.getText().toString());
        int mayores = Integer.parseInt(txtMasculinoAdultoMayor.getText().toString());
        int movilidad = Integer.parseInt(txtMasculinoMovilidadCondicionada.getText().toString());

        return ninos + adultos + mayores + movilidad;
    }

    /**
     * Retorna la sumatoria de los ciclistas femeninos
     */
    private int subtotalFemeninoCiclistas() {
        int ninos = Integer.parseInt(txtFemeninoNinoCiclista.getText().toString());
        int adultos = Integer.parseInt(txtFemeninoAdultoCiclista.getText().toString());
        int mayores = Integer.parseInt(txtFemeninoAdultoMayorCiclista.getText().toString());

        return ninos + adultos + mayores;
    }

    /**
     * Retorna la sumatoria de los ciclistas femeninos
     */
    private int subtotalMasculinoCiclistas() {
        int ninos = Integer.parseInt(txtMasculinoNinoCiclista.getText().toString());
        int adultos = Integer.parseInt(txtMasculinoAdultoCiclista.getText().toString());
        int mayores = Integer.parseInt(txtMasculinoAdultoMayorCiclista.getText().toString());

        return ninos + adultos + mayores;
    }

    /**
     * Deja en blanco los campos después de realizar un registro
     */
    private void reiniciar() {
        txtFemeninoNino.setText("0");
        txtFemeninoAdulto.setText("0");
        txtFemeninoAdultoMayor.setText("0");
        txtFemeninoMovilidadCondicionada.setText("0");

        txtMasculinoNino.setText("0");
        txtMasculinoAdulto.setText("0");
        txtMasculinoAdultoMayor.setText("0");
        txtMasculinoMovilidadCondicionada.setText("0");

        txtFemeninoNinoCiclista.setText("0");
        txtFemeninoAdultoCiclista.setText("0");
        txtFemeninoAdultoMayorCiclista.setText("0");

        txtMasculinoNinoCiclista.setText("0");
        txtMasculinoAdultoCiclista.setText("0");
        txtMasculinoAdultoMayorCiclista.setText("0");
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
    public Map obtenerDatos() {
        String[] preferencias =
                Servicios.obtenerPreferencias(getSharedPreferences("generales", Context.MODE_PRIVATE));

        String fNino = txtFemeninoNino.getText().toString();
        String fAdulto = txtFemeninoAdulto.getText().toString();
        String fAdultoMayor = txtFemeninoAdultoMayor.getText().toString();
        String fMovilidad = txtFemeninoMovilidadCondicionada.getText().toString();
        String subtotalFemeninoPeatones = String.valueOf(subtotalFemeninoPeatones());

        String mNino = txtMasculinoNino.getText().toString();
        String mAdulto = txtMasculinoAdulto.getText().toString();
        String mAdultoMayor = txtMasculinoAdultoMayor.getText().toString();
        String mMovilidad = txtMasculinoMovilidadCondicionada.getText().toString();
        String subtotalMasculinoPeatones = String.valueOf(subtotalMasculinoPeatones());

        String subtotalPeatones = String.valueOf(subtotalFemeninoPeatones() + subtotalMasculinoPeatones());

        String fNinoCiclista = txtFemeninoNinoCiclista.getText().toString();
        String fAdultoCiclista = txtFemeninoAdultoCiclista.getText().toString();
        String fAdultoMayorCiclista = txtFemeninoAdultoMayorCiclista.getText().toString();
        String subtotalFemeninoCiclistas = String.valueOf(subtotalFemeninoCiclistas());

        String mNinoCiclista = txtMasculinoNinoCiclista.getText().toString();
        String mAdultoCiclista = txtMasculinoAdultoCiclista.getText().toString();
        String mAdultoMayorCiclista = txtMasculinoAdultoMayorCiclista.getText().toString();
        String subtotalMasculinoCiclistas = String.valueOf(subtotalMasculinoCiclistas());

        String subtotalCiclistas = String.valueOf(subtotalFemeninoCiclistas() + subtotalMasculinoCiclistas());
        String subtotalNoMotorizado = String.valueOf(subtotalFemeninoPeatones() + subtotalMasculinoPeatones() + subtotalFemeninoCiclistas() + subtotalMasculinoCiclistas());

        Map<String, String> mapa = new HashMap<>();

        mapa.put("hoja","Volumen No Motorizado");
        mapa.put("aforador", preferencias[0]);
        mapa.put("ubicacion", preferencias[1]);
        mapa.put("fecha", preferencias[2]);
        mapa.put("dia", preferencias[3]);
        mapa.put("acceso", preferencias[4]);
        mapa.put("desde", horario[0]);
        mapa.put("hasta", horario[1]);

        mapa.put("pfnina", fNino);
        mapa.put("pfadulta", fAdulto);
        mapa.put("pfmayor", fAdultoMayor);
        mapa.put("pfmovilidad", fMovilidad);
        mapa.put("subtotalPF", subtotalFemeninoPeatones);
        mapa.put("pmnino", mNino);
        mapa.put("pmadulto", mAdulto);
        mapa.put("pmmayor", mAdultoMayor);
        mapa.put("pmmovilidad", mMovilidad);
        mapa.put("subtotalPM", subtotalMasculinoPeatones);
        mapa.put("subtotalPeatones", subtotalPeatones);
        mapa.put("cfnina", fNinoCiclista);
        mapa.put("cfadulta", fAdultoCiclista);
        mapa.put("cfmayor", fAdultoMayorCiclista);
        mapa.put("subtotalCF", subtotalFemeninoCiclistas);
        mapa.put("cmnino", mNinoCiclista);
        mapa.put("cmadulto", mAdultoCiclista);
        mapa.put("cmmayor", mAdultoMayorCiclista);
        mapa.put("subtotalCM", subtotalMasculinoCiclistas);
        mapa.put("subtotalCiclistas", subtotalCiclistas);
        mapa.put("subtotalNoMotorizado", subtotalNoMotorizado);

        return mapa;
    }

    /**
     * Método que envía el HTTP Request para añadir filas a la hoja de calculo en Drive.
     */
    private void registrar() {
        if(horario == null) {
            Toast.makeText(this, "Espera un poco, un poquito más.", Toast.LENGTH_LONG).show();
            return;
        }
        Map mapa = obtenerDatos();
        Servicios.enviarRequest(VolumenNoMotorizadoActivity.this, mapa);
        reiniciar();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnMasFemeninoNino: aumentarContador(txtFemeninoNino); break;
            case R.id.btnMasFemeninoAdulto: aumentarContador(txtFemeninoAdulto); break;
            case R.id.btnMasFemeninoAdultoMayor: aumentarContador(txtFemeninoAdultoMayor); break;
            case R.id.btnMasFemeninoMovilidadCondicionada: aumentarContador(txtFemeninoMovilidadCondicionada); break;
            case R.id.btnMasMasculinoNino: aumentarContador(txtMasculinoNino); break;
            case R.id.btnMasMasculinoAdulto: aumentarContador(txtMasculinoAdulto); break;
            case R.id.btnMasMasculinoAdultoMayor: aumentarContador(txtMasculinoAdultoMayor); break;
            case R.id.btnMasMasculinoMovilidadCondicionada: aumentarContador(txtMasculinoMovilidadCondicionada); break;
            case R.id.btnMasFemeninoNinoCiclista: aumentarContador(txtFemeninoNinoCiclista); break;
            case R.id.btnMasFemeninoAdultoCiclista: aumentarContador(txtFemeninoAdultoCiclista); break;
            case R.id.btnMasFemeninoAdultoMayorCiclista: aumentarContador(txtFemeninoAdultoMayorCiclista); break;
            case R.id.btnMasMasculinoNinoCiclista: aumentarContador(txtMasculinoNinoCiclista); break;
            case R.id.btnMasMasculinoAdultoCiclista: aumentarContador(txtMasculinoAdultoCiclista); break;
            case R.id.btnMasMasculinoAdultoMayorCiclista: aumentarContador(txtMasculinoAdultoMayorCiclista); break;

            case R.id.btnMenosFemeninoNino: disminuirContador(txtFemeninoNino); break;
            case R.id.btnMenosFemeninoAdulto: disminuirContador(txtFemeninoAdulto); break;
            case R.id.btnMenosFemeninoAdultoMayor: disminuirContador(txtFemeninoAdultoMayor); break;
            case R.id.btnMenosFemeninoMovilidadCondicionada: disminuirContador(txtFemeninoMovilidadCondicionada); break;
            case R.id.btnMenosMasculinoNino: disminuirContador(txtMasculinoNino); break;
            case R.id.btnMenosMasculinoAdulto: disminuirContador(txtMasculinoAdulto); break;
            case R.id.btnMenosMasculinoAdultoMayor: disminuirContador(txtMasculinoAdultoMayor); break;
            case R.id.btnMenosMasculinoMovilidadCondicionada: disminuirContador(txtMasculinoMovilidadCondicionada); break;
            case R.id.btnMenosFemeninoNinoCiclista: disminuirContador(txtFemeninoNinoCiclista); break;
            case R.id.btnMenosFemeninoAdultoCiclista: disminuirContador(txtFemeninoAdultoCiclista); break;
            case R.id.btnMenosFemeninoAdultoMayorCiclista: disminuirContador(txtFemeninoAdultoMayorCiclista); break;
            case R.id.btnMenosMasculinoNinoCiclista: disminuirContador(txtMasculinoNinoCiclista); break;
            case R.id.btnMenosMasculinoAdultoCiclista: disminuirContador(txtMasculinoAdultoCiclista); break;
            case R.id.btnMenosMasculinoAdultoMayorCiclista: disminuirContador(txtMasculinoAdultoMayorCiclista); break;

            case R.id.btnRegistrarNoMotorizado: registrar(); break;
        }
    }
}
