package com.example.neurolearn

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.ai.client.generativeai.GenerativeModel
import io.noties.markwon.Markwon
import kotlinx.coroutines.launch

class ResultsActivity : AppCompatActivity() {

    private lateinit var markwon: Markwon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val resultSummary = findViewById<TextView>(R.id.tv_result_summary)
        val aiFeedback1 = findViewById<TextView>(R.id.tv_ai_feedback_1)
        val aiFeedback2 = findViewById<TextView>(R.id.tv_ai_feedback_2)
        val continueBtn = findViewById<Button>(R.id.btn_continue)

        aiFeedback2.visibility = View.GONE

        markwon = Markwon.create(this)

        val studentAnswer = intent.getStringExtra("STUDENT_ANSWER") ?: "No answer provided."
        val topic = intent.getStringExtra("TASK_TOPIC") ?: "Neural Networks"
        // NEW: Grab the task!
        val originalTask = intent.getStringExtra("ORIGINAL_TASK") ?: "No task provided."

        resultSummary.text = "Evaluating your task on $topic..."
        aiFeedback1.text = "Grading in progress..."


        evaluateFinalAnswer(topic, originalTask, studentAnswer, resultSummary, aiFeedback1)

        continueBtn.setOnClickListener {
            finish()
        }
    }


    private fun evaluateFinalAnswer(topic: String, originalTask: String, answer: String, summaryText: TextView, feedbackText: TextView) {
        lifecycleScope.launch {
            try {
                val generativeModel = GenerativeModel(
                    modelName = "gemini-2.5-flash",
                    apiKey = "AIzaSyAL8xlgRMeFekXwzzzEMld1Hn0CXfHc6js"
                )


                val prompt = "Act as an AI tutor grading a final submission. The topic is $topic. The original task given to the student was: '$originalTask'. The student submitted this answer: '$answer'. Provide a brief 1-sentence summary of how they did, followed by 2 bullet points of constructive feedback. Keep it encouraging."

                val response = generativeModel.generateContent(prompt)

                summaryText.text = "Final AI Analysis Complete!"
                markwon.setMarkdown(feedbackText, response.text ?: "No feedback generated.")

            } catch (e: Exception) {
                summaryText.text = "Analysis Failed"
                feedbackText.text = "Error: Could not connect to the LLM. ${e.localizedMessage}"
                Toast.makeText(this@ResultsActivity, "Grading API Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
        }

