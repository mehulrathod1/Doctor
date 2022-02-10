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

import com.in.doctor.R;
import com.in.doctor.model.CompleteAssignmentModel;

import java.util.List;

public class CompletedAssignmentAdapter extends RecyclerView.Adapter<CompletedAssignmentAdapter.ViewHolder> {

    List<CompleteAssignmentModel.Assignment> list;
    Context context;
    Click click;


    public CompletedAssignmentAdapter(List<CompleteAssignmentModel.Assignment> list, Context context, Click click) {
        this.list = list;
        this.context = context;
        this.click = click;
    }

    public interface Click {
        void onClickPrescriptionView(int position);

        void onClickPrescriptionDownload(int position);

        void onClickInvoiceView(int position);

        void onClickInvoiceDownload(int position);

        void onClickDocumentView(int position);

        void onClickDocumentDownload(int position);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.complete_assignment_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CompleteAssignmentModel.Assignment model = list.get(position);

        holder.BookingId.setText(model.getBooking_id());
        holder.AppointmentDate.setText(model.getAppointment_date());
        holder.AmountPaid.setText(model.getAmount_paid());


        holder.PrescriptionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onClickPrescriptionView(position);
            }
        });
        holder.PrescriptionDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onClickPrescriptionDownload(position);
            }
        });
        holder.InvoiceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onClickInvoiceView(position);
            }
        });
        holder.InvoiceDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onClickInvoiceDownload(position);
            }
        });
        holder.DocumentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onClickDocumentView(position);
            }
        });
        holder.DocumentDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onClickDocumentDownload(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView BookingId, AppointmentDate, AmountPaid;
        ImageView PrescriptionView, PrescriptionDownload, InvoiceView, InvoiceDownload, DocumentView, DocumentDownload;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            BookingId = itemView.findViewById(R.id.BookingId);
            AppointmentDate = itemView.findViewById(R.id.AppointmentDate);
            AmountPaid = itemView.findViewById(R.id.AmountPaid);

            PrescriptionView = itemView.findViewById(R.id.PrescriptionView);
            PrescriptionDownload = itemView.findViewById(R.id.PrescriptionDownload);
            InvoiceView = itemView.findViewById(R.id.InvoiceView);
            InvoiceDownload = itemView.findViewById(R.id.InvoiceDownload);
            DocumentView = itemView.findViewById(R.id.DocumentView);
            DocumentDownload = itemView.findViewById(R.id.DocumentDownload);
        }
    }
}
