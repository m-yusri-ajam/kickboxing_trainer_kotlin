package com.misterri.kickboxingtrainer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.misterri.kickboxingtrainer.database.KickboxingDatabase
import com.misterri.kickboxingtrainer.database.SessionLogDao
import com.misterri.kickboxingtrainer.databinding.ActivityDataInputBinding
import com.misterri.kickboxingtrainer.model.SessionLog
import kotlinx.coroutines.launch

class DataInputActivity : AppCompatActivity() {

    private lateinit var logDao: SessionLogDao
    private lateinit var binding: ActivityDataInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityDataInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Database DAO
        logDao = KickboxingDatabase.getDatabase(applicationContext).sessionLogDao()

        // UI references via Binding
        val saveButton: Button = binding.btnSaveLog
        val avgHrInput: EditText = binding.inputAvgHr
        val maxHrInput: EditText = binding.inputMaxHr
        val caloriesInput: EditText = binding.inputCalories
        val trainingEffectInput: EditText = binding.inputTrainingEffect

        // Display summary if available (optional, based on layout)
        val numRounds = intent.getIntExtra("EXTRA_NUM_ROUNDS", 0)
        val totalTimeMs = intent.getLongExtra("EXTRA_TOTAL_TIME", 0L)
        val comboIntensity = intent.getIntExtra("EXTRA_COMBO_INTENSITY", 0)
        val comboType = intent.getStringExtra("EXTRA_COMBO_TYPE") ?: "Mixed"

        val durationMinutes = (totalTimeMs / 1000 / 60).toInt()
        binding.sessionSummaryText.text = "Logged: $numRounds rounds ($durationMinutes min) at Intensity $comboIntensity"

        saveButton.setOnClickListener {
            val avgHr = avgHrInput.text.toString().toDoubleOrNull()
            val maxHr = maxHrInput.text.toString().toDoubleOrNull()
            val calories = caloriesInput.text.toString().toDoubleOrNull()
            val trainingEffect = trainingEffectInput.text.toString().toDoubleOrNull()

            if (avgHr == null || maxHr == null || calories == null || trainingEffect == null) {
                Toast.makeText(this, "Please fill in all fields (numeric values).", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sessionLog = SessionLog(
                date = System.currentTimeMillis(),
                durationMinutes = durationMinutes,
                roundsCompleted = numRounds,
                comboMaxIntensity = comboIntensity,
                comboTypeFilter = comboType,
                manualAvgHR = avgHr,
                manualMaxHR = maxHr,
                manualCalories = calories,
                manualTrainingEffort = trainingEffect,
                calculatedResult = 0.0 // Placeholder logic
            )

            // Save to database
            lifecycleScope.launch {
                logDao.insert(sessionLog)
                Toast.makeText(this@DataInputActivity, "Session saved!", Toast.LENGTH_SHORT).show()

                // Return to MainActivity
                // Assuming MainActivity is the entry point
                // Using generic Intent for now, assuming MainActivity exists
                val intent = Intent(this@DataInputActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
        }
    }
}
