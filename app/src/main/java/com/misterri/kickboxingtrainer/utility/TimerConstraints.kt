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
package com.misterri.kickboxingtrainer.utility

object TimerConstraints {
    // Defines the valid range (min and max) for Round and Rest duration based on intensity

    // The key is Int (Intensity), the value is Pair<Pair<Int, Int>, Pair<Int, Int>>
    // (RoundRange to RestRange)
    val CONSTRAINTS = mapOf(
        1 to // Low Intensity: Round (120-180s) to Rest (90-120s)
                (Pair(2 * 60, 3 * 60) to Pair(90, 120)),
        2 to // Medium Intensity: Round (180-240s) to Rest (60-90s)
                (Pair(3 * 60, 4 * 60) to Pair(60, 90)),
        3 to // High Intensity: Round (240-300s) to Rest (30-60s)
                (Pair(4 * 60, 5 * 60) to Pair(30, 60))
    )

    /**
     * @param intensity The max intensity selected by the user (1, 2, or 3).
     * @return A Pair where the first element is the Round Range (min to max in seconds)
     * and the second element is the Rest Range (min to max in seconds).
     */
    fun getRanges(intensity: Int): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        // Use the safe get operator and default to the value for Intensity 1 if null
        return CONSTRAINTS[intensity] ?: CONSTRAINTS.getValue(1)
    }
}