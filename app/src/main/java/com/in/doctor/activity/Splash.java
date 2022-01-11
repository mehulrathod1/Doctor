package com.in.doctor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.in.doctor.R;
import com.in.doctor.global.Glob;

public class Splash extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT = 2000;
    SharedPreferences prefs;
    String TAG = "Splash";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();


        prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        String id = prefs.getString("id", "null");//"No name defined" is the default value.
        String token = prefs.getString("token", "null");
        String auth = prefs.getString("auth", "null");
//        prefs.edit().remove("auth");
        prefs.edit().commit();


        Log.e(TAG, "onCreate: " + id);
        Log.e(TAG, "onCreate: " + token);
        Log.e(TAG, "onCreate: " + auth);

        if (!id.equals(null)) {
            Glob.user_id = id;
        }


        if (auth.equals("null")) {
            moveNext(SignIn.class);

        }

        if (auth.equals("no")) {
            moveNext(Home.class);
        }

        if (auth.equals("yes")) {
            moveNext(Authentication.class);

        }

//        if (id.equals("null") && token.equals("null")) {
//            Log.e(TAG, "onCreate: " + "null");
//            moveNext(SignIn.class);
//        }
//        if (!id.equals("null") && !token.equals("null")) {
//            moveNext(Home.class);
//        } else if (!id.equals("null") && token.equals("null")) {
//            moveNext(SignIn.class);
//        }

    }

    public void moveNext(Class c) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), c);
                startActivity(i);
                finish();

            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}
