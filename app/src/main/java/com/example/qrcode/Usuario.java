package com.example.qrcode;

public class Usuario {
    private String username = "";
    private String password = "";

    public Usuario(String username,String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }
}
