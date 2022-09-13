package com.sergnfitness.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuDayList(
    val listMenuDay: List<MenuDay>
    ):Parcelable

@Parcelize
data class MenuDay(
    var id: Int?,
    var id_note:Int,
    var user: String,
    var menu: List<String>,
    var data: String,
    var weight: Double,
):Parcelable
