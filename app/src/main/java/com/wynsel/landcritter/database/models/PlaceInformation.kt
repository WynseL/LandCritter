package com.wynsel.landcritter.database.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "PlaceInformation")
data class PlaceInformation(
        @ColumnInfo(name = "id") @PrimaryKey val id: String,
        @ColumnInfo(name = "address") val address: String,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "coordinates") val coordinates: Coordinates,
        @ColumnInfo(name = "bounds") val bounds: Bounds
)