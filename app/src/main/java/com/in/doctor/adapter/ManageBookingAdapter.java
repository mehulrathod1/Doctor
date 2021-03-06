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
import com.in.doctor.model.CompleteAssignmentModel;
import com.in.doctor.model.ManageBookingModel;

import java.util.List;

public class ManageBookingAdapter extends RecyclerView.Adapter<ManageBookingAdapter.ViewHolder> {

    List<CompleteAssignmentModel.Assignment> list;
    Context context;
    Click click;

    public ManageBookingAdapter(List<CompleteAssignmentModel.Assignment> list, Context context, Click click) {
        this.list = list;
        this.context = context;
        this.click = click;
    }

    public interface Click {
        void onClickAccept(int position);


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

        CompleteAssignmentModel.Assignment model = list.get(position);

        holder.BookingId.setText(model.getBooking_id());
        holder.BookingDate.setText(model.getAppointment_date());
        holder.BookingOf.setText(model.getBooking_of());
        holder.BookingTime.setText(model.getAppointment_time());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onClickAccept(position);
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
