package edu.co.icesi.ams11chatbase.service;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FCMService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String msg = remoteMessage.getData().toString();

        ContextCompat.getMainExecutor(getApplicationContext()).execute(
                ()->{
                    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
                }
        );
    }
}
