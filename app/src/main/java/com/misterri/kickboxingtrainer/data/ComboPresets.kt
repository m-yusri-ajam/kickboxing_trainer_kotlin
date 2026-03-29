// File: data/ComboPresets.kt (UPDATED KICK TOKEN)

package com.misterri.kickboxingtrainer.data

import com.misterri.kickboxingtrainer.Combo

object ComboPresets {

    val ALL_COMBOS = listOf(
        // ====================================================================
        // === INTENSITY 1: BASIC SETUP & OPENERS (30 Combos) ===
        // ====================================================================

        // Boxing Basics (Head & Body)
        Combo(name = "1-2", intensity = 1, type = "Hand Only"),
        Combo(name = "2-3", intensity = 1, type = "Hand Only"),
        Combo(name = "3-2", intensity = 1, type = "Hand Only"),
        Combo(name = "1B-2", intensity = 1, type = "Hand Only"),
        Combo(name = "2-3B", intensity = 1, type = "Hand Only"),
        Combo(name = "1-1", intensity = 1, type = "Hand Only"),
        Combo(name = "2-B", intensity = 1, type = "Hand Only"),
        Combo(name = "3-B", intensity = 1, type = "Hand Only"),
        Combo(name = "4-3", intensity = 1, type = "Hand Only"),
        Combo(name = "2-1", intensity = 1, type = "Hand Only"),
        Combo(name = "5-2", intensity = 1, type = "Hand Only"),
        Combo(name = "6-3", intensity = 1, type = "Hand Only"),

        // Kick/Knee Basics
        Combo(name = "L", intensity = 1, type = "Kick Only"),
        Combo(name = "R", intensity = 1, type = "Kick Only"),
        Combo(name = "Knee", intensity = 1, type = "Kick Only"),
        Combo(name = "R-2", intensity = 1, type = "Hand & Kick"),
        Combo(name = "L-1", intensity = 1, type = "Hand & Kick"),
        Combo(name = "1-R", intensity = 1, type = "Hand & Kick"),
        Combo(name = "2-L", intensity = 1, type = "Hand & Kick"),

        // Defense/Footwork Basics
        Combo(name = "S-2", intensity = 1, type = "Defense & Counter"),
        Combo(name = "C-2", intensity = 1, type = "Defense & Counter"),
        Combo(name = "PIVOT-1", intensity = 1, type = "Defense & Footwork"),
        Combo(name = "DUCK-2", intensity = 1, type = "Defense & Counter"),
        Combo(name = "PULL-2", intensity = 1, type = "Defense & Counter"),
        Combo(name = "BLOCK-3", intensity = 1, type = "Defense Only"),
        Combo(name = "C-R", intensity = 1, type = "Kick & Defense"),
        Combo(name = "SLIP-3", intensity = 1, type = "Defense & Counter"),
        Combo(name = "SHADOW", intensity = 1, type = "Defense & Footwork"),
        Combo(name = "2-C", intensity = 1, type = "Defense & Counter"),
        Combo(name = "1-S", intensity = 1, type = "Defense & Counter"),
        Combo(name = "MOVE-2", intensity = 1, type = "Defense & Footwork"),
        Combo(name = "1-2-PULL", intensity = 1, type = "Defense & Counter"),


        // ====================================================================
        // === INTENSITY 2: VOLUME & MID-RANGE COUNTERS (30 Combos) ===
        // ====================================================================

        // Boxing Power/Volume
        Combo(name = "1-2-3", intensity = 2, type = "Hand Only"),
        Combo(name = "2-3-2", intensity = 2, type = "Hand Only"),
        Combo(name = "1-2-1", intensity = 2, type = "Hand Only"),
        Combo(name = "3-2-3", intensity = 2, type = "Hand Only"),
        Combo(name = "4-3-2", intensity = 2, type = "Hand Only"),
        Combo(name = "1-1B-2", intensity = 2, type = "Hand Only"),
        Combo(name = "2-3B-2", intensity = 2, type = "Hand Only"),
        Combo(name = "1-2-BODY", intensity = 2, type = "Hand Only"),
        Combo(name = "3-2-B", intensity = 2, type = "Hand Only"),
        Combo(name = "2-3-4", intensity = 2, type = "Hand Only"),
        Combo(name = "2-4B-3", intensity = 2, type = "Hand Only"),
        Combo(name = "1-5-2", intensity = 2, type = "Hand Only"),
        Combo(name = "2-6-3", intensity = 2, type = "Hand Only"),
        Combo(name = "5B-2", intensity = 2, type = "Hand Only"),
        Combo(name = "2-6B", intensity = 2, type = "Hand Only"),

        // Kickboxing Finishers
        Combo(name = "1-2-R", intensity = 2, type = "Hand & Kick"),
        Combo(name = "2-3-L", intensity = 2, type = "Hand & Kick"),
        Combo(name = "R-3-2", intensity = 2, type = "Hand & Kick"),
        Combo(name = "L-3-2", intensity = 2, type = "Hand & Kick"),
        Combo(name = "2-Knee-2", intensity = 2, type = "Hand & Kick"),
        Combo(name = "3-Knee", intensity = 2, type = "Hand & Kick"),
        Combo(name = "1-2-KICK_ANY", intensity = 2, type = "Hand & Kick"), // 🔑 FIX: Changed KICK to KICK_ANY

        // Defense and Countering
        Combo(name = "S-2-3", intensity = 2, type = "Defense & Counter"),
        Combo(name = "C-3-2", intensity = 2, type = "Defense & Counter"),
        Combo(name = "DUCK-3-2", intensity = 2, type = "Defense & Counter"),
        Combo(name = "1-2-S-3", intensity = 2, type = "Defense & Counter"),
        Combo(name = "PIVOT-3-2", intensity = 2, type = "Defense & Footwork"),
        Combo(name = "PULL-2-3", intensity = 2, type = "Defense & Counter"),
        Combo(name = "BLOCK-2-3", intensity = 2, type = "Defense & Counter"),
        Combo(name = "2-3-C-2", intensity = 2, type = "Defense & Counter"),
        Combo(name = "1-2-DUCK", intensity = 2, type = "Defense & Footwork"),
        Combo(name = "2-DUCK-3", intensity = 2, type = "Defense & Counter"),
        Combo(name = "1-2-SHADOW", intensity = 2, type = "Defense & Footwork"),
        Combo(name = "S-2-B", intensity = 2, type = "Defense & Counter"),


        // ====================================================================
        // === INTENSITY 3: LEVEL CHANGE, COMPLEX COUNTERS (30 Combos) ===
        // ====================================================================

        // Advanced Boxing/Level Changes
        Combo(name = "1-2-3-2", intensity = 3, type = "Hand Only"),
        Combo(name = "2-3-2-3", intensity = 3, type = "Hand Only"),
        Combo(name = "1-2B-1-2", intensity = 3, type = "Hand Only"),
        Combo(name = "3-2-3B-2", intensity = 3, type = "Hand Only"),
        Combo(name = "1-1-2-3", intensity = 3, type = "Hand Only"),
        Combo(name = "3-2-1B-2", intensity = 3, type = "Hand Only"),
        Combo(name = "2-4-3-2", intensity = 3, type = "Hand Only"),
        Combo(name = "1-2-3-B", intensity = 3, type = "Hand Only"),
        Combo(name = "2-3-2B-3", intensity = 3, type = "Hand Only"),
        Combo(name = "4B-3-2-1", intensity = 3, type = "Hand Only"),
        Combo(name = "1B-2-3-2", intensity = 3, type = "Hand Only"),
        Combo(name = "2-4-3-4", intensity = 3, type = "Hand Only"),
        Combo(name = "DUCK-4-3-2", intensity = 3, type = "Defense & Counter"),
        Combo(name = "ROLL-2-3-2", intensity = 3, type = "Defense & Counter"),
        Combo(name = "S-S-2-3", intensity = 3, type = "Defense & Counter"),
        Combo(name = "5-6-2", intensity = 3, type = "Hand Only"),
        Combo(name = "2-7-3", intensity = 3, type = "Hand Only"),
        Combo(name = "3-8-2", intensity = 3, type = "Hand Only"),
        Combo(name = "5B-2-3", intensity = 3, type = "Hand Only"),
        Combo(name = "2-6B-3", intensity = 3, type = "Hand Only"),

        // Advanced Kickboxing
        Combo(name = "1-2-3-R", intensity = 3, type = "Hand & Kick"),
        Combo(name = "R-L-2", intensity = 3, type = "Hand & Kick"),
        Combo(name = "2-3-R-2", intensity = 3, type = "Hand & Kick"),
        Combo(name = "L-R-2", intensity = 3, type = "Hand & Kick"),
        Combo(name = "1-2-Knee-2", intensity = 3, type = "Hand & Kick"),
        Combo(name = "L-S-2", intensity = 3, type = "Defense & Kick"),
        Combo(name = "2-3-L-2", intensity = 3, type = "Hand & Kick"),
        Combo(name = "1B-2-R", intensity = 3, type = "Hand & Kick"),
        Combo(name = "3-2-Knee", intensity = 3, type = "Hand & Kick"),

        // Complex Defense
        Combo(name = "DUCK-PULL-2", intensity = 3, type = "Defense & Counter"),
        Combo(name = "C-S-2-3", intensity = 3, type = "Defense & Counter"),
        Combo(name = "PIVOT-S-2", intensity = 3, type = "Defense & Footwork"),
        Combo(name = "PULL-2-3-2", intensity = 3, type = "Defense & Counter"),
        Combo(name = "1-2-DUCK-3-2", intensity = 3, type = "Defense & Counter"),
        Combo(name = "S-2-3-R", intensity = 3, type = "Defense & Kick"),


        // ====================================================================
        // === INTENSITY 4: HIGH VOLUME & SUSTAINED PRESSURE (30 Combos) ===
        // ====================================================================

        // High Volume Boxing
        Combo(name = "1-2-3-2-1", intensity = 4, type = "Hand Only"),
        Combo(name = "2-3-2-3-2", intensity = 4, type = "Hand Only"),
        Combo(name = "1-2-3-2B-3", intensity = 4, type = "Hand Only"),
        Combo(name = "4-3-2-1-2", intensity = 4, type = "Hand Only"),
        Combo(name = "2B-2-3-2-3", intensity = 4, type = "Hand Only"),
        Combo(name = "3-2-3-2-3", intensity = 4, type = "Hand Only"),
        Combo(name = "1-2-3-4-2", intensity = 4, type = "Hand Only"),
        Combo(name = "1B-2-3-2-3B", intensity = 4, type = "Hand Only"),
        Combo(name = "S-2-3-2-3", intensity = 4, type = "Defense & Counter"),
        Combo(name = "DUCK-2-3-2-3", intensity = 4, type = "Defense & Counter"),
        Combo(name = "ROLL-3-2-3-2", intensity = 4, type = "Defense & Counter"),
        Combo(name = "2-3-2-1B-2", intensity = 4, type = "Hand Only"),
        Combo(name = "4B-3-2-3-2", intensity = 4, type = "Hand Only"),
        Combo(name = "1-2-4B-3-2", intensity = 4, type = "Hand Only"),
        Combo(name = "1-2-1B-2-1", intensity = 4, type = "Hand Only"),
        Combo(name = "5-2-3-2", intensity = 4, type = "Hand Only"),
        Combo(name = "2-6-3-2", intensity = 4, type = "Hand Only"),
        Combo(name = "7-2-3-2", intensity = 4, type = "Hand Only"),
        Combo(name = "2-8B-3-2", intensity = 4, type = "Hand Only"),
        Combo(name = "5B-2-3-2", intensity = 4, type = "Hand Only"),

        // High-Volume Kickboxing
        Combo(name = "1-2-3-2-R", intensity = 4, type = "Hand & Kick"),
        Combo(name = "L-2-3-2", intensity = 4, type = "Hand & Kick"),
        Combo(name = "R-L-3-2", intensity = 4, type = "Hand & Kick"),
        Combo(name = "1-2-Knee-Knee", intensity = 4, type = "Hand & Kick"),
        Combo(name = "3-2-L-3", intensity = 4, type = "Hand & Kick"),
        Combo(name = "1-2B-3-R", intensity = 4, type = "Hand & Kick"),
        Combo(name = "S-2-3-R-2", intensity = 4, type = "Defense & Kick"),
        Combo(name = "L-R-3-2", intensity = 4, type = "Hand & Kick"),
        Combo(name = "2-3B-2-L", intensity = 4, type = "Hand & Kick"),

        // High-Volume Defense/Counter
        Combo(name = "C-2-3-2-3", intensity = 4, type = "Defense & Counter"),
        Combo(name = "PIVOT-1-2-3", intensity = 4, type = "Defense & Footwork"),
        Combo(name = "S-2-3-2-L", intensity = 4, type = "Defense & Kick"),
        Combo(name = "PULL-2-3-2-3", intensity = 4, type = "Defense & Counter"),
        Combo(name = "SHADOW-1-2-3-2", intensity = 4, type = "Defense & Footwork"),
        Combo(name = "DUCK-3-2-1B-2", intensity = 4, type = "Defense & Counter"),

        // ====================================================================
        // === INTENSITY 5: FINISHERS, HIGH-RISK, ALL-OUT (30 Combos) ===
        // ====================================================================

        // Knockout/High Power Boxing
        Combo(name = "2-3-2-4-3-2", intensity = 5, type = "Hand Only"),
        Combo(name = "1-2-3-2B-3-2", intensity = 5, type = "Hand Only"),
        Combo(name = "4-3-4-2-3-2", intensity = 5, type = "Hand Only"),
        Combo(name = "1B-2-3-2-4", intensity = 5, type = "Hand Only"),
        Combo(name = "2-DUCK-3-2-3-2", intensity = 5, type = "Defense & Counter"),
        Combo(name = "3-2-3-4-3-2", intensity = 5, type = "Hand Only"),
        Combo(name = "1-2-3-2-1-B", intensity = 5, type = "Hand Only"),
        Combo(name = "ROLL-3-2-3-2-3", intensity = 5, type = "Defense & Counter"),
        Combo(name = "1B-2-3-2-4B-3", intensity = 5, type = "Hand Only"),
        Combo(name = "S-2-3-2-1-2", intensity = 5, type = "Defense & Counter"),
        Combo(name = "2-3-4-3-2-3", intensity = 5, type = "Hand Only"),
        Combo(name = "1-1B-2-3-2-1", intensity = 5, type = "Hand Only"),
        Combo(name = "DUCK-4-3-2-3-2", intensity = 5, type = "Defense & Counter"),
        Combo(name = "2-3-2-1-1B-2", intensity = 5, type = "Hand Only"),
        Combo(name = "1-2-4-3-2-R", intensity = 5, type = "Hand & Kick"),
        Combo(name = "1-5-2-6-3", intensity = 5, type = "Hand Only"),
        Combo(name = "2-7-3-8-2", intensity = 5, type = "Hand Only"),
        Combo(name = "5B-2-3-2-6B", intensity = 5, type = "Hand Only"),
        Combo(name = "7B-2-3-2-4", intensity = 5, type = "Hand Only"),
        Combo(name = "2-3-2-8B-3", intensity = 5, type = "Hand Only"),

        // Advanced KO Kickboxing
        Combo(name = "R-L-R", intensity = 5, type = "Kick Only"),
        Combo(name = "1-2-3-H_KICK", intensity = 5, type = "Hand & Kick"),
        Combo(name = "L-2-3-2-R", intensity = 5, type = "Hand & Kick"),
        Combo(name = "2-3-2-Knee-2", intensity = 5, type = "Hand & Kick"),
        Combo(name = "1-2-R-3-2", intensity = 5, type = "Hand & Kick"),
        Combo(name = "1B-2-3-R-2", intensity = 5, type = "Hand & Kick"),
        Combo(name = "L-S-2-3-2", intensity = 5, type = "Defense & Kick"),
        Combo(name = "2-3-4-L-3", intensity = 5, type = "Hand & Kick"),
        Combo(name = "R-L-2-3-2", intensity = 5, type = "Hand & Kick"),
        Combo(name = "1-2-3-C-3-R", intensity = 5, type = "Defense & Kick"),

        // Ultimate Counters
        Combo(name = "S-ROLL-2-3-2", intensity = 5, type = "Defense & Counter"),
        Combo(name = "C-S-2-3-2-R", intensity = 5, type = "Defense & Kick"),
        Combo(name = "1-2-PULL-4-3", intensity = 5, type = "Defense & Counter"),
        Combo(name = "PIVOT-4-3-2", intensity = 5, type = "Defense & Footwork"),
        Combo(name = "SHADOW-1-2-R", intensity = 5, type = "Defense & Kick"),
        Combo(name = "2-C-2-S-3-2", intensity = 5, type = "Defense & Counter"),
        Combo(name = "1-2-3-DUCK-3-2", intensity = 5, type = "Defense & Counter"),
        Combo(name = "ROLL-4-3-2-3B", intensity = 5, type = "Defense & Counter"),


        // ====================================================================
        // === INTENSITY 6: TECHNICAL MASTERY, LONG SEQUENCES (15 Combos) ===
        // ====================================================================

        Combo(name = "1-2-1B-2-3B-2", intensity = 6, type = "Hand Only"),
        Combo(name = "DUCK-4-3-2-3-2-3", intensity = 6, type = "Defense & Counter"),
        Combo(name = "2-3-2-3-4-3B-2", intensity = 6, type = "Hand Only"),
        Combo(name = "S-2-3-2-4B-3-2", intensity = 6, type = "Defense & Counter"),
        Combo(name = "1-2-1B-2-3-PULL-2", intensity = 6, type = "Defense & Counter"),
        Combo(name = "5-6-7-8-3-2-1", intensity = 6, type = "Hand Only"),
        Combo(name = "2-6B-3-5B-2-4", intensity = 6, type = "Hand Only"),

        Combo(name = "R-L-R-3-2-Knee", intensity = 6, type = "Hand & Kick"),
        Combo(name = "1-2-3-2-R-L-2", intensity = 6, type = "Hand & Kick"),
        Combo(name = "1B-2-3-4-Knee-2", intensity = 6, type = "Hand & Kick"),
        Combo(name = "L-S-2-3-2-L", intensity = 6, type = "Defense & Kick"),
        Combo(name = "2-7-3-2-R-L", intensity = 6, type = "Hand & Kick"),

        Combo(name = "ROLL-4-3-2-S-2-3", intensity = 6, type = "Defense & Counter"),
        Combo(name = "DUCK-S-2-3-2-1", intensity = 6, type = "Defense & Counter"),
        Combo(name = "PIVOT-1-2-3-4-3", intensity = 6, type = "Defense & Footwork"),
        Combo(name = "SHADOW-1-2-3-2-R", intensity = 6, type = "Defense & Kick"),
        Combo(name = "C-S-ROLL-2-3-2", intensity = 6, type = "Defense & Counter"),
        Combo(name = "1-2-3-S-2-3-2", intensity = 6, type = "Defense & Counter"),
        Combo(name = "PULL-2-8-3-2-3B", intensity = 6, type = "Defense & Counter"),


        // ====================================================================
        // === INTENSITY 7: ELITE COMPLEXITY, RARE MOVES (15 Combos) ===
        // ====================================================================

        Combo(name = "1-2-3-4B-3-4-2", intensity = 7, type = "Hand Only"),
        Combo(name = "S-ROLL-DUCK-3-2-3-2", intensity = 7, type = "Defense & Counter"),
        Combo(name = "2-3-2-1B-2-3B-2", intensity = 7, type = "Hand Only"),
        Combo(name = "1-1B-2-3-4-3-2", intensity = 7, type = "Hand Only"),
        Combo(name = "DUCK-S-ROLL-4-3-2", intensity = 7, type = "Defense & Counter"),
        Combo(name = "5-2-3-6-2-5B-2", intensity = 7, type = "Hand Only"),
        Combo(name = "7-8-7-8-3-2-3", intensity = 7, type = "Hand Only"),
        Combo(name = "2-3-4-5-6-7-8", intensity = 7, type = "Hand Only"),

        Combo(name = "3-2-L-S-2-R", intensity = 7, type = "Defense & Kick"),
        Combo(name = "Knee-2-3-R-L", intensity = 7, type = "Hand & Kick"),
        Combo(name = "1-2-3-R-L-2", intensity = 7, type = "Hand & Kick"),
        Combo(name = "R-L-R-L-2-3", intensity = 7, type = "Kick Only"),
        Combo(name = "5B-2-3-R-L-6B", intensity = 7, type = "Hand & Kick"),

        Combo(name = "PULL-DUCK-2-3-2-4", intensity = 7, type = "Defense & Counter"),
        Combo(name = "PIVOT-S-ROLL-2-3", intensity = 7, type = "Defense & Footwork"),
        Combo(name = "1-2-S-3-PULL-2-3", intensity = 7, type = "Defense & Counter"),
        Combo(name = "4B-3-2-S-2-3-2", intensity = 7, type = "Defense & Counter"),
        Combo(name = "3-2-1-2-3-4-3", intensity = 7, type = "Hand Only"),
        Combo(name = "R-L-3B-2-3", intensity = 7, type = "Hand & Kick")
    )
}