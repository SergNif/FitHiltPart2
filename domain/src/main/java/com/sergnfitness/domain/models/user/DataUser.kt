package com.sergnfitness.domain.models.user



import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime


@Parcelize
data class DataUser(
    var id: Int? = null,
    var woman: Boolean = true,
    var man: Boolean = false,

    var age: Int = 18,
    var height: Int = 140,
    var weight: String = "40.0",
    var desired_weight: String = "40.0",

    var minimalPhysicalActive: Boolean = true,
    var fastWalkOnFoot: Boolean = false,
    var examine1_2TimesWeek: Boolean = false,
    var examine3_5TimesWeek: Boolean = false,
    var everyDayFitness: Boolean = false,
    var date: String = LocalDateTime.now().toString().split("T")[0],

    var chicken: Boolean = false,
    var tyrkey: Boolean = false,
    var pork: Boolean = false,
    var meat: Boolean = false,
    var fish: Boolean = false,
    var seaFood: Boolean = false,
    var withoutMeat: Boolean = false,
    var withoutFish: Boolean = false,

    var zucchini: Boolean = false,
    var tomato: Boolean = false,
    var eggplant: Boolean = false,
    var cauliflower: Boolean = false,
    var cucumbers: Boolean = false,
    var broccoli: Boolean = false,
    var mushrooms: Boolean = false,
    var avocado: Boolean = false,

    var egg: Boolean = false,
    var cheese: Boolean = false,
    var nuts: Boolean = false,
    var cottage: Boolean = false,
    var kefir: Boolean = false,
    var yogurt: Boolean = false,

    var waterWithoutGas: Boolean = true,
    var waterSugarGas: Boolean = false,
    var coffee: Boolean = false,
    var tea: Boolean = false,

    var workOffice: Boolean = true,
    var regularTraffic: Boolean = false,
    var OnFoot: Boolean = false,
    var InHome: Boolean = false,

    var fastFood: Boolean = true,
    var laterNight: Boolean = false,
    var fastSugar: Boolean = false,
    var Nothing: Boolean = false,

    var fitness_id: Int? = null,
):Parcelable

//{
//    init {
//        if(woman) {  man=false}
//        if(man) { woman=false}
//    }
//}