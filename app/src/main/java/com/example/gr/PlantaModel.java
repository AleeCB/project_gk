package com.example.gr;

public class PlantaModel {

    String localizacion, macetas, nombre, tipo, img;

    public PlantaModel() {
    }

    public PlantaModel(String localizacion, String macetas, String nombre, String tipo, String img) {
        this.localizacion = localizacion;
        this.macetas = macetas;
        this.nombre = nombre;
        this.tipo = tipo;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImagenPath() {

        return "imagenes_planta/" + img;
    }
}
