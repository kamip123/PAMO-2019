package com.example.zad2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class RecommendationsActivity extends AppCompatActivity {

    private String bmiData = "notSet";
    private String ppmData = "notSet";

    private TextView bmiField;
    private TextView ppmField;

    private TextView bmiFieldResult;
    private TextView ppmFieldResult;
    private ImageView foodResult;

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
                case R.id.navigation_quizzes:
                    intent = new Intent(getBaseContext(), QuizzesActivity.class);
                    startActivity(intent);
                    break;
                case R.id.navigation_charts:
                    intent = new Intent(getBaseContext(), ChartActivity.class);
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
        bmiField = findViewById(R.id.bmiField);
        ppmField = findViewById(R.id.ppmField);

        bmiFieldResult = findViewById(R.id.bmiFieldResult);
        ppmFieldResult = findViewById(R.id.ppmFieldResult);

        foodResult = findViewById(R.id.foodResult);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        MenuItem item = navView.getMenu().findItem(R.id.navigation_recommendations);
        item.setChecked(true);

        readData();

        if( !bmiData.equals("notSet") ) {
            double tempBmi = Double.parseDouble(bmiData.replace(',','.'));

            if ( tempBmi < 18.5 ) {
                bmiFieldResult.setText("Your bmi is too low");
                foodResult.setImageResource(R.drawable.too_low);
            } else if ( 18.5 <= tempBmi && tempBmi < 25) {
                bmiFieldResult.setText("Your bmi is good");
                foodResult.setImageResource(R.drawable.ok);
            } else {
                bmiFieldResult.setText("Your bmi is too high");
                foodResult.setImageResource(R.drawable.too_high);
            }
        }

        if( !ppmData.equals("notSet") ) {
            ppmFieldResult.setText("You should eat: " + ppmData + " kcal");
        }

    }

    protected void readData() {
        String bmiFile = "bmiData.txt";
        String ppmFile = "ppmData.txt";
        FileInputStream fis = null;

        try {
            fis = openFileInput(bmiFile);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }

            bmiData = sb.toString();
            bmiField.setText(bmiData);

            fis = openFileInput(ppmFile);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            sb = new StringBuilder();
            String text2;

            while ((text2 = br.readLine()) != null) {
                sb.append(text2).append("\n");
            }

            ppmData = sb.toString();
            ppmField.setText(ppmData);

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
