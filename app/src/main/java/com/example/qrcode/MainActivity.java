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

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QR_SCAN = 101;
    private Boolean isLogged = false;
    private String token = null;

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intentGetToken = getIntent();
        if(intentGetToken.hasExtra("token")){
            isLogged = intentGetToken.getBooleanExtra("isLogged",false);
            token = intentGetToken.getStringExtra("token");

            button = (Button) findViewById(R.id.button_scan);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, QrCodeActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_QR_SCAN);
                }
            });
        }

        if(!isLogged){
            Intent LoginIntend = new Intent(MainActivity.this,Login.class);
            startActivity(LoginIntend);
            finish();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode != Activity.RESULT_OK)
        {
            if(data==null)
                return;

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
            getDataLicenced(result);

        }
    }

    private void getDataLicenced(String id_hash){
        Uriangatoservice service = (Uriangatoservice) UriangatoClassService.getService(this.token);

        retrofit2.Call<Licencia> LicenciaCall = service.getLicencia();

        LicenciaCall.enqueue(new Callback<Licencia>() {
            @Override
            public void onResponse(retrofit2.Call<Licencia> call, Response<Licencia> response) {

            }

            @Override
            public void onFailure(Call<Licencia> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
