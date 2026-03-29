package com.misterri.kickboxingtrainer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.misterri.kickboxingtrainer.database.SessionLogDao
import com.misterri.kickboxingtrainer.model.SessionLog
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SessionLogViewModel(private val logDao: SessionLogDao) : ViewModel() {

    // Retrieve all sessions from the DAO as a Flow and transform them for display
    val sessionLogs: StateFlow<List<DisplaySessionLog>> =
        logDao.getAllSessionLogs()
            .map { list ->
                // Map the database entities (SessionLog) to display objects
                list.map { session ->
                    DisplaySessionLog(
                        id = session.id,
                        date = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(session.date)),
                        duration = formatTime(session.durationMinutes * 60),
                        mode = "NORMAL", // Assuming normal since mode is not in SessionLog yet
                        details = "${session.roundsCompleted} Rds | ${session.comboMaxIntensity} Int"
                    )
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    // Helper function to format seconds into M:SS format
    private fun formatTime(totalSeconds: Int): String {
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%d:%02d", minutes, seconds)
    }

    // Function to delete all sessions (useful for maintenance/testing)
    fun deleteAllSessions() {
        viewModelScope.launch {
            logDao.deleteAllSessions()
        }
    }
}

// 5.2. Data Class for UI Display
data class DisplaySessionLog(
    val id: Int,
    val date: String,
    val duration: String,
    val mode: String,
    val details: String
)

// 5.3. Custom ViewModel Factory for injection
class SessionLogViewModelFactory(
    private val logDao: SessionLogDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SessionLogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SessionLogViewModel(logDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}