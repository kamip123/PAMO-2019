package com.example.exercise4.ui.charts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.exercise4.R

class ChartsFragment : Fragment() {

    private lateinit var chartsViewModel: ChartsViewModel
    private var webView: WebView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.activity_charts, container, false)
        webView = root.findViewById(R.id.webview) as WebView

        webView!!.settings.javaScriptEnabled = true


        val htmlData = ("<html>"
                + "  <head>"
                + "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>"
                + "    <script type=\"text/javascript\">"
                + "      google.charts.load('current', {'packages':['corechart']});"
                + "      google.charts.setOnLoadCallback(drawChart);"

                + "      function drawChart() {"

                + "        var data = google.visualization.arrayToDataTable(["
                + "          ['Month', 'Weight', 'Expected Weight'],"
                + "          ['Jan', 100, 95],"
                + "          ['Feb', 97, 90],"
                + "          ['Mar', 93, 85],"
                + "          ['Apr', 86, 82],"
                + "          ['Aug', 82, 80],"
                + "          ['Sep', 76, 78],"
                + "          ['Oct', 74, 76],"
                + "          ['Nov', 75, 75],"
                + "          ['Dec',  74, 75],"
                + "        ]);"

                + "        var options = {"
                + "          title: 'My Daily Activities',"
                + "          curveType: 'function',"
                + "          legend: { position: 'bottom' }"
                + "        };"

                + "        var chart = new google.visualization.LineChart(document.getElementById('chartContainer'));"
                + "        chart.draw(data, options);"
                + "      }"
                + "    </script>"
                + "  </head>"
                + "  <body>"
                + "    <div id=\"chartContainer\" style=\"width: 900px; height: 500px;\"></div>"
                + "  </body>"
                + "</html>")

        webView!!.loadData(htmlData, "text/html", "UTF-8")

        return root
    }
}