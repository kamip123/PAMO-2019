package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class RecommendationsActivity extends AppCompatActivity {

    private double height = 0.0;
    private double weight = 0.0;
    private double bmi = 0.0;
    private String data = "";

    private EditText heightField;
    private EditText weightField;
    private TextView resultField;
    private Button calculateButton;
    private TextView test;

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

        setContentView(R.layout.activity_recommendations);
        test = findViewById(R.id.test);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        MenuItem item = navView.getMenu().findItem(R.id.navigation_recommendations);
        item.setChecked(true);
    }

    protected void readData() {
        String fileName = "dietData.txt";
        FileInputStream fis = null;

        try {
            fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }

            data = sb.toString();
            test.setText(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
