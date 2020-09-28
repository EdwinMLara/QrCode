package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
            Uriangatoservice service = (Uriangatoservice) UriangatoClassService.postCreateUserService();

            retrofit2.Call<ResponseBody> loginCall = service.getLogin(apiUsuario);

            loginCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                    Toast.makeText(Login.this, response.body().toString(),Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
