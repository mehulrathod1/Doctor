package com.in.doctor.fragment;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.in.doctor.R;
import com.in.doctor.global.Glob;
import com.in.doctor.model.PersonalSettingModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePersonal extends Fragment {

    View view;

    EditText edtFirstName, edtLastName, edtSpeciality, edtSubSpeciality, edtEducation, edtLanguageSpoken, edtExperience, edtAddress;
    ImageView profileImage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile_personal, container, false);
        init();
        doctorPersonal(Glob.Token, Glob.user_id);
        return view;
    }

    public void init() {

        edtFirstName = view.findViewById(R.id.edtFirstName);
        edtLastName = view.findViewById(R.id.edtLastName);
        edtSpeciality = view.findViewById(R.id.edtSpeciality);
        edtSubSpeciality = view.findViewById(R.id.edtSubSpeciality);
        edtEducation = view.findViewById(R.id.edtEducation);
        edtLanguageSpoken = view.findViewById(R.id.edtLanguageSpoken);
        edtExperience = view.findViewById(R.id.edtExperience);
        edtAddress = view.findViewById(R.id.edtAddress);
        profileImage = view.findViewById(R.id.profile_image);


        Glob.progressDialog(getContext());

    }

    public void doctorPersonal(String token, String user_id) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();

        call.doctorPersonal(token, user_id).enqueue(new Callback<PersonalSettingModel>() {
            @Override
            public void onResponse(Call<PersonalSettingModel> call, Response<PersonalSettingModel> response) {
                PersonalSettingModel profileSettingPersonal = response.body();
                Toast.makeText(getContext(), profileSettingPersonal.getMessage(), Toast.LENGTH_SHORT).show();

//                Toast.makeText(getContext(), profileSettingPersonal.getData().getFirst_name(), Toast.LENGTH_SHORT).show();


                Log.e("jhgfdsa", "onResponse: " + profileSettingPersonal.getData().getProfile_image());
                Glide.with(getContext())
                        .load(profileSettingPersonal.getData().getProfile_image())
                        .into(profileImage);

                edtFirstName.setText(profileSettingPersonal.getData().getFirst_name());
                edtLastName.setText(profileSettingPersonal.getData().getLast_name());
                edtSpeciality.setText(profileSettingPersonal.getData().getSpecialistid());
                edtEducation.setText(profileSettingPersonal.getData().getEducation());
                edtExperience.setText(profileSettingPersonal.getData().getExperience());
                edtLanguageSpoken.setText(profileSettingPersonal.getData().getLanguage_spoken());
                edtAddress.setText(profileSettingPersonal.getData().getAddress());
                Glob.dialog.dismiss();
            }

            @Override
            public void onFailure(Call<PersonalSettingModel> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: " + t.getMessage());

            }
        });
    }

}