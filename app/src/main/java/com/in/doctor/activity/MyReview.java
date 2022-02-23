package com.in.doctor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.in.doctor.R;
import com.in.doctor.adapter.MyReviewAdapter;
import com.in.doctor.global.Glob;
import com.in.doctor.model.MyReviewModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyReview extends AppCompatActivity {

    ImageView nevBack;
    TextView headerTitle;
    RecyclerView recyclerView;
    MyReviewAdapter reviewAdapter;
    List<MyReviewModel.ReviewData> reviewList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);
        getSupportActionBar().hide();
        init();
        getReview(Glob.Token, Glob.user_id);
    }


    public void init() {
        nevBack = findViewById(R.id.nevBack);
        headerTitle = findViewById(R.id.header_title);
        recyclerView = findViewById(R.id.recycler);

        headerTitle.setText("My Review");

        Glob.progressDialog(this);

        nevBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getReview(String token, String doctor_id) {
        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();

        call.getReview(token, doctor_id).enqueue(new Callback<MyReviewModel>() {
            @Override
            public void onResponse(Call<MyReviewModel> call, Response<MyReviewModel> response) {

                MyReviewModel myReviewModel = response.body();

                List<MyReviewModel.ReviewData> dataList = myReviewModel.getReviewDataList();

                for (int i = 0; i < dataList.size(); i++) {

                    MyReviewModel.ReviewData model = dataList.get(i);

                    MyReviewModel.ReviewData data = new MyReviewModel.ReviewData(model.getReview_id(), model.getPatient_id(),
                            model.getUserName(), model.getReview(), model.getRating(), model.getDate(), model.getProfile_image());


                    reviewList.add(data);
                    Collections.sort(reviewList, new Comparator<MyReviewModel.ReviewData>() {
                        @Override
                        public int compare(MyReviewModel.ReviewData o1, MyReviewModel.ReviewData o2) {
                            return o2.getRating().compareTo(o1.getRating());
                        }
                    });

                    Glob.dialog.dismiss();
                }
                reviewData();

            }

            @Override
            public void onFailure(Call<MyReviewModel> call, Throwable t) {

            }
        });
    }

    public void reviewData() {


        reviewAdapter = new MyReviewAdapter(reviewList, this, new MyReviewAdapter.Click() {
            @Override
            public void onItemClick(int position) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        reviewAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(reviewAdapter);
    }
}