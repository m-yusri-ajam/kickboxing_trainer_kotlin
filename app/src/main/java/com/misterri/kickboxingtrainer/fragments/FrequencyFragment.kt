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

class FrequencyFragment : Fragment() {

    private lateinit var frequencyPicker: NumberPicker

    companion object {
        fun newInstance() = FrequencyFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_frequency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hostActivity = activity as SetupActivity

        // 1. Initialize Components
        frequencyPicker = view.findViewById(R.id.picker_combo_frequency)
        val nextButton: Button = view.findViewById(R.id.btn_next_frequency)
        val backButton: Button = view.findViewById(R.id.btn_back_frequency)

        // 2. Set Up NumberPicker Ranges (1s to 3s)
        frequencyPicker.minValue = 1
        frequencyPicker.maxValue = 3
        frequencyPicker.value = hostActivity.sessionSetupData.comboFrequency ?: 2

        // 3. Set up Listeners
        nextButton.setOnClickListener {
            // Save the data to the host Activity
            hostActivity.updateSetupData(SetupData().apply { comboFrequency = frequencyPicker.value })
            // Move to the next step
            hostActivity.goToNextStep(SetupData.STEP_FREQUENCY)
        }

        backButton.setOnClickListener {
            // Save current state before going back
            hostActivity.updateSetupData(SetupData().apply { comboFrequency = frequencyPicker.value })
            hostActivity.goToPreviousStep()
        }
    }
}