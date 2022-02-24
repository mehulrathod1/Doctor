package com.in.doctor.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.in.doctor.R;
import com.in.doctor.activity.BookingDetail;
import com.in.doctor.activity.OnlineConsultants;
import com.in.doctor.adapter.ManageBookingAdapter;
import com.in.doctor.adapter.UpcomingAppointmentAdapter;
import com.in.doctor.global.Glob;
import com.in.doctor.model.UpcomingAppointmentModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeUpcoming extends Fragment {

    View view;
    RecyclerView upcomingRecycler;
    UpcomingAppointmentAdapter upcomingAppointmentAdapter;
    List<UpcomingAppointmentModel.Upcoming> list = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_upcoming, container, false);


        init();
        getAppointment(Glob.Token, Glob.user_id);
        return view;
    }

    public void init() {

        upcomingRecycler = view.findViewById(R.id.upcomingRecycler);

    }

    public void getAppointment(String token, String doctor_id) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);

        call.getAppointment(token, doctor_id).enqueue(new Callback<UpcomingAppointmentModel>() {
            @Override
            public void onResponse(Call<UpcomingAppointmentModel> call, Response<UpcomingAppointmentModel> response) {

                UpcomingAppointmentModel upcomingAppointmentModel = response.body();
                List<UpcomingAppointmentModel.Upcoming> dataList = upcomingAppointmentModel.getUpcomingList();

                if (dataList.size() != 0) {
                    for (int i = 0; i < dataList.size(); i++) {

                        UpcomingAppointmentModel.Upcoming model = dataList.get(i);

                        UpcomingAppointmentModel.Upcoming data = new UpcomingAppointmentModel.Upcoming(
                                model.getBooking_id(), model.getAppointment_date(), model.getAppointment_time(),
                                model.getPatient_id(), model.getBooking_of()
                        );

                        list.add(data);
                    }
                    recyclerData();
                }

            }


            @Override
            public void onFailure(Call<UpcomingAppointmentModel> call, Throwable t) {

                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void recyclerData() {


        upcomingAppointmentAdapter = new UpcomingAppointmentAdapter(list, getContext(), new UpcomingAppointmentAdapter.Click() {
            @Override
            public void onViewClick(int position) {

                String booking_Id = list.get(position).getBooking_id();

                Intent intent = new Intent(getContext(), BookingDetail.class);
                intent.putExtra("bookingId", booking_Id);
                startActivity(intent);
            }
        });


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        upcomingRecycler.setLayoutManager(mLayoutManager);
        upcomingRecycler.setNestedScrollingEnabled(false);
        upcomingRecycler.setAdapter(upcomingAppointmentAdapter);
    }
}