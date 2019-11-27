package com.example.exercise4.ui.bmi

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.exercise4.R
import java.lang.Exception
import java.io.File

class BmiFragment : Fragment() {
    private var height = 0.0
    private var weight = 0.0
    private var bmi = 0.0

    private var heightField: EditText? = null
    private var weightField: EditText? = null
    private var resultField: TextView? = null
    private var calculateButton: Button? = null
    private lateinit var bmiViewModel: BmiViewModel

    private val heightChanged = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            try {
                height = java.lang.Double.parseDouble(s.toString()) / 100
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.activity_bmi, container, false)

        heightField = root.findViewById(R.id.heightField)
        weightField = root.findViewById(R.id.weightField)
        resultField = root.findViewById(R.id.resultField)
        calculateButton = root.findViewById(R.id.calculateButton)

        heightField!!.addTextChangedListener(heightChanged)
        weightField!!.addTextChangedListener(weightChanged)

        calculateButton!!.setOnClickListener {
            calculate()
            resultField!!.setText(String.format("%.2f", bmi))
        }

        return root
    }

    protected fun calculate() {
        if (height == 0.0 || weight == 0.0) {
            // show error message
        } else {
            bmi = weight / (height * height)
            createData()
        }
    }

    protected fun createData() {
        val fileName = "bmiData.txt"
        val data = String.format("%.2f", bmi)

        val path = context!!.getFilesDir()

        val directory = File(path, "database")
        directory.mkdirs()

        var file = File(directory, fileName)
        file.createNewFile()
        file.writeText(data)

    }
}