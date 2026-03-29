package com.misterri.kickboxingtrainer.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.misterri.kickboxingtrainer.Combo

@Dao
interface ComboDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(combo: Combo)

    // Bulk insert function for the presets
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(combos: List<Combo>)

    @Query("SELECT * FROM combo_table ORDER BY name ASC")
    fun getAllCombos(): Flow<List<Combo>>

    @Query("SELECT * FROM combo_table WHERE intensity <= :maxIntensity AND (:comboType = 'ALL' OR :comboType = 'ANY' OR type = :comboType)")
    fun getFilteredCombos(maxIntensity: Int, comboType: String): Flow<List<Combo>>

    @Query("DELETE FROM combo_table")
    suspend fun deleteAll()

    @Update
    suspend fun update(combo: Combo)

    @Delete
    suspend fun delete(combo: Combo)

    @Query("DELETE FROM combo_table WHERE id = :comboId")
    suspend fun deleteById(comboId: Int)
}