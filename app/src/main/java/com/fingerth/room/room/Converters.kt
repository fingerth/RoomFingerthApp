package com.fingerth.room.room

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestamp(l: Long): Date = Date(l)

    @TypeConverter
    fun dateToTimestamp(date: Date) = date.time

//    @TypeConverter
//    fun fromLocation(location: Location): String {
//        return JSONObject().apply {
//            put("lng", location.lng)
//            put("lat", location.lat)
//        }.toString()
//    }
//
//    @TypeConverter
//    fun toLocation(location: String): Location {
//        val json = JSONObject(location)
//        return Location(json.getString("lng"), json.getString("lat"))
//    }
}