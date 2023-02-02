package com.troy.actionbutton.repository.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.troy.actionbutton.repository.entity.ActionEntity

interface ActionDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stylist: List<ActionEntity>)

    @Query("SELECT * FROM `action`")
    fun getAllActions(): List<ActionEntity>
}