package com.mobisem.engineering.Activities;

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
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobisem.engineering.Connection.TcpClient;
import com.mobisem.engineering.R;
import com.mobisem.engineering.Utils.CommonUtils;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.HashMap;


public class FirstProcess extends AppCompatActivity {

    TextView firstProcessStartTv;

    TcpClient mTcp;
    int meditationID;
    FirebaseFirestore db;
    FirebaseAuth mAuth;


   private  static FirstProcess mcontext;

    public static FirstProcess getMcontext() {
        return mcontext;
    }

    public static void setMcontext(FirstProcess mcontext) {
        FirstProcess.mcontext = mcontext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_process);
        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        mcontext=this;
         inits();
         firstProcessStartTv.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 meditationID=0;
                 new ConnectTask().execute("");

             }
         });




    }
    public void inits(){
        firstProcessStartTv=findViewById(R.id.firstProcessStartTv);

    }


    public class ConnectTask extends AsyncTask<String, String, Boolean> {
        TcpClient tcpClient;
        ProgressDialog dialogPlus;
        DialogPlus dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialogPlus = CommonUtils
                    .showDialog(FirstProcess.this,
                            R.string.meditation_title,
                            R.string.meditation_message);
        }

        @Override
        protected Boolean doInBackground(String... message) {
           // we create a TCPClient object
                    tcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    Log.i("ASYNC:",message);
                    publishProgress(message);


//


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




            if (isSuccess){

                Intent intent = new Intent(getApplicationContext(), MeditationActivity.class);
            startActivity(intent);
            finish();
        }






        }
    }



}
