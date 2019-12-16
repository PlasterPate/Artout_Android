package com.mbglobal.local.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events_table")
data class EventTable(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "image_path")
    val imagePath: String?,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "event_owner")
    val eventOwner: Int,

    @ColumnInfo(name = "location_lat")
    val locationLat: Double,

    @ColumnInfo(name = "location_long")
    val locationLong: Double,

    @ColumnInfo(name = "start_date")
    val startDate: String,

    @ColumnInfo(name = "end_date")
    val endDate: String
)