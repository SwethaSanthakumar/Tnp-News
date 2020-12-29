package com.example.tnpnewsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginForm extends AppCompatActivity {

    private EditText username;
    private EditText Password;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        TextView textView = (TextView)findViewById(R.id.textView13);
        SpannableString content = new SpannableString("CREATE ACCOUNT");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);
        username =  findViewById(R.id.editTextusername);
        Password =  findViewById(R.id.editText2password);
        firebaseAuth=FirebaseAuth.getInstance();
        //final Button btnlg = (Button) findViewById(R.id.button);
        //final Button regis =(Button)findViewById(R.id.button6);


        /*btnlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String Pass = Password.getText().toString();
                if (user.equals("seran") && Pass.equals("12345")) {
                    username.setBackgroundColor(Color.GREEN);
                    Password.setBackgroundColor(Color.GREEN);
                    Toast.makeText(getApplicationContext(), "Login Sucessful", Toast.LENGTH_LONG).show();

                    String str=username.getText().toString();

                    Intent it=new Intent(getApplicationContext(),MainActivity.class);
                    it.putExtra("message",str);
                    startActivity(it);

                } else {
                    username.setBackgroundColor(Color.RED);
                    Password.setBackgroundColor(Color.RED);
                    Toast.makeText(getApplicationContext(), "Login Failure \n Try again....", Toast.LENGTH_LONG).show();

                }

            }
        });*/


    }

    public void home(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void register(View v)
    {
        Intent i = new Intent(this, RegistrationForm.class);
        startActivity(i);
        finish();
    }

    public void login(View v)
    {
        String res =username.getText().toString();
        if (res.trim().equals("")) {
            username.setError("Enter a Email ID \n Example:abc@gmail.com");
            return;
        }

        String res1 =Password.getText().toString();
        if (res1.trim().equals("")) {
            Password.setError("Enter a Password \n Minimum Length is 6");
            return;
        }
        final ProgressDialog progressDialog= ProgressDialog.show(LoginForm.this,"Please wait..","Processing..",true);

        (firebaseAuth.signInWithEmailAndPassword(username.getText().toString(),Password.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful()){
                    Toast.makeText(LoginForm.this,"Login Successful..",Toast.LENGTH_LONG).show();
                    username.setBackgroundColor(Color.GREEN);
                    Password.setBackgroundColor(Color.GREEN);
                    Intent i=new Intent(LoginForm.this,MainActivity.class);
                    i.putExtra("Email",firebaseAuth.getCurrentUser().getEmail());
                    startActivity(i);
                    finish();
                } else {
                    Log.e("ERROR",task.getException().toString());
                    Toast.makeText(LoginForm.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}




