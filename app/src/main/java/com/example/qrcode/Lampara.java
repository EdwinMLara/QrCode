package com.example.qrcode;

import java.util.ArrayList;

public class Lampara {
    private int id_lampara;
    private String descricion;
    private int status_lampara;
    private int control_manual;
    private ArrayList<Evento> Eventos;

    public int getId_lampara() {
        return id_lampara;
    }

    public void setId_lampara(int id_lampara) {
        this.id_lampara = id_lampara;
    }

    public String getDescricion() {
        return descricion;
    }

    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }

    public int getStatus_lampara() {
        return status_lampara;
    }

    public void setStatus_lampara(int status_lampara) {
        this.status_lampara = status_lampara;
    }

    public int getControl_manual() {
        return control_manual;
    }

    public void setControl_manual(int control_manual) {
        this.control_manual = control_manual;
    }

    public ArrayList<Evento> getEventos() {
        return Eventos;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        Eventos = eventos;
    }
}
