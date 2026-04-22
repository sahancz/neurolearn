package com.example.neurolearn

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val welcomeText = findViewById<TextView>(R.id.tv_welcome_name)
        val taskCard = findViewById<LinearLayout>(R.id.ll_task_card)
        val taskTitle = findViewById<TextView>(R.id.tv_task_title)

        welcomeText.text = "Hello, Sahan"

        //  grab innterests picked from InterestsActivity
        val userInterests = intent.getStringArrayListExtra("USER_INTERESTS")

        // Default to neural networks if no selection, otherwise use the given choice
        val selectedTopic = if (!userInterests.isNullOrEmpty()) userInterests[0] else "Neural Networks"

        // Update the UI to show personalized topic
        taskTitle.text = "$selectedTopic Task"

        taskCard.setOnClickListener {
            val intent = Intent(this, TaskDetailActivity::class.java)
            // Pass the chosen topic to the AI screen
            intent.putExtra("TASK_TOPIC", selectedTopic)
            startActivity(intent)
        }
    }
}