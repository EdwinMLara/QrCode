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
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final String LOGTAG = "Scan";
    private Button button;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    public static final int SUCCESS_CODE = 200;
    private Licencia li;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button_scan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(MainActivity.this, QrCodeActivity.class);
                startActivityForResult(intent,REQUEST_CODE_QR_SCAN);*/
                Toast.makeText(MainActivity.this, "Solo es prueba", Toast.LENGTH_SHORT).show();
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
            //Getting the passed result
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
        Call<Licencia> licenciaCall = service.getLicencia();

        licenciaCall.enqueue(new Callback<Licencia>() {
            @Override
            public void onResponse(Call<Licencia> call, Response<Licencia> response) {
                if (response.code() == SUCCESS_CODE) {
                    li = response.body();
                    Toast.makeText(MainActivity.this, li.getCiudad_solicitante(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "No hay respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Licencia> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail response", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
