package com.example.user.myfirstapplication

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class Book(
//        @PrimaryKey open var id : String = UUID.randomUUID().toString(),
        @PrimaryKey open var id : Int = -1,
        @Required open var name : String = "",
        open var price : Int = 0
) : RealmObject()
