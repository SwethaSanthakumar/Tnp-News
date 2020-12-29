package com.example.tnpnewsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationForm extends AppCompatActivity {

    private EditText usrname;
    private EditText pwd;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        usrname = findViewById(R.id.editText);
        pwd = findViewById(R.id.editText2);
        firebaseAuth=FirebaseAuth.getInstance();


    }

    public void home(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
    public void onClicklogin(View v) {
        Intent i =new Intent(RegistrationForm.this,LoginForm.class);
        startActivity(i);
        finish();
    }


    public void registerbtn(View v)
    {
        String res =usrname.getText().toString();
        if (res.trim().equals("")) {
            usrname.setError("Email ID \n Example:abc@gmail.com");
            return;
        }

        String res1 =pwd.getText().toString();
        if (res1.trim().equals("")) {
            pwd.setError("Password \n Minimum Character Length is 6");
            return;
        }
        final ProgressDialog progressDialog= ProgressDialog.show(RegistrationForm.this,"Please...","Processing...",true);
        (firebaseAuth.createUserWithEmailAndPassword(usrname.getText().toString(),pwd.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if(task.isSuccessful()){
                    Toast.makeText(RegistrationForm.this,"Signup Success..",Toast.LENGTH_LONG).show();
                    Intent i =new Intent(RegistrationForm.this,LoginForm.class);
                    startActivity(i);
                    finish();
                    //Log.d(TAG, "createUserWithEmail:success");
                    //FirebaseUser user = mAuth.getCurrentUser();
                    //updateUI(user);
                } else {
                    Log.e("ERROR",task.getException().toString());
                    Toast.makeText(RegistrationForm.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
