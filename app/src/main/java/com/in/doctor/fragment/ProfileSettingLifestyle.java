package com.in.doctor.fragment;

import static com.in.doctor.global.Glob.Token;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.in.doctor.R;
import com.in.doctor.global.Glob;
import com.in.doctor.model.ClinicalSettingModel;
import com.in.doctor.model.CommonModel;
import com.in.doctor.model.LifestyleSettingModel;
import com.in.doctor.model.PersonalSettingModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileSettingLifestyle extends Fragment {

    View view;
    Spinner spnSmoking, spnAlcohol, spnWorkoutLevel;
    EditText edtSportInvolvement;
    ArrayAdapter<String> smokingAdapter, alcoholAdapter, workoutAdapter;
    List<String> smokingList, alcoholList, workoutList;
    Button btnSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile_setting_lifestyle, container, false);
        init();
        doctorLifestyle(Token, Glob.user_id);

        return view;
    }

    public void init() {

        spnSmoking = view.findViewById(R.id.spnSmoking);
        spnAlcohol = view.findViewById(R.id.spnAlcohol);
        spnWorkoutLevel = view.findViewById(R.id.spnWorkoutLevel);
        edtSportInvolvement = view.findViewById(R.id.edtSportInvolvement);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        smokingList = new ArrayList<>();
        smokingList.add("yes");
        smokingList.add("No");


        smokingAdapter = new ArrayAdapter<String>(getContext(), R.layout.profile_spinner_text, smokingList);
        smokingAdapter.setDropDownViewResource(R.layout.dropdown_item);
        spnSmoking.setAdapter(smokingAdapter);

        alcoholList = new ArrayList<>();
        alcoholList.add("yes");
        alcoholList.add("No");


        alcoholAdapter = new ArrayAdapter<String>(getContext(), R.layout.profile_spinner_text, alcoholList);
        alcoholAdapter.setDropDownViewResource(R.layout.dropdown_item);
        spnAlcohol.setAdapter(alcoholAdapter);


        workoutList = new ArrayList<>();
        workoutList.add("High");
        workoutList.add("Medium");


        workoutAdapter = new ArrayAdapter<String>(getContext(), R.layout.profile_spinner_text, workoutList);
        workoutAdapter.setDropDownViewResource(R.layout.dropdown_item);
        spnWorkoutLevel.setAdapter(workoutAdapter);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                updateDoctorLifestyle(Token, Glob.user_id, spnSmoking.getSelectedItem().toString(),
                        spnAlcohol.getSelectedItem().toString(),
                        spnWorkoutLevel.getSelectedItem().toString(),
                        edtSportInvolvement.getText().toString());
            }
        });
    }


    public void doctorLifestyle(String token, String user_id) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);


        call.doctorLifestyle(token, user_id).enqueue(new Callback<LifestyleSettingModel>() {
            @Override
            public void onResponse(Call<LifestyleSettingModel> call, Response<LifestyleSettingModel> response) {

                LifestyleSettingModel lifestyleSettingModel = response.body();


                Log.e("TpiAG", "onResponse: " + lifestyleSettingModel.getData().getSmoking());
                Log.e("TpiAG", "onResponse: " + lifestyleSettingModel.getData().getAlchol());
                Log.e("TpiAG", "onResponse: " + lifestyleSettingModel.getData().getWorkout_level());
                Log.e("TpiAG", "onResponse: " + lifestyleSettingModel.getData().getSports_involvement());

                if (lifestyleSettingModel.getData().getSmoking().equals("No")) {
                    spnSmoking.setSelection(1);
                } else {
                    spnSmoking.setSelection(0);
                }
                if (lifestyleSettingModel.getData().getAlchol().equals("No")) {
                    spnAlcohol.setSelection(1);
                } else {
                    spnAlcohol.setSelection(0);
                }

                if (lifestyleSettingModel.getData().getWorkout_level().equals("High")) {
                    spnWorkoutLevel.setSelection(0);
                } else {
                    spnWorkoutLevel.setSelection(1);
                }

                edtSportInvolvement.setText(lifestyleSettingModel.getData().getSports_involvement());

            }

            @Override
            public void onFailure(Call<LifestyleSettingModel> call, Throwable t) {

            }
        });
    }

    public void updateDoctorLifestyle(String token, String doctor_id,
                                      String smoking, String alchol,
                                      String workout_level,
                                      String sports_involvement) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);

        call.updateDoctorLifestyle(token, doctor_id, smoking, alchol,
                workout_level, sports_involvement).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                CommonModel model = response.body();

                Toast.makeText(getContext(), "" + model.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {

            }
        });
    }

}