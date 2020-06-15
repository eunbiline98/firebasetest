package com.example.firebasetest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

//import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // deklarasi tombol
    private Button mBtn_user, mBtn_tamu;
    private static final String TAG = "MyFirebaseMsgService";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel=
                    new NotificationChannel("myNotification", "myNotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Success";
                        if (!task.isSuccessful()){
                            msg = "fail";
                        }
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        //inisial tombol
        mBtn_user = (Button)findViewById(R.id.btn_user);
        mBtn_tamu=(Button)findViewById(R.id.btn_tamu);

        // function tombol
        mBtn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login= new Intent(getApplicationContext(), loginActivity.class);
                startActivity(login);
            }
        });

        mBtn_tamu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tamu = new Intent(getApplicationContext(), tamuActivity.class);
                startActivity(tamu);
            }
        });

    }
}