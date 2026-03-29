package com.misterri.kickboxingtrainer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.misterri.kickboxingtrainer.data.SetupData
import com.misterri.kickboxingtrainer.data.SessionConfig
import com.misterri.kickboxingtrainer.data.WorkoutMode
import com.misterri.kickboxingtrainer.fragments.ConfirmFragment
import com.misterri.kickboxingtrainer.fragments.DurationFragment
import com.misterri.kickboxingtrainer.fragments.IntensityFragment
import com.misterri.kickboxingtrainer.fragments.RoundsFragment
import com.misterri.kickboxingtrainer.fragments.TypeFilterFragment
import com.misterri.kickboxingtrainer.fragments.FrequencyFragment
import com.misterri.kickboxingtrainer.fragments.ModeFragment
import com.misterri.kickboxingtrainer.TrainingActivity // 🔑 FIX: Explicit Import to resolve reference

class SetupActivity : AppCompatActivity() {

    val sessionSetupData = SetupData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup_host)

        if (savedInstanceState == null) {
            navigateToFragment(RoundsFragment.newInstance(), addToBackStack = false)
        }
    }

    private fun navigateToFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    // --- Public methods for Fragments to call ---

    fun updateSetupData(data: SetupData) {
        data.totalRounds?.let { sessionSetupData.totalRounds = it }
        data.roundDurationSeconds?.let { sessionSetupData.roundDurationSeconds = it }
        data.restDurationSeconds?.let { sessionSetupData.restDurationSeconds = it }
        data.maxIntensity?.let { sessionSetupData.maxIntensity = it }
        data.comboType?.let { sessionSetupData.comboType = it }
        data.comboFrequency?.let { sessionSetupData.comboFrequency = it }
        data.mode?.let { sessionSetupData.mode = it }
    }

    // Method called by the current fragment to proceed
    fun goToNextStep(currentStep: Int) {
        when (currentStep) {
            SetupData.STEP_ROUNDS -> navigateToFragment(DurationFragment.newInstance())
            SetupData.STEP_DURATION -> navigateToFragment(IntensityFragment.newInstance())
            SetupData.STEP_INTENSITY -> navigateToFragment(FrequencyFragment.newInstance())
            SetupData.STEP_FREQUENCY -> navigateToFragment(TypeFilterFragment.newInstance())
            SetupData.STEP_TYPE -> navigateToFragment(ModeFragment.newInstance())
            SetupData.STEP_MODE -> navigateToFragment(ConfirmFragment.newInstance())
            SetupData.STEP_COMPLETE -> startTrainingSession()
            else -> Toast.makeText(this, "Unknown setup step", Toast.LENGTH_SHORT).show()
        }
    }

    // Method to return to the previous fragment
    fun goToPreviousStep() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }


    private fun startTrainingSession() {
        // Final sanity check before starting
        if (sessionSetupData.isValid()) {

            // Use local non-nullable vals to satisfy the compiler
            val totalRounds = sessionSetupData.totalRounds!!
            val maxIntensity = sessionSetupData.maxIntensity!!
            val roundDurationSeconds = sessionSetupData.roundDurationSeconds!!
            val restDurationSeconds = sessionSetupData.restDurationSeconds!!
            val comboType = sessionSetupData.comboType!!
            val comboFrequency = sessionSetupData.comboFrequency!!
            val mode = sessionSetupData.mode!!

            val config = SessionConfig(
                totalRounds = totalRounds,
                maxIntensity = maxIntensity,
                roundDurationSeconds = roundDurationSeconds,
                restDurationSeconds = restDurationSeconds,
                comboType = comboType,
                prepDurationSeconds = 10,
                comboFrequency = comboFrequency,
                mode = mode
            )

            // The 'TrainingActivity' reference is now resolved by the import at the top
            val intent = Intent(this, TrainingActivity::class.java).apply {
                putExtra("EXTRA_CONFIG", config)
            }
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Setup is incomplete. Please check your selections.", Toast.LENGTH_LONG).show()
        }
    }
}