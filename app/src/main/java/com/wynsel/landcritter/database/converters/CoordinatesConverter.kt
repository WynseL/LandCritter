package com.wynsel.landcritter.database.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wynsel.landcritter.database.models.Coordinates

class CoordinatesConverter {

    @TypeConverter
    fun fromString(value: String): Coordinates {
        val listType = object : TypeToken<Coordinates>() {}.type
        return Gson().fromJson<Coordinates>(value, listType)
    }

    @TypeConverter
    fun fromCoordinates(value : Coordinates): String {
        val gson = Gson()
        return gson.toJson(value)
    }
}