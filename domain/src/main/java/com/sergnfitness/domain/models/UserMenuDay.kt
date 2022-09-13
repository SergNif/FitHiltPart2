package com.sergnfitness.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
@Parcelize
data class UserMenuDay(
    var id_tab: Int? = null,
    var id: Int? = null,
    var age: Int? = null,
    var date: String = LocalDateTime.now().toString().split("T")[0],
    var time: String = LocalDateTime.now().toString().split("T")[0],
    var desired_weight: Double? = null,
    var height: Int? = null,
    var weight: Double? = null,
    var fitness_id: Int? = null,
    var header: String? = null,
    var menu: String? = null,
):Parcelable
