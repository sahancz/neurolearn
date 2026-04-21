package com.example.neurolearn
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class InterestsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interests)

        val interestGroup = findViewById<ChipGroup>(R.id.cg_interests)
        val nextBtn = findViewById<Button>(R.id.btn_interest_next)

        nextBtn.setOnClickListener {
            val selectedInterests = ArrayList<String>()

            for (i in 0 until interestGroup.childCount) {
                val chip = interestGroup.getChildAt(i) as? Chip
                if (chip?.isChecked == true) {
                    selectedInterests.add(chip.text.toString())
                }
            }

            val intent = Intent(this, DashboardActivity::class.java)
            intent.putStringArrayListExtra("USER_INTERESTS", selectedInterests)
            startActivity(intent)
        }
    }
}