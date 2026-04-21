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

        taskCard.setOnClickListener {
            val intent = Intent(this, TaskDetailActivity::class.java)
            intent.putExtra("TASK_NAME", "Generated Task 1")
            startActivity(intent)
        }
    }
}