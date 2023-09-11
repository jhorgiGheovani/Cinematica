package com.jhorgi.cinematica.core.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jhorgi.cinematica.core.domain.model.GenresItem

class ConvertersGenre {
    @TypeConverter
    fun fromList(list: List<GenresItem>?): String? {
        return list?.let { Gson().toJson(list) }
    }

    @TypeConverter
    fun toList(json: String?): List<GenresItem>? {
        return json?.let { Gson().fromJson(json, object : TypeToken<List<GenresItem>>() {}.type) }
    }
}