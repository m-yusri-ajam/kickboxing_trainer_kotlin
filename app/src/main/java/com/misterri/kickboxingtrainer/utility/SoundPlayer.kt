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
import android.media.MediaPlayer
import com.misterri.kickboxingtrainer.R

class SoundPlayer(private val context: Context) {

    private var roundStartPlayer: MediaPlayer? = null
    private var roundEndPlayer: MediaPlayer? = null
    private var clapperPlayer: MediaPlayer? = null

    init {
        // Initialize all players once here
        roundStartPlayer = MediaPlayer.create(context, R.raw.round_start_bell)
        roundEndPlayer = MediaPlayer.create(context, R.raw.round_end_bell)
        clapperPlayer = MediaPlayer.create(context, R.raw.clapper_click)
    }

    fun playRoundStartBell() {
        roundStartPlayer?.seekTo(0)
        roundStartPlayer?.start()
    }

    fun playRoundEndBell() {
        roundEndPlayer?.seekTo(0)
        roundEndPlayer?.start()
    }

    fun playClapperWarning() {
        clapperPlayer?.seekTo(0)
        clapperPlayer?.start()
    }

    // Only one release function
    fun release() {
        roundStartPlayer?.release()
        roundEndPlayer?.release()
        clapperPlayer?.release()
        roundStartPlayer = null
        roundEndPlayer = null
        clapperPlayer = null
    }
}