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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.misterri.kickboxingtrainer.database.ComboDao
import com.misterri.kickboxingtrainer.ComboSoundPlayer
import com.misterri.kickboxingtrainer.utility.SoundPlayer
import com.misterri.kickboxingtrainer.data.ComboSelector
import com.misterri.kickboxingtrainer.data.SessionConfig
import com.misterri.kickboxingtrainer.data.WorkoutMode
import com.misterri.kickboxingtrainer.utility.ComboFrequency
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class TrainerViewModel(
    val config: SessionConfig,
    private val comboDao: ComboDao,
    private val comboSoundPlayer: ComboSoundPlayer,
    private val soundPlayer: SoundPlayer
) : ViewModel() {

    // --- CONSTANTS ---
    private val INITIAL_COMBO_START_DELAY_MS = 2000L
    val SHARK_TANK_DURATION_MS = 60 * 1000L

    // --- WORKOUT STATE ---
    private val _timerState = MutableStateFlow(0L)
    val timerState: StateFlow<Long> = _timerState

    private val _workoutStatus = MutableStateFlow("PREP")
    val workoutStatus: StateFlow<String> = _workoutStatus

    private val _currentRound = MutableStateFlow(0)
    val currentRound: StateFlow<Int> = _currentRound

    private val _lastCalledCombo = MutableStateFlow("")
    val lastCalledCombo: StateFlow<String> = _lastCalledCombo

    private val _navigationEvent = MutableSharedFlow<Map<String, Any>>()
    val navigationEvent: SharedFlow<Map<String, Any>> = _navigationEvent

    // 🔑 ADDED: Property to track total elapsed active time in milliseconds
    private var totalActiveTimeMs: Long = 0L

    // --- PAUSE/RESUME STATE ---
    private var _isPaused = false
    private var _timeAtPause = 0L
    private var _statusBeforePause = "PREP"

    // --- TIMERS ---
    private var countdownJob: Job? = null
    private var comboCalloutJob: Job? = null
    private var comboSelector: ComboSelector? = null

    val roundDurationMs = config.roundDurationSeconds * 1000L
    val restDurationMs = config.restDurationSeconds * 1000L
    val prepDurationMs = 10 * 1000L

    // --- INITIALIZATION ---
    init {
        viewModelScope.launch {
            try {
                // Fetch combos from database asynchronously
                var combos = comboDao.getFilteredCombos(config.maxIntensity, config.comboType).first()

                // Fallback: If no combos found (e.g. invalid filter), load ALL combos
                if (combos.isEmpty()) {
                    val allCombos = comboDao.getAllCombos().first()
                    // Filter fallback combos by intensity
                    combos = allCombos.filter { it.intensity <= config.maxIntensity }
                }

                if (combos.isNotEmpty()) {
                    comboSelector = ComboSelector(combos)
                    // START SESSION ONLY AFTER COMBOSELECTOR IS READY
                    startCountdown(prepDurationMs, "PREP")
                } else {
                    _workoutStatus.value = "ERROR"
                }
            } catch (e: Exception) {
                _workoutStatus.value = "ERROR"
                e.printStackTrace()
            }
        }
    }

    // --- CORE LOGIC ---

    fun startCountdown(durationMs: Long, status: String, nextPhase: () -> Unit = this::handlePhaseEnd) {
        countdownJob?.cancel()
        _workoutStatus.value = status

        countdownJob = viewModelScope.launch {
            var remainingTime = durationMs
            val halfwayPoint = durationMs / 2
            val tenSecondsMs = 10 * 1000L

            // Initial sound logic
            if (status == "SHARKTANK" || status == "ROUND" || status == "PREP" || status == "REST") {
                soundPlayer.playRoundStartBell()
            }

            while (isActive && remainingTime >= 0L) {
                _timerState.value = remainingTime

                // Cues
                if (status == "ROUND" && remainingTime == halfwayPoint) {
                    comboSoundPlayer.playAudio("HALFWAY THERE")
                }
                if (status == "REST" && remainingTime == tenSecondsMs) {
                    comboSoundPlayer.playAudio("TEN SECONDS REMAINING")
                }

                delay(1000L)
                remainingTime -= 1000L
            }

            if (isActive) {
                // Countdown finished
                if (status == "ROUND" || status == "SHARKTANK") {
                    soundPlayer.playRoundEndBell()
                } else if (status == "PREP" || status == "REST") {
                    // Start bell played in next phase start
                }
                nextPhase()
            }
        }
    }

    private fun handlePhaseEnd() {
        // 🔑 MODIFIED: Accumulate total active time before transitioning
        if (_workoutStatus.value == "ROUND") {
            totalActiveTimeMs += roundDurationMs
        } else if (_workoutStatus.value == "SHARKTANK") {
            totalActiveTimeMs += SHARK_TANK_DURATION_MS
        }

        when (config.mode) {
            // NORMAL MODE FLOW
            WorkoutMode.NORMAL -> {
                when (_workoutStatus.value) {
                    "PREP" -> startRound()
                    "ROUND" -> startRest()
                    "REST" -> if (_currentRound.value < config.totalRounds) startRound() else endWorkout()
                    else -> {}
                }
            }
            // SHARK TANK MODE FLOW
            WorkoutMode.SHARKTANK -> {
                when (_workoutStatus.value) {
                    "PREP" -> startSharkTank()
                    "SHARKTANK" -> startRest()
                    "REST" -> if (_currentRound.value < config.totalRounds) startSharkTank() else endWorkout()
                    else -> {}
                }
            }
        }
    }

    // --- NORMAL MODE ACTIONS ---

    private fun startRound() {
        _currentRound.value++
        startCountdown(roundDurationMs, "ROUND")
        startComboCallouts()
    }

    private fun startComboCallouts() {
        comboCalloutJob?.cancel()
        comboCalloutJob = viewModelScope.launch {
            delay(INITIAL_COMBO_START_DELAY_MS)
            while (isActive) {
                // FIX: Use correct method from ComboFrequency (returns MS directly)
                val delayMs = ComboFrequency.getDelayMs(config.maxIntensity)
                delay(delayMs)

                if (isActive && _workoutStatus.value == "ROUND") {
                    val combo = comboSelector?.getNextCombo()
                    if (combo != null) {
                        _lastCalledCombo.value = combo.name
                        comboSoundPlayer.playCombo(combo.name)
                    }
                }
            }
        }
    }

    // --- SHARK TANK MODE ACTIONS ---

    private fun startSharkTank() {
        _currentRound.value++
        startCountdown(SHARK_TANK_DURATION_MS, "SHARKTANK")
        startSharkTankCallouts()
    }

    private fun startSharkTankCallouts() {
        comboCalloutJob?.cancel()
        comboCalloutJob = viewModelScope.launch {
            val rapidDelayMs = 500L

            while (isActive) {
                val combo = comboSelector?.getNextCombo()
                if (combo != null) {
                    _lastCalledCombo.value = combo.name
                    comboSoundPlayer.playCombo(combo.name)
                }
                delay(rapidDelayMs)
            }
        }
    }

    private fun startRest() {
        comboCalloutJob?.cancel()
        startCountdown(restDurationMs, "REST")
    }

    fun togglePause() {
        if (_isPaused) {
            // Resume
            val remainingDuration = _timeAtPause
            val status = _statusBeforePause

            if (status == "ROUND") {
                startComboCallouts()
            } else if (status == "SHARKTANK") {
                startSharkTankCallouts()
            }
            startCountdown(remainingDuration, status)
            _isPaused = false
        } else {
            // Pause
            countdownJob?.cancel()
            comboCalloutJob?.cancel()

            _timeAtPause = _timerState.value
            _statusBeforePause = _workoutStatus.value

            _workoutStatus.value = "PAUSED"
            _isPaused = true
        }
    }

    fun endWorkout() {
        countdownJob?.cancel()
        comboCalloutJob?.cancel()
        _workoutStatus.value = "FINISHED"

        viewModelScope.launch {
            // 🔑 MODIFIED: Emit total active time along with the navigation action
            _navigationEvent.emit(mapOf(
                "action" to "NAVIGATE_TO_LOG",
                "total_active_time_ms" to totalActiveTimeMs
            ))
        }
    }

    override fun onCleared() {
        super.onCleared()
        countdownJob?.cancel()
        comboCalloutJob?.cancel()
        comboSoundPlayer.release()
        soundPlayer.release()
    }
}