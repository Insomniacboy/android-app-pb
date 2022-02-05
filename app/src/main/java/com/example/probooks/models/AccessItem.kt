package com.example.probooks.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class AccessItem (
    var id: Int = 0,
    var accesstitle: String = "",
    var accessauthor: String = "",
    var accessimage: String = "",
    var access_url: String = "",
) : Parcelable