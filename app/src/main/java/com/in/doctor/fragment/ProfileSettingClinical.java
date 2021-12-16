package com.in.doctor.fragment;

import static com.in.doctor.global.Glob.Token;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.in.doctor.R;
import com.in.doctor.global.Glob;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileSettingClinical extends Fragment {

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile_setting_clinical, container, false);
        doctorClinical(Token,"13");
        return view;
    }

    public void doctorClinical(String token, String user_id) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();


        call.doctorClinical(token, user_id).enqueue(new Callback<ClinicSettingModel>() {
            @Override
            public void onResponse(Call<ClinicSettingModel> call, Response<ClinicSettingModel> response) {
                ClinicSettingModel clinicSettingModel = response.body();

                Toast.makeText(getContext(), clinicSettingModel.getMessage(), Toast.LENGTH_SHORT).show();

                Log.e("TAG", "onResponse: "+clinicSettingModel.getData().getClinic_name() );
            }

            @Override
            public void onFailure(Call<ClinicSettingModel> call, Throwable t) {

            }
        });

    }

}