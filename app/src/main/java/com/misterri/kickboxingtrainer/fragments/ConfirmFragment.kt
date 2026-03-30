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
package com.misterri.kickboxingtrainer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.misterri.kickboxingtrainer.R
import com.misterri.kickboxingtrainer.SetupActivity
import com.misterri.kickboxingtrainer.data.SetupData

class ConfirmFragment : Fragment() {

    companion object {
        fun newInstance() = ConfirmFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hostActivity = activity as SetupActivity
        val setupData = hostActivity.sessionSetupData

        // 1. Initialize Components
        val summaryTextView: TextView = view.findViewById(R.id.session_summary_text)
        val startButton: Button = view.findViewById(R.id.btn_begin_training)
        val backButton: Button = view.findViewById(R.id.btn_back_confirm)

        // 2. Display Summary
        summaryTextView.text = buildSummaryText(setupData)

        // 3. Set up Listeners
        startButton.setOnClickListener {
            // Final step: tell the host to start the training session
            hostActivity.goToNextStep(SetupData.STEP_COMPLETE)
        }

        backButton.setOnClickListener {
            hostActivity.goToPreviousStep()
        }
    }

    private fun buildSummaryText(data: SetupData): String {
        return "Rounds: ${data.totalRounds ?: "N/A"}\n" +
                "Max Intensity: ${data.maxIntensity ?: "N/A"}\n" +
                "Combo Frequency: ${data.comboFrequency ?: "MISSING!"}s\n" + // 🔑 ADDED FREQUENCY CHECK
                "Round Duration: ${data.roundDurationSeconds ?: "N/A"}s\n" +
                "Rest Duration: ${data.restDurationSeconds ?: "N/A"}s\n" +
                "Combo Type Filter: ${data.comboType ?: "N/A"}"
    }
}