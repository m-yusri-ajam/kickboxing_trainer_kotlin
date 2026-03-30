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

import android.annotation.SuppressLint
import com.misterri.kickboxingtrainer.model.SessionLog

object SessionCalculator {

    // This function takes all the necessary data inputs and returns the calculated KPI.
    @SuppressLint("DefaultLocale")
    fun calculateKPI(
        trainingEffect: Double,
        calories: Double,
        maxHR: Double,
        durationMinutes: Int
    ): Double {

        // Safety check to prevent division by zero (if calories somehow equals 0)
        if (calories == 0.0) return 0.0

        val kpiResult = (trainingEffect / calories) * maxHR * durationMinutes

        // We can round the result for cleaner storage/display
        return String.format("%.2f", kpiResult).toDouble()
    }

    // This function creates the final SessionLog object
    fun createSessionLog(
        durationMinutes: Int,
        roundsCompleted: Int,
        comboMaxIntensity: Int,
        comboTypeFilter: String,
        manualAvgHR: Double,
        manualMaxHR: Double,
        manualCalories: Double,
        manualTrainingEffort: Double
    ): SessionLog {

        // 1. Calculate the required KPI
        val kpi = calculateKPI(
            manualTrainingEffort,
            manualCalories,
            manualMaxHR,
            durationMinutes
        )

        // 2. Return the complete SessionLog object
        return SessionLog(
            durationMinutes = durationMinutes,
            roundsCompleted = roundsCompleted,
            comboMaxIntensity = comboMaxIntensity,
            comboTypeFilter = comboTypeFilter,
            manualAvgHR = manualAvgHR,
            manualMaxHR = manualMaxHR,
            manualCalories = manualCalories,
            manualTrainingEffort = manualTrainingEffort,
            calculatedResult = kpi // <-- The final calculated KPI
        )
    }
}