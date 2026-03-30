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

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// 🔑 NEW ENUM: Defines the available training modes
enum class WorkoutMode {
    NORMAL,
    SHARKTANK
}

@Parcelize
data class SessionConfig(
    // Timer Settings
    val totalRounds: Int,
    val roundDurationSeconds: Int,
    val restDurationSeconds: Int,

    val maxIntensity: Int,
    val comboType: String,

    // Added fields to support defaults in TrainingActivity
    val prepDurationSeconds: Int = 10,
    val comboFrequency: Int, // MUST BE SUPPLIED BY SETUP WIZARD

    // 🔑 NEW FIELD: Mode selection, default to NORMAL
    val mode: WorkoutMode = WorkoutMode.NORMAL
) : Parcelable