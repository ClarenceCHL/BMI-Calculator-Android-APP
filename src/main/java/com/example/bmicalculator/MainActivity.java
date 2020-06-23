package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.view.View;

import java.util.Locale;

/**
 * This class defines the basic properties and operations for the first activity (screen).
 *
 * @author Haolun Cheng, Surya Sabarwal
 */
public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_NUMBER = "com.example.bmicalculator.example.EXTRA_NUMBER";
    public double passBMI = 0;

    /**
     * This method initializes the default settings for the first activity interface, and it is
     * automatically called when the application is launched.
     *
     * @param savedInstanceState last saved state of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioButton EnglishButton = findViewById(R.id.englishButton);

        final RadioButton MetricButton = findViewById(R.id.metricButton);

        final RadioGroup ButtonGroup = findViewById(R.id.radioGroup);

        final Button CalculateBMI = findViewById(R.id.calculateBMI);

        final Button GetAdvice = findViewById(R.id.getAdvice);

        final TextView outputText = findViewById(R.id.outputBMI);

        final EditText InputHeight = findViewById(R.id.inputHeight);

        final EditText InputWeight = findViewById(R.id.inputWeight);

        CalculateBMI.setOnClickListener(new View.OnClickListener() {
            /**
             * This method defines the operations when the button CalculateBMI is clicked.
             *
             * @param v a view
             */
            @Override
            public void onClick(View v) {
                double BMIValue = 0;
                try {
                    double heightNum = Double.parseDouble(InputHeight.getText().toString());
                    double weightNum = Double.parseDouble(InputWeight.getText().toString());
                    if (EnglishButton.isChecked()) {
                        BMIValue = (weightNum * 703) / (heightNum * heightNum);
                    } else if (MetricButton.isChecked()) {
                        BMIValue = weightNum / (heightNum * heightNum);
                    }
                    outputText.setText(String.format(Locale.getDefault(), "%.2f", BMIValue));
                    passBMI = BMIValue;
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Invalid Weight/Height Input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ButtonGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             * This method defines the operations when a radio button is selected.
             *
             * @param group a radio group
             * @param checked_id id for the checked radio button
             */
            @Override
            public void onCheckedChanged(RadioGroup group, int checked_id) {
                switch (checked_id) {
                    case R.id.englishButton:
                        InputHeight.setHint("Enter height in inches");
                        InputWeight.setHint("Enter weight in pounds");
                        break;
                    case R.id.metricButton:
                        InputHeight.setHint("Enter height in meters");
                        InputWeight.setHint("Enter weight in kilograms");
                        break;
                }
            }
        });

        GetAdvice.setOnClickListener(new View.OnClickListener() {
            /**
             * This method defines the operations when the button GetAdvice is clicked.
             *
             * @param v a view
             */
            @Override
            public void onClick(View v) {
                if (outputText.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please calculate your BMI first", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra(EXTRA_NUMBER, passBMI);
                    startActivity(intent);
                }
            }
        });
    }
}
