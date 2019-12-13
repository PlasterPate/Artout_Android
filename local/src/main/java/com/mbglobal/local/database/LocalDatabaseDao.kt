package com.mbglobal.local.database

import androidx.room.*
import com.mbglobal.local.database.tables.EventTable
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface LocalDatabaseDao {

    @Insert
    fun insert(event: EventTable): Completable

    @Update
    fun update(event: EventTable): Completable

    @Query("SELECT * FROM events_table WHERE id = :id")
    fun get(id: Int): Single<EventTable>

    @Query("SELECT * FROM events_table")
    fun getAllEvents(): Single<List<EventTable>>
}