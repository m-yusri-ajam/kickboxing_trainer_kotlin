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

object ComboFrequency {
    // Defines the valid random delay range (min and max in seconds) between callouts

    // Key: Max Intensity (1, 2, 3) -> Value: Pair(Min Delay, Max Delay)
    val DELAY_CONSTRAINTS = mapOf(
        1 to Pair(8, 12),  // Low Intensity: 8 to 12 seconds
        2 to Pair(5, 8),   // Medium Intensity: 5 to 8 seconds
        3 to Pair(3, 5)    // High Intensity: 3 to 5 seconds
    )

    // Internal helper function (renamed from getRandomDelay for clarity)
    private fun getDelaySeconds(intensity: Int): Long {
        val range = DELAY_CONSTRAINTS[intensity] ?: DELAY_CONSTRAINTS.getValue(2) // Default to Medium

        // Generate a random long between the min and max (inclusive)
        return (range.first.toLong()..range.second.toLong()).random()
    }

    /**
     * @param intensity The max intensity selected by the user (1, 2, or 3).
     * @return A random delay (in milliseconds) within the defined constraints for that level.
     */
    fun getDelayMs(intensity: Int): Long {
        val delaySeconds = getDelaySeconds(intensity)

        // Return the result in milliseconds as the TrainerViewModel expects
        return delaySeconds * 1000L
    }
}