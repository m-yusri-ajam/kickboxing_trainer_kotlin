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
package com.misterri.kickboxingtrainer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.misterri.kickboxingtrainer.Combo
import com.misterri.kickboxingtrainer.database.ComboDao
import com.misterri.kickboxingtrainer.model.SessionLog // 🔑 Assumes SessionLog is your new entity
import com.misterri.kickboxingtrainer.database.SessionLogDao // 🔑 ADDED: Import the new DAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import com.misterri.kickboxingtrainer.data.ComboPresets

// 🔑 DATABASE VERSION BUMPED to 5.
@Database(
    entities = [Combo::class, SessionLog::class], // 🔑 MODIFIED: Includes SessionLog entity
    version = 5,
    exportSchema = false
)
abstract class KickboxingDatabase : RoomDatabase() {

    abstract fun comboDao(): ComboDao
    abstract fun sessionLogDao(): SessionLogDao // 🔑 ADDED: Accessor for the new DAO

    companion object {
        @Volatile
        private var INSTANCE: KickboxingDatabase? = null

        private val ApplicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

        fun getDatabase(context: Context): KickboxingDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = KickboxingDatabase::class.java,
                    name = "kickboxing_database"
                )
                    .addCallback(KickboxingDatabaseCallback(ApplicationScope))
                    // Since we are changing the version and adding a new table,
                    // we add this fallback to prevent crashes if a migration path is not defined.
                    .fallbackToDestructiveMigration() // 🔑 ADDED: Allows safe version upgrade for development
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class KickboxingDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { database ->
                scope.launch {
                    val comboDao = database.comboDao()
                    // Pre-populate Combos
                    comboDao.insertAll(ComboPresets.ALL_COMBOS)

                    // You could optionally insert a fake log entry here for initial testing:
                    // val logDao = database.sessionLogDao()
                    // logDao.insertSession(SessionLog(mode = "NORMAL", ...))
                }
            }
        }
    }
}