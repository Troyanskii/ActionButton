package com.troy.actionbutton.repository.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class IntConverter: BaseListTypeConverter() {

    @TypeConverter
    fun toIntsList(intsString: String?): List<Int> {
        if(intsString == null || intsString.isEmpty()) {
            return mutableListOf()
        }
        val type: Type = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(intsString, type)
    }
}