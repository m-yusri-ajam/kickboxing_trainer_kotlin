package com.misterri.kickboxingtrainer // Your correct package name

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "combo_table")
data class Combo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val intensity: Int,
    val type: String
)