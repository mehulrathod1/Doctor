package com.in.doctor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.in.doctor.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnSignup;
    EditText EdtFirstName;

    ArrayAdapter<String> countryCodeAdapter;
    Spinner countryCode;
    List<String> countryCodeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        init();


    }

    public void init() {

        btnSignup = findViewById(R.id.btnSignup);
        EdtFirstName = findViewById(R.id.EdtFirstName);
        countryCode = findViewById(R.id.countryCode);


        countryCodeList = new ArrayList<>();
        countryCodeList.add("+91");
        countryCodeList.add("+92");
        countryCodeList.add("+93");
        countryCodeList.add("+94");
        countryCodeList.add("+91");
        countryCodeList.add("+92");
        countryCodeList.add("+93");
        countryCodeList.add("+94");
        countryCodeList.add("+91");
        countryCodeList.add("+92");
        countryCodeList.add("+930");
        countryCodeList.add("+94");
        countryCodeList.add("+91");
        countryCodeList.add("+92");
        countryCodeList.add("+93");
        countryCodeList.add("+94");

        countryCodeAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_textview, countryCodeList);
        countryCodeAdapter.setDropDownViewResource(R.layout.dropdown_item);
        countryCode.setAdapter(countryCodeAdapter);





        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);

            }
        });
    }
}
