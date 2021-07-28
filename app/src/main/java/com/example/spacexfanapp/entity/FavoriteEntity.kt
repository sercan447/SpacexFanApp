package com.example.spacexfanapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteEntity")
data class FavoriteEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    public var Id:Int = 0,

    @ColumnInfo(name = "uid")
    public var uid:String = "",

    @ColumnInfo(name = "name")
    public var name:String = "",

    @ColumnInfo(name = "Image")
    public var Image:String = ""

){
    constructor() :  this(0,"","")
}
