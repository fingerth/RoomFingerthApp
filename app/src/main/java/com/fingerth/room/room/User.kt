package com.fingerth.room.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class User {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    var age: Int = 0
    var firstName: String? = null
    var lastName: String? = null
    var name: String? = null

    constructor(age: Int, firstName: String, lastName: String, name: String) {
        this.age = age
        this.firstName = firstName
        this.lastName = lastName
        this.name = name
    }

    //    @Ignore
//    var picture: Bitmap? = null
//
//    @Embedded
//    var address: Address? = null


}

class Address {
    var street: String? = null
    var state: String? = null
    var city: String? = null

    @ColumnInfo(name = "post_code")
    var postCode = 0
}