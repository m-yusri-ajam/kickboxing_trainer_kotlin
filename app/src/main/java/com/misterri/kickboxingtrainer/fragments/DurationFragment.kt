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

class DurationFragment : Fragment() {

    private lateinit var roundDurationPicker: NumberPicker
    private lateinit var restDurationPicker: NumberPicker

    private val ROUND_DURATION_STEP = 30
    private val REST_DURATION_STEP = 10

    companion object {
        fun newInstance() = DurationFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_duration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hostActivity = activity as SetupActivity

        // 1. Initialize Components
        roundDurationPicker = view.findViewById(R.id.picker_round_duration)
        restDurationPicker = view.findViewById(R.id.picker_rest_duration)
        val nextButton: Button = view.findViewById(R.id.btn_next_duration)
        val backButton: Button = view.findViewById(R.id.btn_back_duration)

        // 2. Set Up NumberPicker Ranges

        // Round Duration: 60s to 300s (5 minutes), in steps of 30s
        setupTimePicker(
            picker = roundDurationPicker,
            min = 60,
            max = 300,
            step = ROUND_DURATION_STEP,
            defaultValue = hostActivity.sessionSetupData.roundDurationSeconds ?: 180
        )

        // Rest Duration: 10s to 90s, in steps of 10s
        setupTimePicker(
            picker = restDurationPicker,
            min = 10,
            max = 90,
            step = REST_DURATION_STEP,
            defaultValue = hostActivity.sessionSetupData.restDurationSeconds ?: 60
        )

        // 3. Set up Listeners
        nextButton.setOnClickListener {
            val roundDuration = getPickerValue(roundDurationPicker, 60, ROUND_DURATION_STEP)
            val restDuration = getPickerValue(restDurationPicker, 10, REST_DURATION_STEP)

            // Save the data to the host Activity
            hostActivity.updateSetupData(SetupData().apply {
                roundDurationSeconds = roundDuration
                restDurationSeconds = restDuration
            })
            // Move to the next step
            hostActivity.goToNextStep(SetupData.STEP_DURATION)
        }

        backButton.setOnClickListener {
            // Save current state before going back
            val roundDuration = getPickerValue(roundDurationPicker, 60, ROUND_DURATION_STEP)
            val restDuration = getPickerValue(restDurationPicker, 10, REST_DURATION_STEP)
            hostActivity.updateSetupData(SetupData().apply {
                roundDurationSeconds = roundDuration
                restDurationSeconds = restDuration
            })
            hostActivity.goToPreviousStep()
        }
    }

    // Helper function to set up the NumberPickers for time/duration with specific step values
    private fun setupTimePicker(picker: NumberPicker, min: Int, max: Int, step: Int, defaultValue: Int) {
        val displayValues = ArrayList<String>()
        var value = min
        while (value <= max) {
            displayValues.add(value.toString())
            value += step
        }
        picker.minValue = 0
        picker.maxValue = displayValues.size - 1
        picker.displayedValues = displayValues.toTypedArray()

        // Set the default value by finding its index
        val defaultIndex = displayValues.indexOf(defaultValue.toString())
        if (defaultIndex >= 0) {
            picker.value = defaultIndex
        } else {
            picker.value = 0 // Fallback to minimum
        }
    }

    // Helper function to convert picker index back to the actual time value
    private fun getPickerValue(picker: NumberPicker, min: Int, step: Int): Int {
        return min + (picker.value * step)
    }
}