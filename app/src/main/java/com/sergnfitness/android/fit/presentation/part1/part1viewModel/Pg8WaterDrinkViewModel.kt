package com.sergnfitness.android.fit.presentation.part1.part1viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.user.User
import javax.inject.Inject

class Pg8WaterDrinkViewModel @Inject constructor() : ViewModel() {

    val TAG = "Pg8WaterDrinkViewModel"

    lateinit var userClass: User
    lateinit var dataUser: DataUser

    private var _page8_button_water_without_gas = MutableLiveData<Boolean>(false)
    val live_page8_button_water_without_gas: LiveData<Boolean> = _page8_button_water_without_gas

    private var _page8_button_water_shugar = MutableLiveData<Boolean>(false)
    val live_page8_button_water_shugar: LiveData<Boolean> = _page8_button_water_shugar

    private var _page8_button_more_cofee = MutableLiveData<Boolean>(false)
    val live_page8_button_more_cofee: LiveData<Boolean> = _page8_button_more_cofee

    private var _page8_button_only_tea = MutableLiveData<Boolean>(false)
    val live_page8_button_only_tea: LiveData<Boolean> = _page8_button_only_tea

    private var _page4_button_every_day_fitness = MutableLiveData<Boolean>(false)
    val live_page4_button_every_day_fitness: LiveData<Boolean> = _page4_button_every_day_fitness

//    page8_button_water_without_gas
//    page8_button_water_shugar
//    page8_button_more_cofee
//    page8_button_only_tea

    fun initLive() {
        _page8_button_water_without_gas.value = dataUser.waterWithoutGas
        _page8_button_water_shugar.value = dataUser.waterSugarGas
        _page8_button_more_cofee.value = dataUser.coffee
        _page8_button_only_tea.value = dataUser.tea
    }

    fun changepage8ButtonWaterWithoutGas(bool: Boolean) {
        _page8_button_water_without_gas.value = bool
        dataUser.waterWithoutGas = true

//        dataUser.waterWithoutGas = false
        dataUser.waterSugarGas = false
        dataUser.coffee = false
        dataUser.tea = false
    }

    fun changepage8ButtonWaterShugar(bool: Boolean) {
        _page8_button_water_shugar.value = bool
        dataUser.waterSugarGas = true

        dataUser.waterWithoutGas = false
        dataUser.coffee = false
        dataUser.tea = false
    }

    fun changepage8ButtonMoreCofee(bool: Boolean) {
        _page8_button_more_cofee.value = bool
        dataUser.coffee = true


        dataUser.waterWithoutGas = false
        dataUser.waterSugarGas = false
        dataUser.tea = false
    }

    fun changepage8ButtonOnlyTea(bool: Boolean) {
        _page8_button_only_tea.value = bool
        dataUser.tea = true

        dataUser.waterWithoutGas = false
        dataUser.waterSugarGas = false
        dataUser.coffee = false
    }

}