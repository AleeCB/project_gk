package com.example.gr;

public class PlantaModel {

    public byte[] getImageUrl;
    String localizacion, macetas, nombre, tipo, imageUrl;

    public PlantaModel() {
    }

    public PlantaModel(String localizacion, String macetas, String nombre, String tipo, String imageUrl) {
        this.localizacion = localizacion;
        this.macetas = macetas;
        this.nombre = nombre;
        this.tipo = tipo;
        this.imageUrl = imageUrl;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getMacetas() {
        return macetas;
    }

    public void setMacetas(String macetas) {
        this.macetas = macetas;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImagenPath() {

        return "imagenes_planta/" + imageUrl;
    }
}
