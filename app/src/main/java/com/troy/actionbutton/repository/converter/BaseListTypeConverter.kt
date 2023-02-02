package com.troy.actionbutton.repository.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class BaseListTypeConverter {

    @TypeConverter
    fun fromListToString(list: List<*>): String {
        val type = object: TypeToken<List<*>>() {}.type
        return Gson().toJson(list, type)
    }
}