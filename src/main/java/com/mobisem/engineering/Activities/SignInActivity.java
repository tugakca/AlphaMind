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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobisem.engineering.MeditationApplication;
import com.mobisem.engineering.R;
import com.mobisem.engineering.Utils.CommonUtils;


public class SignInActivity extends AppCompatActivity {
private FirebaseAuth mAuth;
EditText email,password;
TextView button;
TextView signUpText,forgotPasswordTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.signInEmail);
        password = findViewById(R.id.signInPassword);
        signUpText = findViewById(R.id.signUpTv);
        forgotPasswordTv=findViewById(R.id.forgotPasswordTv);


        forgotPasswordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });






        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });

        button=findViewById(R.id.signInButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog signInDialog= CommonUtils.showDialog(SignInActivity.this,R.string.login_dialog_title,R.string.login_dialog_message);
                mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    signInDialog.dismiss();
                                    Log.d("TAG", "signIn:success");
                                    String personID = mAuth.getCurrentUser().getUid();
                                    MeditationApplication.getInstance().setCurrentUser(personID);

                                    Intent intent = new Intent(getApplicationContext(), EnteranceActivity.class);
                                    startActivity(intent);
                                }
                                else{

                                    signInDialog.dismiss();
                                    Log.w("TAG"," createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignInActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }
}
