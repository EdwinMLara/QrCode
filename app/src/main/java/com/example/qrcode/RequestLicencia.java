package com.example.qrcode;

public class RequestLicencia {
    private String numero_recibo = "";

    public RequestLicencia(String numero_recibo){
        this.numero_recibo = numero_recibo;
    }

    public String getNumero_recibo() {
        return this.numero_recibo;
    }
}
