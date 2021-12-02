package com.in.doctor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.in.doctor.R;

public class BookAppointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        getSupportActionBar().hide();
    }
}