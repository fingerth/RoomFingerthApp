package com.fingerth.room.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Loan {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "book_id")
    var bookId = 0

    var title: String? = null

    var name: String? = null

    @ColumnInfo(name = "user_id")
    var userId = 0
}