package com.in.doctor.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.in.doctor.R;
import com.in.doctor.model.ManageCalendarModel;
import com.in.doctor.model.OnlineConsultantModel;

import java.util.List;

public class OnlineConsultantAdapter extends RecyclerView.Adapter<OnlineConsultantAdapter.ViewHolder> {
    List<OnlineConsultantModel> list;
    Context context;
    Click click;

    public OnlineConsultantAdapter(List<OnlineConsultantModel> list, Context context, Click click) {
        this.list = list;
        this.context = context;
        this.click = click;
    }

    public interface Click {
        void onButtonClick(int position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.online_consultant_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        OnlineConsultantModel model = list.get(position);
        holder.BookingId.setText(model.getBookingId());
        holder.DoctorName.setText(model.getDoctorName());
        holder.CityName.setText(model.getCityName());
        holder.Price.setText(model.getPrice());

        holder.viewBookingDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onButtonClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView BookingId, DoctorName, CityName, Price,viewBookingDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            profileImage = itemView.findViewById(R.id.profileImage);
            BookingId = itemView.findViewById(R.id.BookingId);
            DoctorName = itemView.findViewById(R.id.DoctorName);
            CityName = itemView.findViewById(R.id.CityName);
            Price = itemView.findViewById(R.id.Price);
            viewBookingDetail = itemView.findViewById(R.id.viewBookingDetail);
        }
    }
}