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

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final String LOGTAG = "MAIN";
    private Button button;
    private TextView Nombre;
    private TextView Num_lamparas;
    private TextView Direccion_ip;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    public static final int SUCCESS_CODE = 200;
    private Licencia li;
    private Area area;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button_scan);
        Nombre = (TextView)findViewById(R.id.Nombre);
        Num_lamparas = (TextView)findViewById(R.id.num_lamparas);
        Direccion_ip = (TextView)findViewById(R.id.ip);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(MainActivity.this, QrCodeActivity.class);
                startActivityForResult(intent,REQUEST_CODE_QR_SCAN);*/
                //Toast.makeText(MainActivity.this, "Solo es prueba", Toast.LENGTH_SHORT).show();
                getDataLicencia();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode != Activity.RESULT_OK)
        {
            Log.d(LOGTAG,"COULD NOT GET A GOOD RESULT.");
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if( result!=null)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
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
            Log.d(LOGTAG,"Have scan result in your app activity :"+ result);
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Scan result");
            alertDialog.setMessage(result);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        }
    }

    private void getDataLicencia(){
        Uriangatoservice service = (Uriangatoservice) UriangatoClassService.getService();
        retrofit2.Call<Area> areacall = service.getLicencia();

        Log.d(LOGTAG,"prueba a ver que pedo");

        areacall.enqueue(new Callback<Area>() {
            @Override
            public void onResponse(retrofit2.Call<Area> call, Response<Area> response) {
                Log.d(LOGTAG,response.message());
                if (response.code() == SUCCESS_CODE) {
                    area = response.body();
                    Nombre.setText(area.getNombre_area());
                    Num_lamparas.setText(String.valueOf(area.getNum_lamparas()));
                    Direccion_ip.setText(area.getDireccion_ip());
                    //Toast.makeText(MainActivity.this, area.getNombre_area(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "No hay respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Area> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
