package com.in.doctor.activity;

import static com.in.doctor.global.Glob.Token;
import static com.in.doctor.global.Glob.dialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.in.doctor.R;
import com.in.doctor.global.Glob;
import com.in.doctor.model.SignInModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {


    Button btnSignIn;
    EditText edtEmail, edtPassword;

    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    private Executor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();

        init();

        executor = ContextCompat.getMainExecutor(getApplicationContext());


        biometricPrompt = new BiometricPrompt(SignIn.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);

                Toast.makeText(getApplicationContext(), "" + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Faill", Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder().
                setTitle("Biometric Authentication")
                .setSubtitle("Login Fingure or Face")
                .setNegativeButtonText("cancel")
                .build();

    }

    public void init() {

        Glob.progressDialog(this);

        btnSignIn = findViewById(R.id.btnSignIn);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(getApplicationContext(), Home.class);
//                startActivity(intent);
                biometricPrompt.authenticate(promptInfo);
//                signInUser(Token, edtEmail.getText().toString(), edtPassword.getText().toString());
            }
        });
    }

    public void signInUser(String token, String email, String password) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();

        call.SignIn(token, email, password).enqueue(new Callback<SignInModel>() {
            @Override
            public void onResponse(Call<SignInModel> call, Response<SignInModel> response) {

                SignInModel model = response.body();

                if (model.getStatus().equals("true")) {
                    Toast.makeText(getApplicationContext(), model.getMessage(), Toast.LENGTH_SHORT).show();
                    Glob.dialog.dismiss();
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), model.getMessage(), Toast.LENGTH_SHORT).show();
                    Glob.dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<SignInModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: " + t.getMessage());
                Glob.dialog.dismiss();
            }
        });

    }

}