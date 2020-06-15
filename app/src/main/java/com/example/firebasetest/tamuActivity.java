package com.example.firebasetest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class tamuActivity extends AppCompatActivity {
    private TextView loginTamu;
    private Button button_tamu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamu);

        loginTamu = findViewById(R.id.idtamu);
        button_tamu= findViewById(R.id.btn_akses);

        button_tamu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference statustamu = database.getReference("tamu");

        statustamu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int tamu = dataSnapshot.getValue(int.class);
                IDtamu (tamu);
            }

            private void IDtamu (int tamu) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void aksesData(View view)
    { }
    public void loginData(View view)
    { }
}
