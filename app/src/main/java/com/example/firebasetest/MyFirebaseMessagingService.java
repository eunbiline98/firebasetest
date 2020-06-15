package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        shortNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getTitle());
    }

    private void shortNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "myNotification")
                .setContentTitle(title)
                .setAutoCancel(true)
                .setContentText(message);
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(999, builder.build());
    }
}