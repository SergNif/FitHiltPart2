package com.sergnfitness.domain.models.user

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (

    var id: Int? = null,
    var fullName: String? = null,
    var email: String? = null,
    var password: String? = null,
    var fitness_id: Int? = null,
):Parcelable



