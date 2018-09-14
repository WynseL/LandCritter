package com.wynsel.landcritter.database.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wynsel.landcritter.database.models.Bounds

class BoundsConverter {
    
    @TypeConverter
    fun fromString(value: String): Bounds {
        val listType = object : TypeToken<Bounds>() {}.type
        return Gson().fromJson<Bounds>(value, listType)
    }

    @TypeConverter
    fun fromBounds(value : Bounds): String {
        val gson = Gson()
        return gson.toJson(value)
    }
}