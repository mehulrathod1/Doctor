package com.in.doctor.fragment;

import static com.in.doctor.global.Glob.Token;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.in.doctor.R;
import com.in.doctor.global.Glob;
import com.in.doctor.model.ClinicalSettingModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileSettingClinical extends Fragment {

    EditText edtClinicName, edtClinicLocation, edtFromToDays, edtOpenTime, edtCloseTime, edtConsultancyFees, edtAvailabilityStatus;
    Button btnSubmit;
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

        init();
        doctorClinic(Token, "13");

        return view;
    }

    public void init() {

        edtClinicName = view.findViewById(R.id.edtClinicName);
        edtClinicLocation = view.findViewById(R.id.edtClinicLocation);
        edtFromToDays = view.findViewById(R.id.edtFromToDays);
        edtOpenTime = view.findViewById(R.id.edtOpenTime);
        edtCloseTime = view.findViewById(R.id.edtCloseTime);
        edtConsultancyFees = view.findViewById(R.id.edtConsultancyFees);
        edtAvailabilityStatus = view.findViewById(R.id.edtAvailabilityStatus);
        btnSubmit = view.findViewById(R.id.btnSubmit);


    }


    public void doctorClinic(String token, String user_id) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);



        call.doctorClinic(token, user_id).enqueue(new Callback<ClinicalSettingModel>() {
            @Override
            public void onResponse(Call<ClinicalSettingModel> call, Response<ClinicalSettingModel> response) {

                ClinicalSettingModel model = response.body();

                Log.e("TAG", "onResponse: " + model.getData().getDoctor_id());

                edtClinicName.setText(model.getData().getClinic_name());
                edtClinicLocation.setText(model.getData().getClinic_location());
                edtFromToDays.setText(model.getData().getFrom_to_days());

                edtOpenTime.setText(model.getData().getOpen_time());
                edtCloseTime.setText(model.getData().getClose_time());
                edtConsultancyFees.setText(model.getData().getOfline_consultancy_fees());
                edtAvailabilityStatus.setText(model.getData().getDoctor_availability_status());


            }

            @Override
            public void onFailure(Call<ClinicalSettingModel> call, Throwable t) {

            }
        });
    }

}