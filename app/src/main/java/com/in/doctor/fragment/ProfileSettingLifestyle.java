package com.in.doctor.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.in.doctor.R;
import com.in.doctor.global.Glob;
import com.in.doctor.model.PersonalSettingModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileSettingLifestyle extends Fragment {

    View view;
    Spinner spnSmoking, spnAlcohol, spnWorkoutLevel;
    EditText edtSportInvolvement;
    ArrayAdapter<String> smokingAdapter, alcoholAdapter, workoutAdapter;
    List<String> smokingList, alcoholList, workoutList;

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

        return view;
    }

    public void init() {

        spnSmoking = view.findViewById(R.id.spnSmoking);
        spnAlcohol = view.findViewById(R.id.spnAlcohol);
        spnWorkoutLevel = view.findViewById(R.id.spnWorkoutLevel);
        edtSportInvolvement = view.findViewById(R.id.edtSportInvolvement);

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


        workoutAdapter = new ArrayAdapter<String>(getContext(), R.layout.profile_spinner_text, alcoholList);
        workoutAdapter.setDropDownViewResource(R.layout.dropdown_item);
        spnWorkoutLevel.setAdapter(workoutAdapter);

    }

}