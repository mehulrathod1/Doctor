package com.in.doctor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.in.doctor.R;
import com.in.doctor.model.MyQuestionModel;

import java.util.List;

public class MyQuestionAdapter extends RecyclerView.Adapter<MyQuestionAdapter.ViewHolder> {

    private List<MyQuestionModel> list;

    public MyQuestionAdapter(List<MyQuestionModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_question_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MyQuestionModel model = list.get(position);
        holder.bind(model);

        holder.itemView.setOnClickListener(v -> {
            boolean expanded = model.isExpanded();
            model.setExpanded(!expanded);
            notifyItemChanged(position);
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Question, date, QuestionDescription, abc;
        View subItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Question = itemView.findViewById(R.id.Question);
            date = itemView.findViewById(R.id.Date);
            QuestionDescription = itemView.findViewById(R.id.QuestionDescription);
            abc = itemView.findViewById(R.id.abc);
            subItem = itemView.findViewById(R.id.sub_item);
        }

        private void bind(MyQuestionModel model) {
            boolean expanded = model.isExpanded();

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            Question.setText(model.getQuestion());
            date.setText(model.getDate());
            QuestionDescription.setText(model.getQuestionDescription());
            abc.setText(model.getAbc());


        }
    }


}
