package com.mbglobal.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mbglobal.local.database.tables.EventTable

@Database(entities = [EventTable::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract val localDatabaseDao: LocalDatabaseDao

//    companion object {
//        @Volatile
//        private var Instance: LocalDatabase? = null
//
//        fun getInstance(context: Context): LocalDatabase {
//            synchronized(this) {
//                var instance = Instance
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context,
//                        LocalDatabase::class.java,
//                        "local_database"
//                    ).fallbackToDestructiveMigration().build()
//                }
//                return instance
//            }
//        }
//    }
}