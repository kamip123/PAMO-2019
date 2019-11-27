package com.example.exercise4.ui.recommendations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.exercise4.R
import java.io.File
import java.io.IOException

class RecommendationsFragment : Fragment() {
    private var bmiData = "notSet"
    private var ppmData = "notSet"

    private var bmiField: TextView? = null
    private var ppmField: TextView? = null

    private var bmiFieldResult: TextView? = null
    private var ppmFieldResult: TextView? = null
    private var foodResult: ImageView? = null

    private lateinit var recommendationsViewModel: RecommendationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.activity_recommendations, container, false)

        bmiField = root.findViewById(R.id.bmiField)
        ppmField = root.findViewById(R.id.ppmField)

        bmiFieldResult = root.findViewById(R.id.bmiFieldResult)
        ppmFieldResult = root.findViewById(R.id.ppmFieldResult)

        foodResult = root.findViewById(R.id.foodResult)

        readData()

        if (bmiData != "notSet") {
            val tempBmi = java.lang.Double.parseDouble(bmiData.replace(',', '.'))

            if (tempBmi < 18.5) {
                bmiFieldResult!!.text = "Your bmi is too low"
                foodResult!!.setImageResource(R.drawable.too_low)
            } else if (18.5 <= tempBmi && tempBmi < 25) {
                bmiFieldResult!!.text = "Your bmi is good"
                foodResult!!.setImageResource(R.drawable.ok)
            } else {
                bmiFieldResult!!.text = "Your bmi is too high"
                foodResult!!.setImageResource(R.drawable.too_high)
            }
        }

        if (ppmData != "notSet") {
            ppmFieldResult!!.text = "You should eat: $ppmData kcal"
        }
        return root
    }

    protected fun readData() {
        val bmiFile = "bmiData.txt"
        val ppmFile = "ppmData.txt"

        val path = context!!.getFilesDir()
        val directory = File(path, "database")

        try {
            var file = File(directory, bmiFile)
            bmiData = file.readText()
            bmiField!!.text = "Your score: $bmiData"
        } catch (e: IOException){}

        try {
            var file = File(directory, ppmFile)
            ppmData = file.readText()
            ppmField!!.text = "Your score: $ppmData"
        } catch (e: IOException){}
    }
}