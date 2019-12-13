package com.mbglobal.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mbglobal.local.database.tables.EventTable

@Database(entities = [EventTable::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract val localDatabaseDao: LocalDatabaseDao
}