package com.mobisem.engineering.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.mobisem.engineering.R;
import com.mobisem.engineering.Utils.CommonUtils;
import com.orhanobut.dialogplus.DialogPlus;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText forgotPassword;
    TextView sendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        forgotPassword=findViewById(R.id.forgotPassword);

        sendButton=findViewById(R.id.forgotButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPassword(forgotPassword.toString());

            }
        });





    }


    public void forgotPassword(String email){

       final ProgressDialog progressDialog;
               progressDialog=CommonUtils.showDialog(this,R.string.forget_text,R.string.forget_dialog_message);
                  progressDialog.show();
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                             progressDialog.dismiss();

                        }

                    }
                });




    }
}
