package com.example.gr.model;

public class SuculentaModel {

    String info, imagen;

    public SuculentaModel() {
    }

    public SuculentaModel(String info, String imagen) {
        this.info = info;
        this.imagen = imagen;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
