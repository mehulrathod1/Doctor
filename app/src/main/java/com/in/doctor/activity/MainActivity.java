package com.in.doctor.activity;

import static com.in.doctor.global.Glob.Token;
import static com.in.doctor.global.Glob.user_id;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.in.doctor.R;
import com.in.doctor.global.Glob;
import com.in.doctor.model.CommonModel;
import com.in.doctor.model.SignUpModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnSignup;
    EditText edtFirstName, edtLastName, ediEmail, edtMobileNumber, edtPassword, edtConformPassword;
    ArrayAdapter<String> countryCodeAdapter;
    Spinner countryCode;
    List<String> countryCodeList;

    String TAG = "MainActivity";
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        init();

    }

    public void init() {

        btnSignup = findViewById(R.id.btnSignup);
        edtFirstName = findViewById(R.id.edtFirstName);
        countryCode = findViewById(R.id.countryCode);
        edtLastName = findViewById(R.id.edtLastName);
        ediEmail = findViewById(R.id.ediEmail);
        edtMobileNumber = findViewById(R.id.edtMobileNumber);
        edtPassword = findViewById(R.id.edtPassword);
        edtConformPassword = findViewById(R.id.edtConformPassword);

        Glob.progressDialog(this);

        countryCodeList = new ArrayList<>();
        countryCodeList.add("+91");
//        countryCodeList.add("+92");
//        countryCodeList.add("+93");
//        countryCodeList.add("+94");
//        countryCodeList.add("+91");
//        countryCodeList.add("+92");
//        countryCodeList.add("+93");
//        countryCodeList.add("+94");
//        countryCodeList.add("+91");
//        countryCodeList.add("+92");
//        countryCodeList.add("+930");
//        countryCodeList.add("+94");
//        countryCodeList.add("+91");
//        countryCodeList.add("+92");
//        countryCodeList.add("+93");
//        countryCodeList.add("+94");

        countryCodeAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_textview, countryCodeList);
        countryCodeAdapter.setDropDownViewResource(R.layout.dropdown_item);
        countryCode.setAdapter(countryCodeAdapter);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Intent intent = new Intent(getApplicationContext(), SignIn.class);
//                startActivity(intent);


                if (edtFirstName.getText().toString().equals("")) {
                    edtFirstName.setError("Please Enter FirstName");
                } else if (edtLastName.getText().toString().equals("")) {
                    edtLastName.setError("Please Enter LastName");
                } else if (ediEmail.getText().toString().equals("")) {
                    ediEmail.setError("Please Enter Email");
                } else if (edtMobileNumber.getText().toString().equals("")) {
                    edtMobileNumber.setError("Please Enter Mobile Number");
                } else if (edtPassword.getText().toString().equals("")) {
                    edtPassword.setError("Please Enter Password");
                } else if (edtConformPassword.getText().toString().equals("")) {
                    edtConformPassword.setError("Please Enter ConformPassword");
                } else {
                    signUp(Token, edtFirstName.getText().toString(), edtLastName.getText().toString(),
                            ediEmail.getText().toString(), countryCode.getSelectedItem().toString(),
                            edtMobileNumber.getText().toString(), edtPassword.getText().toString(),
                            edtConformPassword.getText().toString());
                }


            }
        });

    }

    public void signUp(String token, String first_name, String last_name,
                       String email, String country_code, String mobile_no,
                       String password, String confirm_password) {


        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();

        call.signUp(token, first_name, last_name, email, country_code, mobile_no, password, confirm_password).enqueue(new Callback<SignUpModel>() {
            @Override
            public void onResponse(Call<SignUpModel> call, Response<SignUpModel> response) {

                SignUpModel signUpModel = response.body();
                if (signUpModel.getStatus().equals("true")) {

                    Toast.makeText(getApplicationContext(), signUpModel.getMessage(), Toast.LENGTH_SHORT).show();

                    user_id = signUpModel.getSignUpModel().getUser_id();
                    Log.e(TAG, "onResponse: " + user_id + "-------" + signUpModel.getSignUpModel().getUser_id());

                    Glob.dialog.dismiss();
                    Intent intent = new Intent(getApplicationContext(), SignIn.class);
                    startActivity(intent);
                } else {
                    Glob.dialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpModel> call, Throwable t) {

            }
        });

    }
}
