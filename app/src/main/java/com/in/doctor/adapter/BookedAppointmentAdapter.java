package com.in.doctor.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.in.doctor.R;
import com.in.doctor.model.BookedAppointmentModel;

import java.util.List;

public class BookedAppointmentAdapter extends RecyclerView.Adapter<BookedAppointmentAdapter.viewHolder> {

    List<BookedAppointmentModel> list;
    Context context;
    Click click;

    public BookedAppointmentAdapter(List<BookedAppointmentModel> list, Context context, Click click) {
        this.list = list;
        this.context = context;
        this.click = click;
    }

    public interface Click {
        void onButtonClick(int position);
    }


    @NonNull
    @Override
    public BookedAppointmentAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booking_appointment_item, parent, false);
        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookedAppointmentAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {

        BookedAppointmentModel model = list.get(position);

        holder.BookingId.setText(model.getBookingId());
        holder.DoctorName.setText(model.getDoctorName());
        holder.Location.setText(model.getLocation());
        holder.Amount.setText(model.getAmount());

        holder.ViewDetail.setOnClickListener(new View.OnClickListener() {
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

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView BookingId, DoctorName, Location, Amount;
        ImageView profileImage, ViewDetail;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.profileImage);
            ViewDetail = itemView.findViewById(R.id.ViewDetail);

            BookingId = itemView.findViewById(R.id.BookingId);
            DoctorName = itemView.findViewById(R.id.DoctorName);
            Location = itemView.findViewById(R.id.Location);
            Amount = itemView.findViewById(R.id.Amount);
        }
    }
}
