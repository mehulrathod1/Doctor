package com.in.doctor.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.in.doctor.R;
import com.in.doctor.activity.ChatDashboard;
import com.in.doctor.adapter.ChatAdapter;
import com.in.doctor.global.Glob;
import com.in.doctor.model.ChatModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chat extends Fragment {

    ChatAdapter adapter;
    RecyclerView recyclerView;
    List<ChatModel.ChatDoctorList> chatList = new ArrayList<>();
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        init();
        getChatList(Glob.Token, Glob.user_id);
        return view;
    }

    public void init() {

        Glob.progressDialog(getActivity());

        recyclerView = view.findViewById(R.id.recycler);

    }

    public void getChatList(String token, String user_id) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();


        call.getChatList(token, user_id).enqueue(new Callback<ChatModel>() {
            @Override
            public void onResponse(Call<ChatModel> call, Response<ChatModel> response) {

                ChatModel chatModel = response.body();

                List<ChatModel.ChatDoctorList> dataList = chatModel.getChatDoctorLists();


                for (int i = 0; i < dataList.size(); i++) {

                    ChatModel.ChatDoctorList model = dataList.get(i);
                    ChatModel.ChatDoctorList data = new ChatModel.ChatDoctorList(model.getUser_id(),
                            model.getUser_name(), model.getProfile_image(), model.getMessage());

                    chatList.add(data);
                    Glob.dialog.dismiss();
                }
                recyclerData();

            }

            @Override
            public void onFailure(Call<ChatModel> call, Throwable t) {

                Log.e("onFailure", "onFailure: " + t.getMessage());
                Glob.dialog.dismiss();

            }
        });

    }

    public void recyclerData() {


        adapter = new ChatAdapter(chatList, getContext(), new ChatAdapter.Click() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), ChatDashboard.class);
                intent.putExtra("user_id", chatList.get(position).getUser_id());
                intent.putExtra("user_name", chatList.get(position).getUser_name());
                intent.putExtra("user_image", chatList.get(position).getProfile_image());
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

}