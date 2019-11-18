package com.example.zad2;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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

public class QuizzesActivity extends AppCompatActivity {

    private String quiz1Result = "Your score: 0/6";
    private String quiz2Result = "Your score: 0/3";

    private TextView quiz1ResultField;
    private TextView quiz2ResultField;

    private Button quiz1Button;
    private Button quiz2Button;

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
        setContentView(R.layout.activity_quizzes);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        quiz1ResultField = findViewById(R.id.quiz1Result);
        quiz2ResultField = findViewById(R.id.quiz2Result);

        readData();

        if( !quiz1Result.equals("Your score: 0/6") ) {
            quiz1ResultField.setText(quiz1Result);
        }

        if( !quiz2Result.equals("Your score: 0/3") ) {
            quiz2ResultField.setText(quiz2Result);
        }

        quiz1Button = findViewById(R.id.quiz1Button);
        quiz2Button = findViewById(R.id.quiz2Button);

        quiz1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getBaseContext(), Quiz1Activity.class);
                startActivity(intent);
            }
        });

        quiz2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                ///// CHANGE TO 2
                intent = new Intent(getBaseContext(), Quiz1Activity.class);
                startActivity(intent);
            }
        });

    }

    protected void readData() {
        String quiz1File = "quiz1.txt";
        String quiz2File = "quiz2.txt";
        FileInputStream fis = null;

        try {
            fis = openFileInput(quiz1File);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }

            quiz1Result = sb.toString();
            quiz1ResultField.setText("Your score: " + quiz1Result);

            fis = openFileInput(quiz2File);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            sb = new StringBuilder();
            String text2;

            while ((text2 = br.readLine()) != null) {
                sb.append(text2).append("\n");
            }

            quiz2Result = sb.toString();
            quiz2ResultField.setText("Your score: " + quiz2Result);

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
