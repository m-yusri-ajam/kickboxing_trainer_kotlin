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
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import com.misterri.kickboxingtrainer.R
import com.misterri.kickboxingtrainer.SetupActivity
import com.misterri.kickboxingtrainer.data.SetupData

class RoundsFragment : Fragment() {

    private lateinit var roundsPicker: NumberPicker

    companion object {
        fun newInstance() = RoundsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rounds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hostActivity = activity as SetupActivity

        // 1. Initialize Components
        roundsPicker = view.findViewById(R.id.picker_total_rounds)
        val nextButton: Button = view.findViewById(R.id.btn_next_rounds)
        val backButton: Button = view.findViewById(R.id.btn_back_rounds)

        // 2. Set Up NumberPicker Ranges
        roundsPicker.minValue = 1
        roundsPicker.maxValue = 12
        roundsPicker.value = hostActivity.sessionSetupData.totalRounds ?: 5 // Load existing value or default

        // 3. Set up Listeners
        nextButton.setOnClickListener {
            // Save the data to the host Activity
            hostActivity.updateSetupData(SetupData().apply { totalRounds = roundsPicker.value })
            // Move to the next step
            hostActivity.goToNextStep(SetupData.STEP_ROUNDS)
        }

        backButton.setOnClickListener {
            hostActivity.goToPreviousStep()
        }
    }
}