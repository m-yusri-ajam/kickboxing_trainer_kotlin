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
package com.misterri.kickboxingtrainer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session_log_table")
data class SessionLog(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Long = System.currentTimeMillis(),
    val durationMinutes: Int,
    val roundsCompleted: Int,
    val comboMaxIntensity: Int,
    val comboTypeFilter: String,
    val manualAvgHR: Double,
    val manualMaxHR: Double,
    val manualCalories: Double,
    val manualTrainingEffort: Double,
    val calculatedResult: Double
)