package com.mobisem.engineering.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobisem.engineering.MeditationApplication;
import com.mobisem.engineering.R;
import com.mobisem.engineering.Utils.CommonUtils;


public class SignUpActivity extends AppCompatActivity {
  private FirebaseAuth mAuth;
  EditText email,password;
  TextView button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email=findViewById(R.id.signUpEmail);
        password=findViewById(R.id.signUpPassword);
        button= findViewById(R.id.signUpButton);
        mAuth= FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog signUpDialog= CommonUtils.showDialog(SignUpActivity.this,R.string.sign_up,R.string.signup_dialog_message);

                mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    signUpDialog.dismiss();
                                    Log.d("TAG", "createUserWithEmail:success");
                                    String personID = mAuth.getCurrentUser().getUid();
                                    MeditationApplication.getInstance().setCurrentUser(personID);
                                    Intent intent = new Intent(getApplicationContext(), EnteranceActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });

            }
        });

    }
}
