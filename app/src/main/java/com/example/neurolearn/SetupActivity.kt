package com.example.neurolearn

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)

        val createAccountBtn = findViewById<Button>(R.id.btn_create_account)

        createAccountBtn.setOnClickListener {
            // Proceed to the Interests selection screen
            val intent = Intent(this, InterestsActivity::class.java)
            startActivity(intent)
        }
    }
}