package com.wynsel.landcritter.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import com.wynsel.landcritter.database.models.PlaceInformation

@Dao
interface RoomService {

    @Insert
    fun addPlacesInformation(placeInformation: PlaceInformation) : Long

}