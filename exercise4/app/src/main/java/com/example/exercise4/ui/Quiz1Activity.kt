package com.example.exercise4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class Quiz1Activity : AppCompatActivity() {

    private var quizResult = 0
    private var submitButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz1)
        submitButton = findViewById(R.id.submitButton)

        submitButton!!.setOnClickListener(View.OnClickListener {
            var emptyAnswer: Boolean? = false
            var radioGroup = findViewById<RadioGroup>(R.id.radioGroupQuestion1)
            if (radioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(applicationContext, "You have not answered question 1", Toast.LENGTH_LONG).show()
                emptyAnswer = true
            }

            radioGroup = findViewById(R.id.radioGroupQuestion2)
            if (radioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(applicationContext, "You have not answered question 2", Toast.LENGTH_LONG).show()
                emptyAnswer = true
            }

            radioGroup = findViewById(R.id.radioGroupQuestion3)
            if (radioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(applicationContext, "You have not answered question 3", Toast.LENGTH_LONG).show()
                emptyAnswer = true
            }

            radioGroup = findViewById(R.id.radioGroupQuestion4)
            if (radioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(applicationContext, "You have not answered question 4", Toast.LENGTH_LONG).show()
                emptyAnswer = true
            }

            radioGroup = findViewById(R.id.radioGroupQuestion5)
            if (radioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(applicationContext, "You have not answered question 5", Toast.LENGTH_LONG).show()
                emptyAnswer = true
            }

            radioGroup = findViewById(R.id.radioGroupQuestion6)
            if (radioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(applicationContext, "You have not answered question 6", Toast.LENGTH_LONG).show()
                emptyAnswer = true
            }

            if (emptyAnswer!!) {
                return@OnClickListener
            }

            var rb: RadioButton
            rb = findViewById(R.id.radio11)
            if (rb.isChecked) {
                quizResult += 1
            }

            rb = findViewById(R.id.radio21)
            if (rb.isChecked) {
                quizResult += 1
            }

            rb = findViewById(R.id.radio31)
            if (rb.isChecked) {
                quizResult += 1
            }

            rb = findViewById(R.id.radio41)
            if (rb.isChecked) {
                quizResult += 1
            }

            rb = findViewById(R.id.radio51)
            if (rb.isChecked) {
                quizResult += 1
            }

            rb = findViewById(R.id.radio61)
            if (rb.isChecked) {
                quizResult += 1
            }

            createData()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
    }

    protected fun createData() {
        val fileName = "quiz1.txt"
        val data = "Your score: $quizResult/6"

        val path = getFilesDir()

        val directory = File(path, "database")
        directory.mkdirs()

        var file = File(directory, fileName)
        file.createNewFile()
        file.writeText(data)
    }
}
