package com.in.doctor.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.in.doctor.R;
import com.in.doctor.global.Glob;
import com.in.doctor.model.PersonalSettingModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileSettingLifestyle extends Fragment {

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile_setting_lifestyle, container, false);
        return view;
    }


    public void doctorLifestyle(String token, String user_id) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();

        call.doctorPersonal(token, user_id).enqueue(new Callback<PersonalSettingModel>() {
            @Override
            public void onResponse(Call<PersonalSettingModel> call, Response<PersonalSettingModel> response) {
                PersonalSettingModel profileSettingPersonal = response.body();
                Toast.makeText(getContext(), profileSettingPersonal.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PersonalSettingModel> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });
    }

}