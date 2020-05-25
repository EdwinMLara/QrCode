package com.example.qrcode;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.zxing.aztec.AztecReader;

import java.util.ArrayList;

public class Area implements Parcelable {
    private String nombre_area;
    private int num_lamparas;
    private int control_manual;
    private int control_calendario;
    private String direccion_ip;

    //private ArrayList<Lampara> Lamparas;

    protected Area(Parcel in) {
        nombre_area = in.readString();
        num_lamparas = in.readInt();
        control_manual = in.readInt();
        control_calendario = in.readInt();
        direccion_ip = in.readString();
    }

    public static final Parcelable.Creator<Area> CREATOR = new Parcelable.Creator<Area>() {
        @Override
        public Area createFromParcel(Parcel in) {
            return new Area(in);
        }

        @Override
        public Area[] newArray(int size) {
            return new Area[size];
        }
    };

    public String getNombre_area() {
        return nombre_area;
    }

    public void setNombre_area(String nombre_area) {
        this.nombre_area = nombre_area;
    }

    public int getNum_lamparas() {
        return num_lamparas;
    }

    public void setNum_lamparas(int num_lamparas) {
        this.num_lamparas = num_lamparas;
    }

    public int getControl_manual() {
        return control_manual;
    }

    public void setControl_manual(int control_manual) {
        this.control_manual = control_manual;
    }

    public int getControl_calendario() {
        return control_calendario;
    }

    public void setControl_calendario(int control_calendario) {
        this.control_calendario = control_calendario;
    }

    public String getDireccion_ip() {
        return direccion_ip;
    }

    public void setDireccion_ip(String direccion_ip) {
        this.direccion_ip = direccion_ip;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre_area);
        dest.writeInt(num_lamparas);
        dest.writeInt(control_manual);
        dest.writeInt(control_calendario);
        dest.writeString(direccion_ip);
    }
}
