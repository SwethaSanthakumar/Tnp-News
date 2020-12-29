package com.example.tnpnewsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        Button storeinformation = (Button) findViewById(R.id.storeinformation);
        Button showinformation = (Button) findViewById(R.id.showinformation);
        textView = (TextView) findViewById(R.id.txtPrefs);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.storeinformation:
                        Intent intent = new Intent(Profile.this,PrefsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.showinformation:
                        displaySharedPreferences();
                        break;
                    default:
                        break;
                }
            }
        };
        storeinformation.setOnClickListener(listener);
        showinformation.setOnClickListener(listener);
    }


    private void displaySharedPreferences() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Profile.this);
        String username = prefs.getString("username", "Default NickName");
        String passw = prefs.getString("password", "Default Password");
        String emailid = prefs.getString("email", "Default Email");
        boolean checkBox = prefs.getBoolean("checkBox", false);
        String listPrefs = prefs.getString("listpref", "Default list prefs");
        String department = prefs.getString("dept", "Default Department");
        String yearstudy = prefs.getString("year", "Default Year");


        StringBuilder builder = new StringBuilder();
        builder.append("USERNAME          : " + username + "\n");
        builder.append("PASSWORD          : " + passw + "\n");
        builder.append("EMAIL             : " + emailid + "\n");
        builder.append("DEPARTMENT        : " + department + "\n");
        builder.append("YEAR OF STUDY     : " + yearstudy + "\n");
        builder.append("KEEP ME LOGGED IN : " + String.valueOf(checkBox) + "\n");
        builder.append("LIST PREFERENCE   : " + listPrefs);
        textView.setText(builder.toString());

    }

}