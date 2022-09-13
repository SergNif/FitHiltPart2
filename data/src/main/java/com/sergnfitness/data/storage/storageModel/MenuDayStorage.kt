package com.sergnfitness.data.storage.storageModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuDayStorage(
    var id_note:Int,
    var user: String,
    var menu: List<String>,
    var data: String,
    var weight: Double,
):Parcelable
