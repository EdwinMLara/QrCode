package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_SHORT;

public class Login extends AppCompatActivity {
    public static final String TAG = Login.class.getSimpleName();
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.v("TAG",TAG);

        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempLogin();
            }
        });
    }

    private void attempLogin(){
        editTextUsername.setError(null);
        editTextPassword.setError(null);

        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if(TextUtils.isEmpty(username)){
            editTextUsername.setError(getString(R.string.error_empty_username));
            focusView = editTextUsername;
            cancel = true;
        }

        if(TextUtils.isEmpty(password)){
            editTextPassword.setError(getString(R.string.error_empty_password));
            focusView = editTextPassword;
            cancel = true;
        }

        if(cancel){
            focusView.requestFocus();
        }else{
            Usuario usuario = new Usuario(username,password);
            ApiUsuario apiUsuario = new ApiUsuario("generateToken",usuario);
            Uriangatoservice service = UriangatoClassService.postCreateUserService();

            retrofit2.Call<JsonObject> loginCall = service.getLogin(apiUsuario);

            loginCall.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(retrofit2.Call<JsonObject> call, Response<JsonObject> response) {
                    JsonObject respondentsJson = response.body();
                    JsonObject responseServer = respondentsJson.getAsJsonObject("response");
                    Log.v(TAG,responseServer.toString());
                    int status  = responseServer.get("status").getAsInt();
                    if(status == 200){
                        JsonObject result = responseServer.getAsJsonObject("result");
                        token = result.get("token").getAsString();

                        Intent tokenLoginIntent = new Intent(Login.this,MainActivity.class);
                        tokenLoginIntent.putExtra("token",token);
                        tokenLoginIntent.putExtra("isLogged",true);
                        startActivity(tokenLoginIntent);
                    }
                }



                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(Login.this, t.getMessage(), LENGTH_SHORT).show();
                }
            });

        }
    }
}
