package com.example.tnpnewsapp;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class SplashScreen extends Activity {
    MediaPlayer oursong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        oursong = MediaPlayer.create(SplashScreen.this, R.raw.audio);
        oursong.start();


        Thread timer= new Thread(){
            public void run(){
                try{
                    sleep(5000);

                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    Intent openStartingPoint= new Intent(SplashScreen.this,LoginForm.class);
                    startActivity(openStartingPoint);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        oursong.release();
        finish();
    }


}


