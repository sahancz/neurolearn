package com.example.neurolearn

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var tvPrompt: TextView
    private lateinit var tvResponse: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnGetHint: Button
    private lateinit var btnExplain: Button
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        
        tvPrompt = findViewById(R.id.tv_llm_prompt)
        tvResponse = findViewById(R.id.tv_llm_response)
        progressBar = findViewById(R.id.pb_loading)
        btnGetHint = findViewById(R.id.btn_generate_hint)
        btnExplain = findViewById(R.id.btn_explain_logic)
        btnSubmit = findViewById(R.id.btn_submit_task)

        //LLM Tasks
        executeLLMTask("Generate an introductory task for Neural Networks.")

        btnGetHint.setOnClickListener {
            executeLLMTask("Provide a subtle hint for the question: What is a Neural Network?")
        }

        btnExplain.setOnClickListener {
            executeLLMTask("Explain why 'Backpropagation' is the correct method for training weights.")
        }

        btnSubmit.setOnClickListener {
            val intent = Intent(this, ResultsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun executeLLMTask(prompt: String) {
        progressBar.visibility = View.VISIBLE
        tvPrompt.text = "Prompt: $prompt"
        tvResponse.text = ""

        lifecycleScope.launch {
            try {
                // Manual delay to demonstrate loading state
                kotlinx.coroutines.delay(1500)
                progressBar.visibility = View.GONE
                tvResponse.text = "AI Response: This is a simulated AI response tailored to your interests in AI."
            } catch (e: Exception) {
                progressBar.visibility = View.GONE
                tvResponse.text = "Error: Failed to connect to LLM backend."
                Toast.makeText(this@TaskDetailActivity, "Connection Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
