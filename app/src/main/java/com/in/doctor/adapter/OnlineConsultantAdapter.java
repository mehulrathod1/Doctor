package com.in.doctor.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.in.doctor.R;
import com.in.doctor.model.OnlineConsultantModel;
import com.in.doctor.model.UpcomingAppointmentModel;

import java.util.List;

public class OnlineConsultantAdapter extends RecyclerView.Adapter<OnlineConsultantAdapter.ViewHolder> {
    //    List<OnlineConsultantModel.Consultant> list;
    List<UpcomingAppointmentModel.Upcoming> list;
    Context context;
    Click click;


    public interface Click {
        void onButtonClick(int position);
    }

    public OnlineConsultantAdapter(List<UpcomingAppointmentModel.Upcoming> list, Context context, Click click) {
        this.list = list;
        this.context = context;
        this.click = click;
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

        UpcomingAppointmentModel.Upcoming model = list.get(position);
        holder.BookingId.setText(model.getBooking_id());
        holder.DoctorName.setText(model.getPatient_name());
        holder.CityName.setText(model.getPatientAge() + "  year");
        holder.Price.setText(model.getFees() + " ₹");
        holder.Status.setText(model.getStatus());


        Glide.with(context)
                .load(model.getProfile_image())
                .into(holder.profileImage);

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
        TextView BookingId, DoctorName, CityName, Price, viewBookingDetail, Status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            profileImage = itemView.findViewById(R.id.profileImage);
            BookingId = itemView.findViewById(R.id.BookingId);
            DoctorName = itemView.findViewById(R.id.DoctorName);
            CityName = itemView.findViewById(R.id.CityName);
            Price = itemView.findViewById(R.id.Price);
            Status = itemView.findViewById(R.id.Status);

            viewBookingDetail = itemView.findViewById(R.id.viewBookingDetail);
        }
    }
}
