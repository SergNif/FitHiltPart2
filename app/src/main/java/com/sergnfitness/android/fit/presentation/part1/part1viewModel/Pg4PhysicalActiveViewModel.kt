package com.sergnfitness.android.fit.presentation.part1.part1viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Pg4PhysicalActiveViewModel @Inject constructor(): ViewModel() {


    val TAG = "Pg4PhysicalActiveViewModel"

    lateinit var userClass: User
    lateinit var dataUser: DataUser

    private var _page4_button_min_activ = MutableLiveData<Boolean>(false)
    val live_page4_button_min_activ: LiveData<Boolean> = _page4_button_min_activ

    private var _page4_button_fast_walk = MutableLiveData<Boolean>(false)
    val live_page4_button_fast_walk: LiveData<Boolean> = _page4_button_fast_walk

    private var _page4_button_1_2_per_week = MutableLiveData<Boolean>(false)
    val live_page4_button_1_2_per_week: LiveData<Boolean> = _page4_button_1_2_per_week

    private var _page4_button_3_5_per_week = MutableLiveData<Boolean>(false)
    val live_page4_button_3_5_per_week: LiveData<Boolean> = _page4_button_3_5_per_week

    private var _page4_button_every_day_fitness = MutableLiveData<Boolean>(false)
    val live_page4_button_every_day_fitness: LiveData<Boolean> = _page4_button_every_day_fitness

    fun initLive() {
        _page4_button_min_activ.value = dataUser.minimalPhysicalActive
        _page4_button_fast_walk.value = dataUser.fastWalkOnFoot
        _page4_button_1_2_per_week.value = dataUser.examine1_2TimesWeek
        _page4_button_3_5_per_week.value = dataUser.examine3_5TimesWeek
        _page4_button_every_day_fitness.value = dataUser.everyDayFitness
    }

    fun changeMinActive(): View.OnClickListener? {

        Log.e(TAG, "1 clickkkkkkk")
        _page4_button_min_activ.value = true
        _page4_button_fast_walk.value = false
        _page4_button_1_2_per_week.value = false
        _page4_button_3_5_per_week.value = false
        _page4_button_every_day_fitness.value = false

        dataUser.minimalPhysicalActive = true
        dataUser.fastWalkOnFoot = false
        dataUser.examine1_2TimesWeek = false
        dataUser.examine3_5TimesWeek = false
        dataUser.everyDayFitness = false

        return null
    }

    fun changeFastWalk(): View.OnClickListener? {
        Log.e(TAG, "2 clickkkkkkk")
        _page4_button_min_activ.value = false
        _page4_button_fast_walk.value = true
        _page4_button_1_2_per_week.value = false
        _page4_button_3_5_per_week.value = false
        _page4_button_every_day_fitness.value = false

        dataUser.minimalPhysicalActive = false
        dataUser.fastWalkOnFoot = true
        dataUser.examine1_2TimesWeek = false
        dataUser.examine3_5TimesWeek = false
        dataUser.everyDayFitness = false
        return null
    }

    fun changen12PerWeek(): View.OnClickListener? {
        Log.e(TAG, "3 clickkkkkkk")
        _page4_button_min_activ.value = false
        _page4_button_fast_walk.value = false
        _page4_button_1_2_per_week.value = true
        _page4_button_3_5_per_week.value = false
        _page4_button_every_day_fitness.value = false

        dataUser.minimalPhysicalActive = false
        dataUser.fastWalkOnFoot = false
        dataUser.examine1_2TimesWeek = true
        dataUser.examine3_5TimesWeek = false
        dataUser.everyDayFitness = false
        return null
    }

    fun changen35PerWeek(): View.OnClickListener? {
        Log.e(TAG, "4 clickkkkkkk")
        _page4_button_min_activ.value = false
        _page4_button_fast_walk.value = false
        _page4_button_1_2_per_week.value = false
        _page4_button_3_5_per_week.value = true
        _page4_button_every_day_fitness.value = false

        dataUser.minimalPhysicalActive = false
        dataUser.fastWalkOnFoot = false
        dataUser.examine1_2TimesWeek = false
        dataUser.examine3_5TimesWeek = true
        dataUser.everyDayFitness = false
        return null
    }

    fun changeEveryDayFitness(): View.OnClickListener? {
        Log.e(TAG, "5 clickkkkkkk")
        _page4_button_min_activ.value = false
        _page4_button_fast_walk.value = false
        _page4_button_1_2_per_week.value = false
        _page4_button_3_5_per_week.value = false
        _page4_button_every_day_fitness.value = true

        dataUser.minimalPhysicalActive = false
        dataUser.fastWalkOnFoot = false
        dataUser.examine1_2TimesWeek = false
        dataUser.examine3_5TimesWeek = false
        dataUser.everyDayFitness = true
        return null
    }
}