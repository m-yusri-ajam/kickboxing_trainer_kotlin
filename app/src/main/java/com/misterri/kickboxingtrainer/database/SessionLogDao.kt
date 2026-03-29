package com.misterri.kickboxingtrainer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.misterri.kickboxingtrainer.model.SessionLog
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(log: SessionLog)

    @Query("SELECT * FROM session_log_table ORDER BY date DESC")
    fun getAllSessionLogs(): Flow<List<SessionLog>>

    @Query("DELETE FROM session_log_table")
    suspend fun deleteAllSessions()
}