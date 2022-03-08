package com.in.doctor.fragment;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.Toast;

import com.in.doctor.R;
import com.in.doctor.activity.CompletedAssignment;
import com.in.doctor.adapter.CompletedAssignmentAdapter;
import com.in.doctor.adapter.ManageBookingAdapter;
import com.in.doctor.global.Glob;
import com.in.doctor.model.CompleteAssignmentModel;
import com.in.doctor.model.ManageBookingModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeCompleted extends Fragment {

    View view;
    RecyclerView completedRecycler;
    ManageBookingAdapter adapter;
    List<CompleteAssignmentModel.Assignment> list = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_completed, container, false);

        init();
        getCompleteAssignment(Glob.Token, Glob.user_id);
        return view;
    }

    public void init() {

        completedRecycler = view.findViewById(R.id.completedRecycler);

    }

    public void getCompleteAssignment(String token, String doctorID) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);


        call.getCompletedAssignment(token, doctorID).enqueue(new Callback<CompleteAssignmentModel>() {
            @Override
            public void onResponse(Call<CompleteAssignmentModel> call, Response<CompleteAssignmentModel> response) {

                CompleteAssignmentModel model = response.body();


                List<CompleteAssignmentModel.Assignment> dataList = model.getCompleteAssignment();

                if (dataList.size() != 0) {
                    for (int i = 0; i < dataList.size(); i++) {

                        CompleteAssignmentModel.Assignment data = dataList.get(i);
                        CompleteAssignmentModel.Assignment dataa = new CompleteAssignmentModel.Assignment(data.getBooking_id(),
                                data.getAppointment_date(), data.getAmount_paid() + "  ₹", data.getPatient_id(), data.getPatient_document(), data.getInvoice(), data.getBooking_of(),
                                data.getPatient_name(),
                                data.getAppointment_time());
                        list.add(dataa);

                    }
                    recyclerData();
                }
            }

            @Override
            public void onFailure(Call<CompleteAssignmentModel> call, Throwable t) {

                Log.e("error", "onFailure: " + t.getMessage());

            }
        });
    }

    public void recyclerData() {


        adapter = new ManageBookingAdapter(list, getContext(), new ManageBookingAdapter.Click() {
            @Override
            public void onClickAccept(int position) {

                Intent intent = new Intent(getContext(), CompletedAssignment.class);
                startActivity(intent);

            }


        });


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        completedRecycler.setLayoutManager(mLayoutManager);
        completedRecycler.setNestedScrollingEnabled(false);
        completedRecycler.setAdapter(adapter);
    }


}