package com.wynsel.landcritter.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.wynsel.landcritter.database.converters.BoundsConverter
import com.wynsel.landcritter.database.converters.CoordinatesConverter
import com.wynsel.landcritter.database.models.PlaceInformation

@Database(entities = [PlaceInformation::class],
        exportSchema = false,
        version = 1)
@TypeConverters(CoordinatesConverter::class, BoundsConverter::class)
abstract class CritterDatabase: RoomDatabase() {

    companion object {

        private var INSTANCE: CritterDatabase? = null

        fun getInstance(context: Context): RoomDatabase? {
            if (INSTANCE == null) {
                synchronized(CritterDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CritterDatabase::class.java, "critter.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    abstract fun roomService(): RoomService

}