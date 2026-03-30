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
package com.misterri.kickboxingtrainer

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.util.Log
import kotlinx.coroutines.delay

class ComboSoundPlayer(private val context: Context) {

    private val soundPool: SoundPool
    private val soundMap = mutableMapOf<String, Int>()

    // MAPPING: Links the combo tokens (Keys) to the R.raw IDs (Values).
    private val strikeToRawIdMap = mapOf(
        // PUNCH NUMBERS
        "1" to R.raw.number_1,
        "2" to R.raw.number_2,
        "3" to R.raw.number_3,
        "4" to R.raw.number_4,
        "5" to R.raw.number_5,
        "6" to R.raw.number_6,
        "7" to R.raw.number_7,
        "8" to R.raw.number_8,

        // BODY PUNCHES (Compound Tokens)
        "1B" to R.raw.number_1b,
        "2B" to R.raw.number_2b,
        "3B" to R.raw.number_3b,
        "4B" to R.raw.number_4b,
        "5B" to R.raw.number_5b,
        "6B" to R.raw.number_6b,
        "7B" to R.raw.number_7b,
        "8B" to R.raw.number_8b,

        // KICKS / KNEES (Specific Kicks)
        "LEAD KICK" to R.raw.kick_lead,
        "REAR KICK" to R.raw.kick_rear,
        "L" to R.raw.kick_lead,
        "R" to R.raw.kick_rear,
        "Knee" to R.raw.knee,
        "H_KICK" to R.raw.head_kick,

        // DEFENSE / MOVEMENT (Reverting to short tokens for S and C)
        "SLIP" to R.raw.slip, // 🔑 FIX: Reverted to S
        "S" to R.raw.slip,
        "C" to R.raw.check,
        "CHECK" to R.raw.check, // 🔑 FIX: Reverted to C
        "DUCK" to R.raw.duck,
        "PULL" to R.raw.pull,
        "ROLL" to R.raw.roll,
        "PIVOT" to R.raw.pivot,
        "SHADOW" to R.raw.shadow,
        "MOVE" to R.raw.move,

        // GENERIC / LEGACY / FULL-WORD TOKENS
        "B" to R.raw.body,
        "BODY" to R.raw.body,
        "KICK" to R.raw.kick_generic, // 🔑 FIX: Generic kick token/resource renamed
        "BLOCK" to R.raw.block,

        // TIME RELATED
        "TEN SECONDS REMAINING" to R.raw.ten_seconds_remaining,
        "HALFWAY THERE" to R.raw.halfway_there
    )

    // ... (rest of the class remains the same)
    init {
        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(5)
            .setAudioAttributes(attributes)
            .build()

        loadSounds()
    }

    private fun loadSounds() {
        strikeToRawIdMap.forEach { (token, resourceId) ->
            // Store all keys in uppercase for robust lookup
            val soundId = soundPool.load(context, resourceId, 1)
            soundMap[token.uppercase()] = soundId
        }
    }

    /**
     * Plays a sequence of sounds based on a combo string (e.g., "1-2-R").
     * Must be a suspend function to use delay() between individual strikes.
     */
    suspend fun playCombo(comboString: String) {
        // Split by typical separators: "-", " ", ","
        val moves = comboString.split("-", " ", ",")

        for ((index, moveRaw) in moves.withIndex()) {
            val move = moveRaw.trim().uppercase()
            if (move.isEmpty()) continue

            // 1. Play the move directly by looking up the full token
            playAudio(move)

            // 2. Add a delay after the move is complete, but before the next move in the combo.
            if (index < moves.size - 1) {
                delay(500L) // Delay between strikes for pace
            }
        }
    }

    /**
     * Helper function to handle audio playback.
     */
    fun playAudio(token: String) {
        val soundId = soundMap[token.uppercase()]
        if (soundId != null) {
            soundPool.play(soundId, 1f, 1f, 1, 0, 1f)
        } else {
            // This is crucial for debugging missing sounds!
            Log.e("ComboSoundPlayer", "No sound mapping found for token: $token")
        }
    }

    fun release() {
        soundPool.release()
    }
}