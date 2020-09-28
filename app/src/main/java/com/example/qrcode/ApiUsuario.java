package com.example.qrcode;

public class ApiUsuario {
    private String name;
    private Usuario param;

    public ApiUsuario(String name, Usuario usuario){
        this.name = name;
        this.param = usuario;
    }

    public String getName() {
        return this.name;
    }

    public Usuario getParam(){
        return this.param;
    }
}
