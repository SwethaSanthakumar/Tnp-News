package com.example.tnpnewsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Analysis extends AppCompatActivity {

    private int calsBurned = 0;
    private int calsConsumed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analysis);
    }

    public void addBurned(View v) {
        EditText burnedEditText = findViewById(R.id.burned);
        calsBurned = Integer.parseInt(burnedEditText.getText().toString());
        updateChart();
    }

    public void addConsumed(View v) {
        EditText consumedEditText = findViewById(R.id.consumed);
        calsConsumed = Integer.parseInt(consumedEditText.getText().toString());
        updateChart();
    }

    private void updateChart(){
        TextView numberOfCals = findViewById(R.id.number_of_calories);
        numberOfCals.setText(String.valueOf(calsBurned) + " / " + calsConsumed);
        ProgressBar pieChart = findViewById(R.id.stats_progressbar);
        double d = (double) calsBurned / (double) calsConsumed;
        int progress = (int) (d * 100);
        pieChart.setProgress(progress);
    }
}