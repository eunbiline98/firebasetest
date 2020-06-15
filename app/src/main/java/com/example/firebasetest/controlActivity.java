package com.example.firebasetest;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class controlActivity extends AppCompatActivity {

    private DatabaseReference PintuFirebaseDatabase;
    private FirebaseDatabase PintuFirebaseInstance;

    private DatabaseReference IDFirebaseDatabase;
    private FirebaseDatabase IDFirebaseInstance;

    private TextView KeadaanPintu;

    EditText addrField;
    Button btnConnect;
    VideoView streamView;
    MediaController mediaController;


     @Override
    protected void onCreate(Bundle savedInstanceState) {

         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_control);

         addrField = (EditText) findViewById(R.id.addr);
         btnConnect = (Button) findViewById(R.id.connect);
         streamView = (VideoView) findViewById(R.id.streamview);

         addrField = (EditText)findViewById(R.id.addr);
         btnConnect = (Button)findViewById(R.id.connect);
         streamView = (VideoView)findViewById(R.id.streamview);

         btnConnect.setOnClickListener(new View.OnClickListener(){

             @Override
             public void onClick(View v) {
                 String s = addrField.getEditableText().toString();
                 playStream(s);
             }});

         KeadaanPintu = findViewById(R.id.keadaan_pintu);
         // pintu control
         PintuFirebaseInstance = FirebaseDatabase.getInstance();
         PintuFirebaseDatabase = PintuFirebaseInstance.getReference("control");
         // id random akses
         IDFirebaseInstance = FirebaseDatabase.getInstance();
         IDFirebaseDatabase = IDFirebaseInstance.getReference("tamu");
         // kondisi pintu
         FirebaseDatabase database = FirebaseDatabase.getInstance();
         DatabaseReference statusref = database.getReference("control");


         statusref.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 int nilai = dataSnapshot.getValue(int.class);
                 KeadaanPintu (nilai);
             }

             private void KeadaanPintu(int nilai) {
                 if(nilai == 1) {
                     KeadaanPintu.setText("Terbuka");
                 } else {
                     KeadaanPintu.setText("Tertutup");
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });
     }

    private void playStream(String src){
        Uri UriSrc = Uri.parse(src);
        if(UriSrc == null){
            Toast.makeText(controlActivity.this,
                    "UriSrc == null", Toast.LENGTH_LONG).show();
        }else{
            streamView.setVideoURI(UriSrc);
            mediaController = new MediaController(this);
            streamView.setMediaController(mediaController);
            streamView.start();

            Toast.makeText(controlActivity.this,
                    "Connect: " + src,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        streamView.stopPlayback();
    }

    public void bukaData(View view)
    {PintuFirebaseDatabase.setValue(1);
        //KeadaanPintu.setText("Terbuka");
    }

    public void tutupData(View view)
    {PintuFirebaseDatabase.setValue(0);
        //KeadaanPintu.setText("Tertutup");
    }

    public void aksesIzinData(View view)
    {
        final Random myRandom = new Random();
        IDFirebaseDatabase.setValue(myRandom.nextInt(10000));
    }


}

