package com.example.qrcode;

import android.os.Parcel;
import android.os.Parcelable;

public class Licencia implements Parcelable {
    private String vigencia1 = "";
    private String vigencia2 = "";
    private String nombre_solicitante = "";
    private String domicilio_solicitante = "";
    private String ciudad_solicitante = "";
    private String telefono_solicitante;
    private int predial_obra;
    private String ubicaion_obra = "";
    private int superficie_obra;
    private String documentos_obra;
    private String fecha= "";
    private String status= "";
    private String nombre_suscriptor = "";
    private int numero_perito;
    private String domicilio_suscritor = "";
    private String observaciones = "";
    private int numero_licencia;
    private String image = "";
    private String pdf = "";
    private int numero_recibo;

    public Licencia(){}
    protected Licencia(Parcel in){
        vigencia1 = in.readString();
        vigencia2 = in.readString();
        nombre_solicitante = in.readString();
        domicilio_solicitante = in.readString();
        ciudad_solicitante = in.readString();
        telefono_solicitante = in.readString();
        predial_obra = in.readInt();
        ubicaion_obra = in.readString();
        superficie_obra = in.readInt();
        documentos_obra = in.readString();
        fecha = in.readString();
        status = in.readString();
        nombre_suscriptor = in.readString();
        numero_perito = in.readInt();
        domicilio_suscritor = in.readString();
        observaciones = in.readString();
        numero_licencia = in.readInt();
        image = in.readString();
        pdf = in.readString();
        numero_recibo = in.readInt();

    }
    public static final Parcelable.Creator<Licencia> CREATOR = new Parcelable.Creator<Licencia>() {
        @Override
        public Licencia createFromParcel(Parcel in) {
            return new Licencia(in);
        }

        @Override
        public Licencia[] newArray(int size) {
            return new Licencia[size];
        }
    };

    public int getNumero_recibo() {
        return numero_recibo;
    }

    public void setNumero_recibo(int numero_recibo) {
        this.numero_recibo = numero_recibo;
    }

    public String getVegencia1() {
        return vigencia1;
    }

    public void setVegencia1(String vegencia1) {
        this.vigencia1 = vegencia1;
    }

    public String getVigencia2() {
        return vigencia2;
    }

    public void setVigencia2(String vigencia2) {
        this.vigencia2 = vigencia2;
    }

    public String getNombre_solicitante() {
        return nombre_solicitante;
    }

    public void setNombre_solicitante(String nombre_solicitante) {
        this.nombre_solicitante = nombre_solicitante;
    }

    public String getDomicilio_solicitante() {
        return domicilio_solicitante;
    }

    public void setDomicilio_solicitante(String domicilio_solicitante) {
        this.domicilio_solicitante = domicilio_solicitante;
    }

    public String getCiudad_solicitante() {
        return ciudad_solicitante;
    }

    public void setCiudad_solicitante(String ciudad_solicitante) {
        this.ciudad_solicitante = ciudad_solicitante;
    }

    public String getTelefono_solicitante() {
        return telefono_solicitante;
    }

    public void setTelefono_solicitante(String telefono_solicitante) {
        this.telefono_solicitante = telefono_solicitante;
    }

    public int getPredial_obra() {
        return predial_obra;
    }

    public void setPredial_obra(int predial_obra) {
        this.predial_obra = predial_obra;
    }

    public String getUbicaion_obra() {
        return ubicaion_obra;
    }

    public void setUbicaion_obra(String ubicaion_obra) {
        this.ubicaion_obra = ubicaion_obra;
    }

    public int getSuperficie_obra() {
        return superficie_obra;
    }

    public void setSuperficie_obra(int superficie_obra) {
        this.superficie_obra = superficie_obra;
    }

    public String getDocumentos_obra() {
        return documentos_obra;
    }

    public void setDocumentos_obra(String documentos_obra) {
        this.documentos_obra = documentos_obra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNombre_suscriptor() {
        return nombre_suscriptor;
    }

    public void setNombre_suscriptor(String nombre_suscriptor) {
        this.nombre_suscriptor = nombre_suscriptor;
    }

    public int getNumero_perio() {
        return numero_perito;
    }

    public void setNumero_perio(int numero_perio) {
        this.numero_perito = numero_perio;
    }

    public String getDomicilio_suscritor() {
        return domicilio_suscritor;
    }

    public void setDomicilio_suscritor(String domicilio_suscritor) {
        this.domicilio_suscritor = domicilio_suscritor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getNumero_licencia() {
        return numero_licencia;
    }

    public void setNumero_licencia(int numero_licencia) {
        this.numero_licencia = numero_licencia;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(vigencia1);
        dest.writeString(vigencia2);
        dest.writeString(nombre_solicitante);
        dest.writeString(domicilio_solicitante);
        dest.writeString(ciudad_solicitante);
        dest.writeString(telefono_solicitante);
        dest.writeInt(predial_obra);
        dest.writeString(ubicaion_obra);
        dest.writeInt(superficie_obra);
        dest.writeString(documentos_obra);
        dest.writeString(fecha);
        dest.writeString(status);
        dest.writeString(nombre_suscriptor);
        dest.writeInt(numero_perito);
        dest.writeString(domicilio_suscritor);
        dest.writeString(observaciones);
        dest.writeInt(numero_licencia);
        dest.writeString(image);
        dest.writeString(pdf);
        dest.writeInt(numero_recibo);
    }
}
