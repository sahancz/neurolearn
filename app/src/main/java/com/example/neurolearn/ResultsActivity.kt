package com.example.neurolearn

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val resultSummary = findViewById<TextView>(R.id.tv_result_summary)
        val aiFeedback1 = findViewById<TextView>(R.id.tv_ai_feedback_1)
        val aiFeedback2 = findViewById<TextView>(R.id.tv_ai_feedback_2)
        val continueBtn = findViewById<Button>(R.id.btn_continue)


        resultSummary.text = "You completed the task! Here is the AI analysis."


        aiFeedback1.text = "1. Question 1: Your logic on Gradient Descent was precise."
        aiFeedback2.text = "2. Question 2: Consider reviewing 'Overfitting' for better accuracy."

        continueBtn.setOnClickListener {

            finish()
        }
    }
}