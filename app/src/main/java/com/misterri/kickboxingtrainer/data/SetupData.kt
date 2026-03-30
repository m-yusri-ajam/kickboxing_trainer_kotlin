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
package com.misterri.kickboxingtrainer.data

class SetupData {
    var totalRounds: Int? = null
    var maxIntensity: Int? = null
    var roundDurationSeconds: Int? = null
    var restDurationSeconds: Int? = null
    var comboType: String? = null
    var comboFrequency: Int? = null
    // 🔑 1. NEW FIELD: To store the selected workout mode
    var mode: WorkoutMode? = null

    companion object {
        const val STEP_ROUNDS = 1
        const val STEP_DURATION = 2
        const val STEP_INTENSITY = 3
        const val STEP_FREQUENCY = 4
        const val STEP_TYPE = 5
        // 🔑 2. NEW STEP: Mode selection step
        const val STEP_MODE = 6
        const val STEP_COMPLETE = 7  // Shifted again
    }

    fun isValid(): Boolean {
        // 🔑 3. NEW CHECK: Ensure mode is not null
        return totalRounds != null &&
                maxIntensity != null &&
                roundDurationSeconds != null &&
                restDurationSeconds != null &&
                !comboType.isNullOrBlank() &&
                comboFrequency != null &&
                mode != null
    }
}