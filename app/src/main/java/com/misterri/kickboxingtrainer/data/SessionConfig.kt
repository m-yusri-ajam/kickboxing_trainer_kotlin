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