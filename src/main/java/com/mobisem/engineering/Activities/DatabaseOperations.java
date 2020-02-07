package com.mobisem.engineering.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobisem.engineering.MeditationApplication;
import com.mobisem.engineering.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DatabaseOperations extends AppCompatActivity {
    FirebaseFirestore db;
    FirebaseAuth mAuth;

    Date currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_operations);

        db = FirebaseFirestore.getInstance();
         mAuth = FirebaseAuth.getInstance();
         currentTime = Calendar.getInstance().getTime();
        addIntoDB();

    }


    public void addIntoDB() {

        int size = MeditationApplication.getInstance().getMeditationModelArrayList().size();

        HashMap<String, Object> hashMap = new HashMap<>();

        for (int i = 0; i < size; i++) {


            if (MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getID() == 0) {

                hashMap.put("base", MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getResult());
                hashMap.put("meditation ID",0);

            }

            if (MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getID() == 1) {

                hashMap.put("breathing", MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getResult());
                hashMap.put("meditation ID",1);

            }
            if (MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getID() == 2) {

                hashMap.put("mindless", MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getResult());
                hashMap.put("meditation ID",2);

            }
            if (MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getID() == 3) {

                hashMap.put("visulization", MeditationApplication.getInstance().getMeditationModelArrayList().get(i).getResult());
                hashMap.put("meditation ID",3);


            }


        }
        hashMap.put("uuid",mAuth.getCurrentUser().getUid());
        hashMap.put("time",currentTime);

        db.collection("subject").add(hashMap);









    }
}
