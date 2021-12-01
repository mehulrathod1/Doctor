package com.in.doctor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.in.doctor.R;
import com.in.doctor.model.ChatModel;
import com.in.doctor.model.DoctorConsultModel;

import java.util.List;

public class DoctorConsultantAdapter extends RecyclerView.Adapter<DoctorConsultantAdapter.ViewHolder> {
    List<DoctorConsultModel> list;
    Context context;
    Click click;

    public DoctorConsultantAdapter(List<DoctorConsultModel> list, Context context, Click click) {
        this.list = list;
        this.context = context;
        this.click = click;
    }

    public interface Click {
        void onItemClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor_counsult_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DoctorConsultModel model = list.get(position);

        holder.DoctorName.setText(model.getDoctorName());
        holder.exp.setText(model.getExp());
        holder.LikePercentage.setText(model.getLikePercentage());
        holder.Rate.setText(model.getRate());
        holder.speciality.setText(model.getSpeciality());
        holder.Location.setText(model.getLocation());

        holder.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView speciality, exp, Location, LikePercentage, Rate, DoctorName;
        ImageView profileImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            DoctorName = itemView.findViewById(R.id.DoctorName);
            speciality = itemView.findViewById(R.id.speciality);
            exp = itemView.findViewById(R.id.exp);
            Location = itemView.findViewById(R.id.Location);
            LikePercentage = itemView.findViewById(R.id.LikePercentage);
            Rate = itemView.findViewById(R.id.Rate);
            profileImage = itemView.findViewById(R.id.profileImage);
        }
    }
}
