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
import androidx.fragment.app.Fragment
import com.misterri.kickboxingtrainer.SetupActivity
import com.misterri.kickboxingtrainer.R
import com.misterri.kickboxingtrainer.data.SetupData
import com.misterri.kickboxingtrainer.data.WorkoutMode
import com.misterri.kickboxingtrainer.databinding.FragmentModeBinding

class ModeFragment : Fragment() {

    private var _binding: FragmentModeBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ModeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentModeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as SetupActivity

        // --- 1. Load existing data (if any) ---
        val currentMode = activity.sessionSetupData.mode

        if (currentMode == WorkoutMode.SHARKTANK) {
            binding.radioSharkTank.isChecked = true
        } else {
            binding.radioNormal.isChecked = true
        }

        // --- 2. Set Button Listeners ---

        binding.btnPrevious.setOnClickListener {
            saveCurrentSelection()
            activity.goToPreviousStep()
        }

        binding.btnNext.setOnClickListener {
            saveCurrentSelection()
            activity.goToNextStep(SetupData.STEP_MODE)
        }
    }

    private fun saveCurrentSelection() {
        val activity = requireActivity() as SetupActivity

        // Determine the selected mode based on the checked RadioButton
        val selectedMode = when (binding.radioGroupMode.checkedRadioButtonId) {
            binding.radioSharkTank.id -> WorkoutMode.SHARKTANK
            else -> WorkoutMode.NORMAL
        }

        // 🔑 FIX: Create a standard SetupData object and ONLY set the 'mode' property.
        // This avoids the constructor error while letting updateSetupData merge the change.
        val data = SetupData()
        data.mode = selectedMode

        // Pass the updated data back to the hosting activity
        activity.updateSetupData(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}