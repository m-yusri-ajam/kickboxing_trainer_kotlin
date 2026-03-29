package com.misterri.kickboxingtrainer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.misterri.kickboxingtrainer.R
import com.misterri.kickboxingtrainer.SetupActivity
import com.misterri.kickboxingtrainer.data.SetupData

class TypeFilterFragment : Fragment() {

    private lateinit var comboTypeSpinner: Spinner

    companion object {
        fun newInstance() = TypeFilterFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_type_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hostActivity = activity as SetupActivity

        // 1. Initialize Components
        comboTypeSpinner = view.findViewById(R.id.spinner_type_filter)
        val nextButton: Button = view.findViewById(R.id.btn_next_type)
        val backButton: Button = view.findViewById(R.id.btn_back_type)

        // 2. Set Spinner Default Value
        val comboTypes = resources.getStringArray(R.array.combo_type_options)
        val defaultType = hostActivity.sessionSetupData.comboType ?: "ANY"
        val defaultIndex = comboTypes.indexOf(defaultType)
        if (defaultIndex >= 0) {
            comboTypeSpinner.setSelection(defaultIndex)
        }

        // 3. Set up Listeners
        nextButton.setOnClickListener {
            val selectedType = comboTypeSpinner.selectedItem.toString().trim()
            // Save the data to the host Activity
            hostActivity.updateSetupData(SetupData().apply { comboType = selectedType })
            // Move to the next step (Confirmation/Finish)
            hostActivity.goToNextStep(SetupData.STEP_TYPE)
        }

        backButton.setOnClickListener {
            // Save current state before going back
            val selectedType = comboTypeSpinner.selectedItem.toString().trim()
            hostActivity.updateSetupData(SetupData().apply { comboType = selectedType })
            hostActivity.goToPreviousStep()
        }
    }
}