package com.mobisem.engineering.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobisem.engineering.R;
import com.mobisem.engineering.Connection.TcpClient;
import com.mobisem.engineering.Utils.CommonUtils;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.HashMap;

public class MeditationActivity extends AppCompatActivity {
    TextView mediationStartButton,meditation1,meditation2,meditation3;
    int meditationID;
    TextView instruction;
    TcpClient tcpClient;
    ImageView guruIcon;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    boolean isMeditation=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);
        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        init();
        actions();
    }

    public void init(){
        mediationStartButton=findViewById(R.id.meditationStartButton);
        meditation1=findViewById(R.id.MT1);
        meditation2=findViewById(R.id.MT2);
        meditation3=findViewById(R.id.MT3);
       guruIcon=findViewById(R.id.guruOnMeditation);
        instruction=findViewById(R.id.instruction);


    }
    public void actions(){


        guruIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AdapterActivity.class);
                startActivity(intent);
            }
        });


        meditation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instruction.setText(getString(R.string.Meditation1));
                meditationID=1;
                isMeditation=true;
            }
        });
        meditation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instruction.setText(getString(R.string.Meditation2));
                meditationID=2;
                isMeditation=true;
            }
        });
        meditation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instruction.setText(getString(R.string.Meditation3));
                meditationID=3;
                isMeditation=true;
            }
        });


        mediationStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMeditation){
                new ConnectTask().execute("");
                }

                else{



                }



            }
        });
    }



    public class ConnectTask extends AsyncTask<String, String, Boolean> {

        ProgressDialog dialogPlus;
        DialogPlus dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogPlus = CommonUtils
                    .showDialog(MeditationActivity.this,
                            R.string.meditation_title,
                            R.string.meditation_message);
        }

        @Override
        protected Boolean doInBackground(String... message) {
            //we create a TCPClient object
            tcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    publishProgress(message);
                }
            });


            return tcpClient.run(meditationID);
        }

        @Override
        protected void onPostExecute(Boolean isSuccess) {
            super.onPostExecute(isSuccess);
            dialogPlus.dismiss();
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(500);
            }

            // graphDialog=CommonUtils.createDialog(MeditationActivity.this,R.layout.activity_graph, Gravity.CENTER);
           // graphDialog.show();
            if(isSuccess){
           Intent intent=new Intent(getApplicationContext(),Graph.class);
           startActivity(intent);}
            else{
                dialog=CommonUtils.createDialog(MeditationActivity.this,R.layout.error_dialog, Gravity.CENTER);
                dialog.show();


            }
        }


    }


    @Override
    protected void onStop() {
        super.onStop();
       // tcpClient.stopClient();

    }
}
