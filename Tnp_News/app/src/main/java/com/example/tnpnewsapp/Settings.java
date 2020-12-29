package com.example.tnpnewsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {
ImageView i1,i2,i3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        i1 = (ImageView) findViewById(R.id.button1);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Settings.this,Noti.class);
                startActivity(a);
            }
        });
        i2 = (ImageView) findViewById(R.id.button2);
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(Settings.this,Wifi.class);
                startActivity(b);
            }
        });
        i3 = (ImageView) findViewById(R.id.button3);
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(Settings.this,Gps.class);
                startActivity(c);
            }
        });

    }
}
