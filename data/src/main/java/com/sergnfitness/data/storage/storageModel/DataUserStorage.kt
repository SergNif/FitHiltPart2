package com.sergnfitness.data.storage.storageModel

import android.os.Parcelable
import com.sergnfitness.domain.models.user.DataUser
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class DataUserStorage(
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

fun DataUserStorage.toDataUser(): DataUser {
    return DataUser(
        id = id,
        woman = woman,
        man = man,

        age = age,
        height = height,
        weight = weight,
        desired_weight = desired_weight,

        minimalPhysicalActive = minimalPhysicalActive,
        fastWalkOnFoot = fastWalkOnFoot,
        examine1_2TimesWeek = examine1_2TimesWeek,
        examine3_5TimesWeek = examine3_5TimesWeek,
        everyDayFitness = everyDayFitness,
        date = date,

        chicken = chicken,
        tyrkey = tyrkey,
        pork = pork,
        meat = meat,
        fish = fish,
        seaFood = seaFood,
        withoutMeat = withoutMeat,
        withoutFish = withoutFish,

        zucchini = zucchini,
        tomato = tomato,
        eggplant = eggplant,
        cauliflower = cauliflower,
        cucumbers = cucumbers,
        broccoli = broccoli,
        mushrooms = mushrooms,
        avocado = avocado,

        egg = egg,
        cheese = cheese,
        nuts = nuts,
        cottage = cottage,
        kefir = kefir,
        yogurt = yogurt,

        waterWithoutGas = waterWithoutGas,
        waterSugarGas = waterSugarGas,
        coffee = coffee,
        tea = tea,

        workOffice = workOffice,
        regularTraffic = regularTraffic,
        OnFoot = OnFoot,
        InHome = InHome,

        fastFood = fastFood,
        laterNight = laterNight,
        fastSugar = fastSugar,
        Nothing = Nothing,

        fitness_id = fitness_id,

        )
}

fun DataUserStorage.fromSharedPrefs(list: MutableList<String>): DataUserStorage {
    var us = this
    var count = 0
    this::class.java.declaredFields.forEach() { member ->
        var type = member.name.javaClass.name
        when (type) {
            "java.lang.String" -> us.javaClass.getDeclaredField(member.name).let { list.get(count) }
            "java.lang.Boolean" -> us.javaClass.getDeclaredField(member.name)
                .let { list.get(count).toBoolean() }
            "java.lang.Int" -> us.javaClass.getDeclaredField(member.name)
                .let { list.get(count).toInt() }
            else -> {us.javaClass.getDeclaredField(member.name)
                .let { "" }}
        }
        count = count + 1
    }
    return us
}

