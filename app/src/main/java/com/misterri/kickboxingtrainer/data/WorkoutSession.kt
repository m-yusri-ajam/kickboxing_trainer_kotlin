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

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session_log_table")
data class WorkoutSession(
    // Primary Key (Auto-generated unique ID for each session)
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Time and Date
    val timestamp: Long = System.currentTimeMillis(),

    // Configuration Details (Saved from SessionConfig)
    val mode: String, // e.g., "NORMAL" or "SHARKTANK"
    val totalRoundsConfigured: Int,
    val roundDurationSecondsConfigured: Int,
    val restDurationSecondsConfigured: Int,
    val maxIntensityConfigured: Int,
    val comboTypeConfigured: String,

    // Outcome Metrics
    val totalTimeInSeconds: Int
)