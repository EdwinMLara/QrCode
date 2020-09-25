package com.example.qrcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class JPS extends AppCompatActivity {
    public static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private Button buttonLocation;
    private TextView textViewLongitud;
    private TextView textViewLatitud;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_j_p_s);

        buttonLocation = (Button)findViewById(R.id.getCurrentLocation);
        textViewLatitud = (TextView)findViewById(R.id.Latitud);
        textViewLongitud = (TextView)findViewById(R.id.Longitud);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(JPS.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_LOCATION_PERMISSION);
                }else{
                    getCurrentLocation();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }else{
                Toast.makeText(this,"Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void getCurrentLocation(){
        progressBar.setVisibility(View.VISIBLE);

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(locationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(JPS.this)
                .requestLocationUpdates(locationRequest, new LocationCallback(){
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(JPS.this)
                                .removeLocationUpdates(this);
                        if(locationResult != null && locationResult.getLocations().size() > 0 ){
                            int lastestLocationIndex = locationResult.getLocations().size() -1;
                            double latitud = locationResult.getLocations().get(lastestLocationIndex).getLatitude();
                            double longitud = locationResult.getLocations().get(lastestLocationIndex).getLongitude();
                            textViewLatitud.setText(String.format("Latitud: %s",latitud));
                            textViewLongitud.setText(String.format("Logitud: %s",longitud));
                        }
                        progressBar.setVisibility(View.GONE);

                    }
                }, Looper.getMainLooper());

    }
}
