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
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
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
            Log.v(TAG,result);
            getDataLicenced(result);

        }
    }

    private void getDataLicenced(String id_hash){

        RequestLicencia requestLicencia = new RequestLicencia(id_hash);
        ApiUsuario apiUsuario = new ApiUsuario("contructionData",requestLicencia);
        Uriangatoservice service = (Uriangatoservice) UriangatoClassService.getLicenciaByToken(this.token);

        retrofit2.Call<JsonObject> LicenciaCall = service.getLicenciaByToken(apiUsuario);

        LicenciaCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(retrofit2.Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject responseJson = response.body();

                if(responseJson.has("error")){
                    JsonObject errorJson = responseJson.getAsJsonObject("error");
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("status");
                    alertDialog.setMessage(errorJson.get("message").getAsString());
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

                if(responseJson.has("response")){
                    Log.v(TAG,"Tiene respuesta");
                    JsonObject responseServer = responseJson.getAsJsonObject("response");
                    int status = responseServer.get("status").getAsInt();
                    if (status == 200) {

                        JsonObject jsonResult = responseServer.getAsJsonObject("result");
                        if(jsonResult != null){
                            JsonObject jsonLicencia = jsonResult.getAsJsonObject("licencia");
                            Intent intentDatosLicencia = new Intent(MainActivity.this,DatosLicencia.class);
                            intentDatosLicencia.putExtra("jsonStringLicencia",jsonLicencia.toString());
                            startActivity(intentDatosLicencia);
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
