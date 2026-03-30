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

import com.misterri.kickboxingtrainer.Combo
import kotlin.random.Random // 🔑 NEW IMPORT

class ComboSelector(private val availableCombos: List<Combo>) {

    private val usedCombos = mutableSetOf<Combo>()

    /**
     * Finds the next combo that has not been used in the current cycle.
     * If all combos have been used, the cycle resets.
     * @return The next Combo to be called, or null if the available list is empty.
     */
    fun getNextCombo(): Combo? {
        if (availableCombos.isEmpty()) return null

        // 1. Check if the cycle is complete (all combos have been used)
        if (usedCombos.size == availableCombos.size) {
            // Reset the cycle
            usedCombos.clear()
        }

        // 2. Filter the list to only include unused combos
        // We use .toMutableList() to ensure we can shuffle it safely
        val unusedCombos = availableCombos.filter { it !in usedCombos }.toMutableList()

        // 🔑 FIX: Shuffle the list before picking the first element.
        // This guarantees true random selection from the remaining choices.
        unusedCombos.shuffle(Random) // Use the standard Random class for shuffling

        // 3. Select the first element from the newly shuffled list
        val nextCombo = unusedCombos.firstOrNull() // Use firstOrNull since it's now shuffled

        // 4. If a combo was selected, mark it as used
        if (nextCombo != null) {
            usedCombos.add(nextCombo)
        }

        return nextCombo
    }

    /**
     * Clears the used list immediately (e.g., if the user changes the filter mid-session).
     */
    fun resetCycle() {
        usedCombos.clear()
    }
}