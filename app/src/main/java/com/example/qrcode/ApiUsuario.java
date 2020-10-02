package com.example.qrcode;

public class ApiUsuario <T> {
    private String name;
    private T param;

    public ApiUsuario(String name, T params){
        this.name = name;
        this.param = params;
    }

    public String getName() {
        return this.name;
    }

    public T getParam(){
        return this.param;
    }
}
