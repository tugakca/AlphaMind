package com.mobisem.engineering.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mobisem.engineering.R;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
       FirebaseDatabase firebaseDatabase;
       DatabaseReference myRef;
       String email,password;
       TextView signUpText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent= new Intent(getApplication(),MeditationActivity.class);
        startActivity(intent);

    }

}
