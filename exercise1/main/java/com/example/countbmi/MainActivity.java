package com.example.countbmi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private double height = 0.0;
    private double weight = 0.0;
    private double bmi = 0.0;

    private EditText heightField;
    private EditText weightField;
    private TextView resultField;
    private Button calculateButton;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heightField = findViewById(R.id.heightField);
        weightField = findViewById(R.id.weightField);
        resultField = findViewById(R.id.resultField);
        calculateButton = findViewById(R.id.calculateButton);

        heightField.addTextChangedListener(heightChanged);
        weightField.addTextChangedListener(weightChanged);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
                resultField.setText(String.format("%.2f", bmi));
            }
        });
    }

    private final TextWatcher heightChanged = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                height = Double.parseDouble(s.toString()) / 100;
            } catch (Exception e) {
                height = 0.0;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
    };

    private final TextWatcher weightChanged = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                weight = Double.parseDouble(s.toString());
            } catch (Exception e) {
                weight = 0.0;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
    };

    protected void calculate() {
        if (height == 0.0 || weight == 0.0) {
            // show error message
        } else {
            bmi = weight / (height * height);
        }
    }


}
