package com.example.probooks.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventItem(
    var id: Int = 0,
    var title: String = "",
    var author: String = "",
    var image: String = "",
    var url: String ="",
) : Parcelable