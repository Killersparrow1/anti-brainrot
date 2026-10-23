package com.brainrot.android.feature_stats.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: String?): List<String>? {
        if (value == null) return null
        val type: Type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun toStringList(list: List<String>?): String? {
        if (list == null) return null
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringMap(value: String?): Map<String, Int>? {
        if (value == null) return null
        val type: Type = object : TypeToken<Map<String, Int>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun toStringMap(map: Map<String, Int>?): String? {
        if (map == null) return null
        return gson.toJson(map)
    }
}