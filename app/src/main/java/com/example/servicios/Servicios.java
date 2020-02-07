/*
 * Copyright (c) 2020. Aplicación desarrollada por Kevin Alejandro Hernández Rodríguez.
 * Ingeniero de sistemas - Universidad de Ibagué - Colombia
 * E-Mail: kevin.hernandezr8@gmail.com
 */

package com.example.servicios;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Date;
import java.util.Map;

public class Servicios {

    /**
     * URL del GoogleScript
     */
    static String webAppUrl = "https://script.google.com/macros/s/AKfycbzPKMUmJa6jPXTckCOPSx_ILlgzlplAiCFDTp7xe2x4AYj-EpE/exec";

    /**
     * Método que envía el HTTP Request para ingresar datos en la hoja de calculo de Drive.
     * @param context
     * @param parametros
     */
    public static void enviarRequest(final Context context, final Map parametros) {
        final ProgressDialog loading = ProgressDialog.show(context, "Enviando...", "Por favor espere");

        StringRequest request = new StringRequest(Request.Method.POST, webAppUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = parametros;
                return params;
            }
        };

        int socketTimeOut = 50000;

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) ;
        request.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(context);

        queue.add(request);
    }

    /**
     * Obtiene los datos guardados en las SharedPreferences
     * @return
     */
    public static String[] obtenerPreferencias(SharedPreferences preferences) {
        return new String[] {
                preferences.getString("aforador", ""),
                preferences.getString("ubicacion", ""),
                preferences.getString("fecha", ""),
                preferences.getString("dia", ""),
                preferences.getString("acceso", ""),
        };
    }

    /**
     * Retorna la hora de inicio y la hora final del aforo (Ej. de 6:30 hasta 6:45)
     */
    public static String[] obtenerHorario() {
        Date fecha = new Date();
        int hora = fecha.getHours();
        int minutos = fecha.getMinutes();

        String[] horario = new String[2];

        if ((minutos >= 0 && minutos < 15)) {
            horario[0] = hora + ":00";
            horario[1] = hora + ":15";
        } else if ((minutos >= 15 && minutos < 30)) {
            horario[0] = hora + ":15";
            horario[1] = hora + ":30";
        } else if ((minutos >= 30 && minutos < 45)) {
            horario[0] = hora + ":30";
            horario[1] = hora + ":45";
        } else if ((minutos >= 45)) {
            horario[0] = hora + ":45";
            horario[1] = (hora + 1) + ":00";
        }
        return horario;
    }
}
