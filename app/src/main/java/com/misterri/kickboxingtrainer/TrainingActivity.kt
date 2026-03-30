/**
 * -----------------------------------------------------------------------------
 * Copyright (c) 2026 Mogamad Yusri Ajam. All rights reserved.
 * * This source code is the proprietary property of Mogamad Yusri Ajam.
 * Unauthorized copying, modification, or distribution of this file, 
 * via any medium, is strictly prohibited.
 *
 * Project: KickboxingTrainer (HIIT Performance)
 * Contact: mogamadyusriajam@gmail.com
 * -----------------------------------------------------------------------------
 */
package com.misterri.kickboxingtrainer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.misterri.kickboxingtrainer.data.SessionConfig
import com.misterri.kickboxingtrainer.databinding.ActivityTrainingBinding
import com.misterri.kickboxingtrainer.viewmodel.TrainerViewModel
import com.misterri.kickboxingtrainer.viewmodel.TrainerViewModelFactory
import kotlinx.coroutines.launch

class TrainingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrainingBinding

    // 1. Get the SessionConfig passed from SetupActivity
    private val sessionConfig: SessionConfig by lazy {
        intent.getParcelableExtra("EXTRA_CONFIG") ?: SessionConfig(
            totalRounds = 3,
            roundDurationSeconds = 180,
            restDurationSeconds = 60,
            maxIntensity = 2,
            comboType = "ALL",
            comboFrequency = 2
        )
    }

    // 2. Instantiate the ViewModel using the factory
    private val viewModel: TrainerViewModel by viewModels {
        TrainerViewModelFactory(application, sessionConfig)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔑 NEW FEATURE: Keep the screen awake while the activity is visible
        window.addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // Setup the ViewModel observer
        observeViewModel()

        // Setup button listeners
        setupListeners()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            // Observe the timer
            viewModel.timerState.collect { timeRemaining ->
                binding.textTimerDisplay.text = formatTime(timeRemaining)
                // Update progress bar based on the current phase duration
                val duration = when(viewModel.workoutStatus.value) {
                    "ROUND" -> viewModel.roundDurationMs
                    "SHARKTANK" -> viewModel.SHARK_TANK_DURATION_MS // Uses SHARK TANK duration
                    "REST" -> viewModel.restDurationMs
                    else -> 1000L
                }
                // Safely calculate progress (0 to 100)
                val progress = if (duration > 0) ((duration - timeRemaining) * 100 / duration).toInt() else 0
                binding.circularProgressBar.progress = progress
            }
        }

        lifecycleScope.launch {
            // Observe the status (PREP, ROUND, REST, PAUSED, SHARKTANK)
            viewModel.workoutStatus.collect { status ->
                binding.textRoundStatus.text = status
                binding.btnPauseResume.text = if (status == "PAUSED") "RESUME" else "PAUSE"
            }
        }

        lifecycleScope.launch {
            // Observe the combo
            viewModel.lastCalledCombo.collect { combo ->
                binding.textComboCallout.text = combo
            }
        }

        lifecycleScope.launch {
            // Observe the round count
            viewModel.currentRound.collect { round ->
                binding.textRoundCounter.text = "$round of ${sessionConfig.totalRounds} Rounds"
            }
        }

        lifecycleScope.launch {
            // Observe navigation events (e.g., end of workout)
            viewModel.navigationEvent.collect { event ->
                if (event["action"] == "NAVIGATE_TO_LOG") {
                    // Navigate to DataInputActivity
                    val totalWorkoutTimeMs = viewModel.roundDurationMs * viewModel.config.totalRounds
                    navigateToDataInput(viewModel.config, totalWorkoutTimeMs)
                }
            }
        }
    }

    private fun setupListeners() {
        binding.btnPauseResume.setOnClickListener {
            viewModel.togglePause()
        }
        binding.btnEndTraining.setOnClickListener {
            viewModel.endWorkout()
        }
    }

    private fun formatTime(ms: Long): String {
        val totalSeconds = ms / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%d:%02d", minutes, seconds)
    }

    private fun navigateToDataInput(config: SessionConfig, totalWorkoutTimeMs: Long) {
        val intent = Intent(this, DataInputActivity::class.java).apply {
            putExtra("EXTRA_NUM_ROUNDS", config.totalRounds)
            putExtra("EXTRA_TOTAL_TIME", totalWorkoutTimeMs)
            putExtra("EXTRA_COMBO_INTENSITY", config.maxIntensity)
            putExtra("EXTRA_COMBO_TYPE", config.comboType)
        }
        startActivity(intent)
        finish()
    }
}
