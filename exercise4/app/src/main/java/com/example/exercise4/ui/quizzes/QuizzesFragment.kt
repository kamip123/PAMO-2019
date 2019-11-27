package com.example.exercise4.ui.quizzes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.example.exercise4.MainActivity
import com.example.exercise4.Quiz1Activity
import com.example.exercise4.R
import java.io.*
import java.lang.StringBuilder

class QuizzesFragment : Fragment() {
    private var quiz1Result = "Your score: 0/6"
    private var quiz2Result = "Your score: 0/3"

    private var quiz1ResultField: TextView? = null
    private var quiz2ResultField: TextView? = null

    private var quiz1Button: Button? = null
    private var quiz2Button: Button? = null

    private lateinit var quizzesViewModel: QuizzesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.activity_quizzes, container, false)

        quiz1ResultField = root.findViewById(R.id.quiz1Result)
        quiz2ResultField = root.findViewById(R.id.quiz2Result)

        readData()

        if (quiz1Result != "Your score: 0/6") {
            quiz1ResultField!!.text = quiz1Result
        }

        if (quiz2Result != "Your score: 0/3") {
            quiz2ResultField!!.text = quiz2Result
        }

        quiz1Button = root.findViewById(R.id.quiz1Button)
        quiz2Button = root.findViewById(R.id.quiz2Button)

        quiz1Button!!.setOnClickListener {
            val intent = Intent (getActivity(), Quiz1Activity::class.java)
            getActivity()!!.startActivity(intent)
        }

        quiz2Button!!.setOnClickListener {
            val intent = Intent (getActivity(), Quiz1Activity::class.java)
            getActivity()!!.startActivity(intent)
        }

        return root
    }

    protected fun readData() {
        val quiz1File = "quiz1.txt"
        val quiz2File = "quiz2.txt"

        val path = context!!.getFilesDir()
        val directory = File(path, "database")

        try {
            var file = File(directory, quiz1File)
            var contents = file.readText()
            quiz1Result = "Your score: $contents"
            quiz1ResultField!!.text = quiz1Result
        } catch (e: IOException){}

        try {
            var file = File(directory, quiz2File)
            var contents = file.readText()
            quiz2Result = "Your score: $contents"
            quiz2ResultField!!.text = quiz2Result

        } catch (e: IOException){}
    }
}