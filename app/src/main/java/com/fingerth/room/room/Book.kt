package com.fingerth.room.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

import androidx.room.PrimaryKey


@Entity
class Book {
    @PrimaryKey
    var id = 0

    var title: String? = null

    @ColumnInfo(name = "user_id")
    var userId = 0
}