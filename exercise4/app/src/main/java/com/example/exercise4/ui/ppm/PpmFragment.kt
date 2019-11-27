package com.example.exercise4.ui.ppm

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.exercise4.R
import com.example.exercise4.ui.recommendations.RecommendationsViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception

class PpmFragment : Fragment() {
    private var height = 0.0
    private var weight = 0.0
    private var ppm = 0.0
    private var years = 0.0
    private var gender = "none"

    private var heightField: EditText? = null
    private var weightField: EditText? = null
    private var yearstField: EditText? = null
    private var resultField: TextView? = null
    private var calculateButton: Button? = null

    private lateinit var ppmViewModel: RecommendationsViewModel

    private val heightChanged = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            try {
                height = java.lang.Double.parseDouble(s.toString())
            } catch (e: Exception) {
                height = 0.0
            }

        }

        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    }

    private val weightChanged = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            try {
                weight = java.lang.Double.parseDouble(s.toString())
            } catch (e: Exception) {
                weight = 0.0
            }

        }

        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    }

    private val yearsChanged = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            try {
                years = java.lang.Double.parseDouble(s.toString())
            } catch (e: Exception) {
                years = 0.0
            }

        }

        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.activity_ppm, container, false)

        heightField = root.findViewById(R.id.heightField)
        weightField = root.findViewById(R.id.weightField)
        yearstField = root.findViewById(R.id.yearstField)
        resultField = root.findViewById(R.id.resultField)
        calculateButton = root.findViewById(R.id.calculateButton)

        heightField!!.addTextChangedListener(heightChanged)
        weightField!!.addTextChangedListener(weightChanged)
        yearstField!!.addTextChangedListener(yearsChanged)

        val rg = root.findViewById<RadioGroup>(R.id.radioGroupGender)

        rg.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio11 -> gender = "male"
                R.id.radio12 -> gender = "female"
            }
        }

        calculateButton!!.setOnClickListener {
            calculate()
            resultField!!.setText(String.format("%.2f", ppm))
        }

        return root
    }
    protected fun calculate() {
        if (height == 0.0 || weight == 0.0) {
            // show error message
        } else {
            if (gender === "female") {
                ppm = 10 * weight + 6.25 * height - 5 * years - 161.0
            } else {
                ppm = 10 * weight + 6.25 * height - 5 * years + 5
            }
            createData()
        }
    }

    protected fun createData() {
        val fileName = "ppmData.txt"
        val data = String.format("%.2f", ppm)

        val path = context!!.getFilesDir()

        val directory = File(path, "database")
        directory.mkdirs()

        var file = File(directory, fileName)
        file.createNewFile()
        file.writeText(data)

    }
}