package com.example.qrcode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatosLicencia extends AppCompatActivity {
    private static final String LOGTAG = "MAIN";
    private Button button,button_localizacion;
    private TextView Nombre;
    private TextView Num_recibo;
    private TextView Vigencia1;
    private TextView Vigencia2;
    private TextView Direccion_solicitante;

    private TextView Predial_obra,Ubicaicon_obra,Superfice,Fecha,Status;
    private TextView Nombre_suscriptor,Numero_perito,Domicilio_suscriptor;

    private static final int REQUEST_CODE_JPS_ACTIVITY = 102;
    public static final int SUCCESS_CODE = 200;
    private Licencia li;
    private Area area;

    public String id_hash = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_licencia);

        button_localizacion = (Button)findViewById(R.id.button_location);

        Nombre = (TextView) findViewById(R.id.Nombre);
        Num_recibo = (TextView) findViewById(R.id.num_recibo);
        Vigencia1 = (TextView) findViewById(R.id.vigencia1);
        Vigencia2 = (TextView) findViewById(R.id.vigencia2);
        Direccion_solicitante = (TextView) findViewById(R.id.direccion_solicitante);

        Predial_obra = (TextView) findViewById(R.id.predial_obra);
        Ubicaicon_obra = (TextView) findViewById(R.id.ubicacion_obra);
        Superfice = (TextView) findViewById(R.id.superficie);
        Fecha = (TextView) findViewById(R.id.fecha);
        Status = (TextView) findViewById(R.id.status);

        Nombre_suscriptor = (TextView) findViewById(R.id.nombre_suscriptor);
        Numero_perito = (TextView) findViewById(R.id.numero_perito);
        Domicilio_suscriptor = (TextView) findViewById(R.id.domicilio_suscriptor);


        button_localizacion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatosLicencia.this, JPS.class);
                startActivityForResult(intent, REQUEST_CODE_JPS_ACTIVITY);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode != Activity.RESULT_OK) {
            Log.d(LOGTAG, "COULD NOT GET A GOOD RESULT.");
            if (data == null)
                return;
        }
    }

}
