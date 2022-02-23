package com.in.doctor.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.in.doctor.R;
import com.in.doctor.model.UpcomingAppointmentModel;

import java.util.List;

public class UpcomingAppointmentAdapter extends RecyclerView.Adapter<UpcomingAppointmentAdapter.ViewHolder> {


    List<UpcomingAppointmentModel.Upcoming> list;
    Context context;
    Click click;

    public interface Click {
        public void onViewClick(int position);
    }

    public UpcomingAppointmentAdapter(List<UpcomingAppointmentModel.Upcoming> list, Context context, Click click) {
        this.list = list;
        this.context = context;
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.manage_booking_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        UpcomingAppointmentModel.Upcoming model = list.get(position);

        holder.BookingDate.setText(model.getAppointment_date());
        holder.BookingTime.setText(model.getAppointment_time());
        holder.BookingId.setText(model.getBooking_id());
        holder.BookingOf.setText(model.getBooking_of());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onViewClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView BookingId, BookingDate, BookingOf, BookingTime;
        Button view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            BookingId = itemView.findViewById(R.id.BookingId);
            BookingDate = itemView.findViewById(R.id.OrderDate);
            BookingOf = itemView.findViewById(R.id.BookingOf);
            BookingTime = itemView.findViewById(R.id.BookingTime);

            view = itemView.findViewById(R.id.RequestAccept);
        }
    }
}
