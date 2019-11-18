package com.example.zad2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz1Activity extends AppCompatActivity {

    private int result = 0;
    private Button submitButton;

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
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RadioButton rb;

                rb = findViewById(R.id.radio11);
                if (rb.isChecked()){
                    result+=1;
                }

                rb = findViewById(R.id.radio21);
                if (rb.isChecked()){
                    result+=1;
                }

                rb = findViewById(R.id.radio31);
                if (rb.isChecked()){
                    result+=1;
                }

                rb = findViewById(R.id.radio41);
                if (rb.isChecked()){
                    result+=1;
                }

                rb = findViewById(R.id.radio51);
                if (rb.isChecked()){
                    result+=1;
                }

                rb = findViewById(R.id.radio61);
                if (rb.isChecked()){
                    result+=1;
                }

                createData();
                Intent intent = new Intent(getBaseContext(), QuizzesActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void createData() {
        String fileName = "quiz1.txt";
        String data = "Your score: " + result + "/6";
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(data.getBytes());
        } catch (IOException e) {
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
