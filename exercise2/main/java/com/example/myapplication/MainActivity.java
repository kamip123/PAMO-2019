package com.example.myapplication;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private double height = 0.0;
    private double weight = 0.0;
    private double bmi = 0.0;

    private EditText heightField;
    private EditText weightField;
    private TextView resultField;
    private Button calculateButton;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_bmi:
                    intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_ppm:
                    intent = new Intent(getBaseContext(), PpmActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_recommendations:
                    intent = new Intent(getBaseContext(), RecommendationsActivity.class);
                    startActivity(intent);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        MenuItem item = navView.getMenu().findItem(R.id.navigation_bmi);
        item.setChecked(true);

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
            createData();
        }
    }

    protected void createData() {
        String fileName = "dietData.txt";
        String data = String.format("%.2f", bmi);
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(data.getBytes());
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
