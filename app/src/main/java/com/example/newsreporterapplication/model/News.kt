package com.example.newsreporterapplication.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "news")
@Parcelize
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val newsTitle: String,
    val newsDescription: String,
    val newsImageUrl: String
): Parcelable
