package com.mobisem.engineering.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobisem.engineering.R;


import java.util.HashMap;

public class EnteranceActivity extends AppCompatActivity {
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    TextView enteranceButton;
    ImageView enteranceGuru;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterance);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        db=FirebaseFirestore.getInstance();
        enteranceButton=findViewById(R.id.enteranceButton);
        enteranceGuru=findViewById(R.id.enteranceGuru);

        enteranceGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteranceGuru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getApplicationContext(),AdapterActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });


        enteranceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), FirstProcess.class);
                startActivity(intent);
            }
        });

    }

    @Override

    public void onBackPressed() {
        if (true) {

        } else {
            super.onBackPressed();
        }
    }
}
