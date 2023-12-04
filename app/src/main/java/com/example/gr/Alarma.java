package com.example.gr;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Alarma {
    private String nombre;
    private Calendar fechaHora;

    public Alarma(String nombre, Calendar fechaHora) {
        this.nombre = nombre;
        this.fechaHora = fechaHora;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaHoraString() {
        if (fechaHora != null) {
            Date date = fechaHora.getTime();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
            return format.format(date);
        } else {
            return "Fecha y hora no disponibles";
        }
    }



    public Calendar getFechaHora() {
        return fechaHora;
    }
}

