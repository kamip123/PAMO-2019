package com.example.zad2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PpmActivity extends AppCompatActivity {

    private double height = 0.0;
    private double weight = 0.0;
    private double ppm = 0.0;
    private double years = 0.0;
    private String gender = "none";

    private EditText heightField;
    private EditText weightField;
    private EditText yearstField;
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
        setContentView(R.layout.activity_ppm);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        MenuItem item = navView.getMenu().findItem(R.id.navigation_ppm);
        item.setChecked(true);

        heightField = findViewById(R.id.heightField);
        weightField = findViewById(R.id.weightField);
        yearstField = findViewById(R.id.yearstField);
        resultField = findViewById(R.id.resultField);
        calculateButton = findViewById(R.id.calculateButton);

        heightField.addTextChangedListener(heightChanged);
        weightField.addTextChangedListener(weightChanged);
        yearstField.addTextChangedListener(yearsChanged);

        RadioGroup rg = findViewById(R.id.radioGroupGender);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioMale:
                        gender = "male";
                        break;
                    case R.id.radioFemale:
                        gender = "female";
                        break;
                }
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
                resultField.setText(String.format("%.2f", ppm));
            }
        });
    }

    private final TextWatcher heightChanged = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                height = Double.parseDouble(s.toString());
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

    private final TextWatcher yearsChanged = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                years = Double.parseDouble(s.toString());
            } catch (Exception e) {
                years = 0.0;
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
            if ( gender == "female" ) {
                ppm = (10*weight)+(6.25*height)-(5*years)-161;
            } else {
                ppm = (10*weight)+(6.25*height)-(5*years)+5;
            }
            createData();
        }
    }

    protected void createData() {
        String fileName = "ppmData.txt";
        String data = String.format("%.2f", ppm);
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(data.getBytes());
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
