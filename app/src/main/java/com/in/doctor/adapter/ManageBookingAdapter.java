package com.in.doctor.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.in.doctor.R;
import com.in.doctor.model.ManageBookingModel;

import java.util.List;

public class ManageBookingAdapter extends RecyclerView.Adapter<ManageBookingAdapter.ViewHolder> {

    List<ManageBookingModel> list;
    Context context;
    Click click;

    public ManageBookingAdapter(List<ManageBookingModel> list, Context context, Click click) {
        this.list = list;
        this.context = context;
        this.click = click;
    }

    public interface Click {
        void onClickAccept(int position);

        void onClickCancel(int position);
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

        ManageBookingModel model = list.get(position);

        holder.BookingId.setText(model.getBookingId());
        holder.OrderDate.setText(model.getOrderDate());
        holder.BookingOf.setText(model.getBookingOf());
        holder.BookingTime.setText(model.getBookingTime());

        holder.RequestAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onClickAccept(position);
            }
        });

        holder.RequestCancel.setOnClickListener(new View.OnClickListener() {
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

        TextView BookingId, OrderDate, BookingOf, BookingTime;

        TextView RequestAccept, RequestCancel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            BookingId = itemView.findViewById(R.id.BookingId);
            OrderDate = itemView.findViewById(R.id.OrderDate);
            BookingOf = itemView.findViewById(R.id.BookingOf);
            BookingTime = itemView.findViewById(R.id.BookingTime);


            RequestCancel = itemView.findViewById(R.id.RequestCancel);
            RequestAccept = itemView.findViewById(R.id.RequestAccept);
        }
    }
}
