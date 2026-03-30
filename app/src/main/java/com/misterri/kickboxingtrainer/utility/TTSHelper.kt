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

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

class TTSHelper(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech = TextToSpeech(context, this)
    private var isReady = false

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Handle language data missing or not supported
                // Log.e("TTS", "Language not supported")
            } else {
                isReady = true
            }
        } else {
            // Log.e("TTS", "Initialization failed")
        }
    }
    // Breathing Advice to be spoken during rest, as requested
    private val breathingAdvice = "Deep breath in through the nose, out through the mouth. Control your heart rate."

    // Initialization block: Called when the class is first created
    init {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                // Set language to US English (or your preferred accent)
                val result = tts?.setLanguage(Locale.US)

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Handle case where language data is missing
                    // Log.e("TTS", "Language not supported")
                }
            } else {
                // Log.e("TTS", "Initialization failed")
            }
        }
    }

    /**
     * Speaks the given text using the TTS engine.
     */
    fun speak(text: String, queueMode: Int = TextToSpeech.QUEUE_FLUSH) {
        tts?.speak(text, queueMode, null, null)
    }

    /**
     * Speaks the breathing advice after the round end bell.
     */
    fun speakBreathingAdvice() {
        // Use QUEUE_ADD to speak this after any previous TTS queue items (like the round end bell)
        speak(breathingAdvice, TextToSpeech.QUEUE_ADD)
    }

    /**
     * Cleans up the TTS engine resources when the activity is destroyed.
     */
    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }
}