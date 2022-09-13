package com.sergnfitness.android.fit.presentation.part1.part1viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Pg9TypicalDayViewModel @Inject constructor(): ViewModel() {
    val TAG = "Pg9TypicalDayViewModel"

    lateinit var userClass: User
    lateinit var dataUser: DataUser

    private var _page9_work_office = MutableLiveData<Boolean>(false)
    val live_page9_work_office: LiveData<Boolean> = _page9_work_office

    private var _page9_in_travel = MutableLiveData<Boolean>(false)
    val live_page9_in_travel: LiveData<Boolean> = _page9_in_travel

    private var _page9_day_foot = MutableLiveData<Boolean>(false)
    val live_page9_day_foot: LiveData<Boolean> = _page9_day_foot

    private var _page9_in_house = MutableLiveData<Boolean>(false)
    val live_page9_in_house: LiveData<Boolean> = _page9_in_house

    fun initLive(){
        _page9_work_office.value = dataUser.workOffice
        _page9_in_travel.value = dataUser.regularTraffic
        _page9_day_foot.value = dataUser.fastWalkOnFoot
        _page9_in_house.value = dataUser.InHome
    }

    fun changepage9WorkOffice(){
        _page9_work_office.value = true


        _page9_in_travel.value = false
        _page9_day_foot.value = false
        _page9_in_house.value = false

        dataUser.workOffice = true
        dataUser.regularTraffic = false
        dataUser.fastWalkOnFoot = false
        dataUser.InHome = false
    }
    fun changepage9InTravel(){
        _page9_in_travel.value = true

        _page9_work_office.value = false
        _page9_day_foot.value = false
        _page9_in_house.value = false

        dataUser.workOffice = false
        dataUser.regularTraffic = true
        dataUser.fastWalkOnFoot = false
        dataUser.InHome = false
    }
    fun changepage9DayFoot(){
        _page9_day_foot.value = true

        _page9_work_office.value = false
        _page9_in_travel.value = false
        _page9_in_house.value = false

        dataUser.workOffice = false
        dataUser.regularTraffic = false
        dataUser.fastWalkOnFoot = true
        dataUser.InHome = false
    }
    fun changepage9InHouse(){
        _page9_in_house.value = true

        _page9_work_office.value = false
        _page9_in_travel.value = false
        _page9_day_foot.value = false

        dataUser.workOffice = false
        dataUser.regularTraffic = false
        dataUser.fastWalkOnFoot = false
        dataUser.InHome = true
    }

}