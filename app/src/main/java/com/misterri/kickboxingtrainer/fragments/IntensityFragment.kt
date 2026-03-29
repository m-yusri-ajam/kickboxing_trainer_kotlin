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

class IntensityFragment : Fragment() {

    private lateinit var intensityPicker: NumberPicker

    companion object {
        fun newInstance() = IntensityFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_intensity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hostActivity = activity as SetupActivity

        // 1. Initialize Components
        intensityPicker = view.findViewById(R.id.picker_max_intensity)
        val nextButton: Button = view.findViewById(R.id.btn_next_intensity)
        val backButton: Button = view.findViewById(R.id.btn_back_intensity)

        // 2. Set Up NumberPicker Ranges
        intensityPicker.minValue = 1
        intensityPicker.maxValue = 7
        intensityPicker.value = hostActivity.sessionSetupData.maxIntensity ?: 3

        // 3. Set up Listeners
        nextButton.setOnClickListener {
            // Save the data to the host Activity
            hostActivity.updateSetupData(SetupData().apply { maxIntensity = intensityPicker.value })
            // Move to the next step
            hostActivity.goToNextStep(SetupData.STEP_INTENSITY)
        }

        backButton.setOnClickListener {
            // Save current state before going back
            hostActivity.updateSetupData(SetupData().apply { maxIntensity = intensityPicker.value })
            hostActivity.goToPreviousStep()
        }
    }
}