package com.example.neurolearn

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.ai.client.generativeai.GenerativeModel
import io.noties.markwon.Markwon
import kotlinx.coroutines.launch

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var tvPrompt: TextView
    private lateinit var tvResponse: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnGetHint: Button
    private lateinit var btnExplain: Button
    private lateinit var btnSubmit: Button
    private lateinit var etStudentAnswer: EditText

    private lateinit var markwon: Markwon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        tvPrompt = findViewById(R.id.tv_llm_prompt)
        tvResponse = findViewById(R.id.tv_llm_response)
        progressBar = findViewById(R.id.pb_loading)
        btnGetHint = findViewById(R.id.btn_generate_hint)
        btnExplain = findViewById(R.id.btn_explain_logic)
        btnSubmit = findViewById(R.id.btn_submit_task)
        etStudentAnswer = findViewById(R.id.et_student_answer)

        markwon = Markwon.create(this)


        val topic = intent.getStringExtra("TASK_TOPIC") ?: "Neural Networks"

        executeLLMTask(
            displayPrompt = "Generating introductory task for $topic.",
            apiPrompt = "Act as an AI tutor. Generate a very brief introductory task for a student learning about $topic."
        )

        btnGetHint.setOnClickListener {
            executeLLMTask(
                displayPrompt = "Requesting a hint...",
                apiPrompt = "Act as a tutor. Provide a subtle hint for the introductory task about $topic. Do not give the direct answer, just a nudge."
            )
        }

        btnExplain.setOnClickListener {
            val studentAnswer = etStudentAnswer.text.toString().trim()

            if (studentAnswer.isEmpty()) {
                Toast.makeText(this, "Please type an answer first so the tutor can evaluate it!", Toast.LENGTH_SHORT).show()
            } else {
                executeLLMTask(
                    displayPrompt = "Evaluating your answer...",
                    apiPrompt = "Act as an AI tutor. The student is learning about $topic. They typed this answer: '$studentAnswer'. If their answer is completely off-topic or a random word, gently tell them to stay focused on $topic. If it is on-topic, explain why it is conceptually correct or incorrect. Keep it under 3 sentences."
                )
            }
        }

        btnSubmit.setOnClickListener {
            val studentAnswer = etStudentAnswer.text.toString().trim()
            // NEW: Grab the actual question the AI asked from the TextView!
            val originalTask = tvResponse.text.toString()

            if (studentAnswer.isEmpty()) {
                Toast.makeText(this, "Please type an answer first!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ResultsActivity::class.java)
                intent.putExtra("STUDENT_ANSWER", studentAnswer)
                intent.putExtra("TASK_TOPIC", topic)
                // NEW: Pass the original task to the Results screen
                intent.putExtra("ORIGINAL_TASK", originalTask)
                startActivity(intent)
            }


        }
    }

    private fun executeLLMTask(displayPrompt: String, apiPrompt: String) {

        progressBar.visibility = View.VISIBLE
        tvPrompt.text = "Action: $displayPrompt"
        tvResponse.text = "Thinking..."

        lifecycleScope.launch {
            try {
                val generativeModel = GenerativeModel(
                    modelName = "gemini-2.5-flash",
                    apiKey = "AIzaSyAL8xlgRMeFekXwzzzEMld1Hn0CXfHc6js"
                )

                val response = generativeModel.generateContent(apiPrompt)

                progressBar.visibility = View.GONE

                markwon.setMarkdown(tvResponse, "**AI Response:**\n\n${response.text}")

            } catch (e: Exception) {
                progressBar.visibility = View.GONE
                tvResponse.text = "Error: Failed to connect to LLM. ${e.localizedMessage}"
                Toast.makeText(this@TaskDetailActivity, "API Connection Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}