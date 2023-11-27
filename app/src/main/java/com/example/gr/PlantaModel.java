package com.example.gr;

public class PlantaModel {

    String localizacion, nombre, tipo;
    Double macetas;

    public PlantaModel(String localizacion, String nombre, String tipo, Double macetas) {
        this.localizacion = localizacion;
        this.nombre = nombre;
        this.tipo = tipo;
        this.macetas = macetas;
    }

    public PlantaModel() {
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getMacetas() {
        return macetas;
    }

    public void setMacetas(Double macetas) {
        this.macetas = macetas;
    }
}
