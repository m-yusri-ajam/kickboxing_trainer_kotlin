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
package com.misterri.kickboxingtrainer.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.misterri.kickboxingtrainer.viewmodel.TrainerViewModel
import com.misterri.kickboxingtrainer.data.SessionConfig
import com.misterri.kickboxingtrainer.database.KickboxingDatabase
import com.misterri.kickboxingtrainer.ComboSoundPlayer
// REMOVE: import kotlinx.coroutines.CoroutineScope
// REMOVE: import kotlinx.coroutines.Dispatchers

class TrainerViewModelFactory(
    private val application: Application,
    private val config: SessionConfig
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrainerViewModel::class.java)) {

            val comboSoundPlayer = ComboSoundPlayer(application)
            val soundPlayer = com.misterri.kickboxingtrainer.utility.SoundPlayer(application)

            // 🔑 FIX: Removed the transient databaseScope creation and passed application only.
            val comboDao = KickboxingDatabase.getDatabase(application).comboDao()

            return TrainerViewModel(
                config = config,
                comboDao = comboDao,
                comboSoundPlayer = comboSoundPlayer,
                soundPlayer = soundPlayer
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}