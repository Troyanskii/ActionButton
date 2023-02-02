package com.troy.actionbutton.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.troy.actionbutton.repository.converter.IntConverter

@Entity(tableName = "action")
data class ActionEntity(
    @PrimaryKey
    val type: String = "",
    val enabled: Boolean = false,
    val priority: Int = 0,
    @SerializedName("valid_days")
    @TypeConverters(IntConverter::class)
    val validDays: List<Int> = mutableListOf(),
    @SerializedName("cool_down")
    val coolDown: Int = Int.MAX_VALUE,
    val lastTimeActivated: Long = 0,
)
