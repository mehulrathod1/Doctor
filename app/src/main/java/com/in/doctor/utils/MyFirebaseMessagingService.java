package com.in.doctor.utils;

import static com.google.firebase.messaging.Constants.MessagePayloadKeys.SENDER_ID;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


    }

    @Override
    public void onDeletedMessages() {

    }

    @Override
    public void onMessageSent(String msgId) {

    }

    @Override
    public void onNewToken(String token) {

    }

    @Override
    public void onSendError(String msgId, Exception exception) {

    }

}
