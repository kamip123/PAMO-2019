package com.example.zad2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChartActivity extends AppCompatActivity {

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

        setContentView(R.layout.activity_charts);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        MenuItem item = navView.getMenu().findItem(R.id.navigation_charts);
        item.setChecked(true);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        String htmlData = "<html>"
                +"  <head>"
                +"    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>"
                +"    <script type=\"text/javascript\">"
                +"      google.charts.load('current', {'packages':['corechart']});"
                +"      google.charts.setOnLoadCallback(drawChart);"

                +"      function drawChart() {"

                +"        var data = google.visualization.arrayToDataTable(["
                +"          ['Month', 'Weight', 'Expected Weight'],"
                +"          ['Jan', 100, 95],"
                +"          ['Feb', 97, 90],"
                +"          ['Mar', 93, 85],"
                +"          ['Apr', 86, 82],"
                +"          ['Aug', 82, 80],"
                +"          ['Sep', 76, 78],"
                +"          ['Oct', 74, 76],"
                +"          ['Nov', 75, 75],"
                +"          ['Dec',  74, 75],"
                +"        ]);"

                +"        var options = {"
                +"          title: 'My Daily Activities',"
                +"          curveType: 'function',"
                +"          legend: { position: 'bottom' }"
                +"        };"

                +"        var chart = new google.visualization.LineChart(document.getElementById('chartContainer'));"
                +"        chart.draw(data, options);"
                +"      }"
                +"    </script>"
                +"  </head>"
                +"  <body>"
                +"    <div id=\"chartContainer\" style=\"width: 900px; height: 500px;\"></div>"
                +"  </body>"
                +"</html>";
        myWebView.loadData(htmlData, "text/html", "UTF-8");
    }
}
