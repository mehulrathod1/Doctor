package com.in.doctor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.in.doctor.R;
import com.in.doctor.model.ChatDashboardModel;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.List;


public class ChatDashboardAdapter extends RecyclerView.Adapter<ChatDashboardAdapter.ViewHolder> {

    List<ChatDashboardModel.DashboardMessage> list;
    Context context;
    Click click;

    public interface Click {
        void onItemClick(int position);

        void onVideoSendView(int position);

        void onVideoReceivedView(int position);
    }

    public ChatDashboardAdapter(List<ChatDashboardModel.DashboardMessage> list, Context context, Click click) {
        this.list = list;
        this.context = context;
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_dashboard_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ChatDashboardModel.DashboardMessage model = list.get(position);

        String message = model.getMessage();
        String image = model.getChat_image();
        String video = model.getChat_video();


        if (model.getSend_by().equals("patient")) {

            holder.second_chat.setVisibility(View.GONE);
            holder.receivedMessage.setText(StringEscapeUtils.unescapeJava(model.getMessage()));
            holder.receivedMessageTime.setText(model.getTime());

        }


        if (model.getSend_by().equals("doctor")) {

            holder.first_chat.setVisibility(View.GONE);
            holder.send_message.setText(StringEscapeUtils.unescapeJava(model.getMessage()));
            holder.sendMessageTime.setText(model.getTime());

        }
        if (message.equals("") && video.equals("") && model.getSend_by().equals("doctor")) {

            holder.send_message.setVisibility(View.GONE);
            holder.sendImage.setVisibility(View.VISIBLE);
            Glide.with(context).load(model.getChat_image()).into(holder.sendImage);

        }
        if (message.equals("") && video.equals("") && model.getSend_by().equals("patient")) {

            holder.receivedMessage.setVisibility(View.GONE);
            holder.receivedImage.setVisibility(View.VISIBLE);
            Glide.with(context).load(model.getChat_image()).into(holder.receivedImage);

        }

        if (message.equals("") && image.equals("") && model.getSend_by().equals("patient")) {

            holder.receivedMessage.setVisibility(View.GONE);
            holder.receivedImage.setVisibility(View.GONE);
            holder.receivedVideo.setVisibility(View.VISIBLE);

        }
        if (message.equals("") && image.equals("") && model.getSend_by().equals("doctor")) {

            holder.send_message.setVisibility(View.GONE);
            holder.sendImage.setVisibility(View.GONE);
            holder.sendVideo.setVisibility(View.VISIBLE);

        }
        holder.sendVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onVideoSendView(position);
            }
        });

        holder.receivedVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onVideoReceivedView(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView receivedMessage, receivedMessageTime, send_message, sendMessageTime;
        RelativeLayout first_chat, second_chat;
        ImageView receivedImage, sendImage, receivedVideo, sendVideo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            receivedMessage = itemView.findViewById(R.id.receivedMessage);
            receivedMessageTime = itemView.findViewById(R.id.receivedMessageTime);
            send_message = itemView.findViewById(R.id.send_message);
            sendMessageTime = itemView.findViewById(R.id.sendMessageTime);

            first_chat = itemView.findViewById(R.id.first_chat);
            second_chat = itemView.findViewById(R.id.second_chat);

            receivedImage = itemView.findViewById(R.id.receivedImage);
            sendImage = itemView.findViewById(R.id.sendImage);

            receivedVideo = itemView.findViewById(R.id.receivedVideo);
            sendVideo = itemView.findViewById(R.id.sendVideo);

        }
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }
}
