package com.example.qrcode;

public class Evento {
    private int id_evento;
    private String fecha;
    private String color;
    private String redenring;

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRedenring() {
        return redenring;
    }

    public void setRedenring(String redenring) {
        this.redenring = redenring;
    }
}
