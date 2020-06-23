package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * This class defines the basic properties and operations for the second activity (screen).
 *
 * @author Haolun Cheng, Surya Sabarwal
 */
public class Main2Activity extends AppCompatActivity {

    /**
     * This method initializes the default settings for the second activity interface, and it is
     * automatically called when the second activity is launched.
     *
     * @param savedInstanceState last saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final ImageView adviceImage = findViewById(R.id.advice);

        Intent intent = getIntent();
        double BMINum = intent.getDoubleExtra(MainActivity.EXTRA_NUMBER, 0);
        final double underweightTop = 18.5;
        final double normalTop = 25;
        final double overweightTop = 30;

        if (BMINum < underweightTop) {
            adviceImage.setImageResource(R.drawable.underweight);
        } else if (BMINum >= underweightTop && BMINum < normalTop) {
            adviceImage.setImageResource(R.drawable.normal);
        } else if (BMINum >= normalTop && BMINum < overweightTop) {
            adviceImage.setImageResource(R.drawable.overweight);
        } else {
            adviceImage.setImageResource(R.drawable.obese);
        }
    }
}
