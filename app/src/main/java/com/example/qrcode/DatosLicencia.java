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

    private static final int REQUEST_CODE_QR_SCAN = 101;
    private static final int REQUEST_CODE_JPS_ACTIVITY = 102;
    public static final int SUCCESS_CODE = 200;
    private Licencia li;
    private Area area;

    public String id_hash = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_licencia);

        button = (Button) findViewById(R.id.button_scan);
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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatosLicencia.this, QrCodeActivity.class);
                startActivityForResult(intent, REQUEST_CODE_QR_SCAN);
            }
        });

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
        if(resultCode != Activity.RESULT_OK)
        {
            Log.d(LOGTAG,"COULD NOT GET A GOOD RESULT.");
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if( result!=null)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(DatosLicencia.this).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return;

        }
        if(requestCode == REQUEST_CODE_QR_SCAN)
        {
            if(data==null)
                return;
            String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
            /*Log.d(LOGTAG,"Have scan result in your app activity :"+ id_hash);
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Scan result");
            alertDialog.setMessage(result);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();*/
            getDataLicenced(result);

        }
    }

    private void getDataLicenced(String id_hash){
        Uriangatoservice service = (Uriangatoservice) UriangatoClassService.getService(id_hash);

        retrofit2.Call<Licencia> areacall = service.getLicencia();

        Log.d(LOGTAG,"prueba a ver que pedo");

        areacall.enqueue(new Callback<Licencia>() {
            @Override
            public void onResponse(retrofit2.Call<Licencia> call, Response<Licencia> response) {
                Log.d(LOGTAG,"reponse" + response.message());
                if (response.code() == SUCCESS_CODE) {
                    li = response.body();
                    Nombre.setText(li.getNombre_solicitante());
                    Num_recibo.setText(String.valueOf(li.getNumero_licencia()));
                    Direccion_solicitante.setText(li.getDomicilio_solicitante() + " " + li.getCiudad_solicitante());
                    Vigencia1.setText(li.getVegencia1());
                    Vigencia2.setText(li.getVigencia2());
                    Predial_obra.setText(String.valueOf(li.getPredial_obra()));
                    Ubicaicon_obra.setText(li.getUbicaion_obra());
                    Superfice.setText(String.valueOf(li.getSuperficie_obra()));
                    Fecha.setText(li.getFecha());
                    Status.setText(li.getStatus());
                    Nombre_suscriptor.setText(li.getNombre_suscriptor());
                    Numero_perito.setText(String.valueOf(li.getNumero_perio()));
                    Domicilio_suscriptor.setText(li.getDomicilio_suscritor());
                    //Toast.makeText(MainActivity.this, area.getNombre_area(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DatosLicencia.this, "No hay respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Licencia> call, Throwable t) {
                Toast.makeText(DatosLicencia.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
