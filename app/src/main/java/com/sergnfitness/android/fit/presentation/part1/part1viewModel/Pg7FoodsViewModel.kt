package com.sergnfitness.android.fit.presentation.part1.part1viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Pg7FoodsViewModel @Inject constructor() : ViewModel() {

    var userClass: User = User()
    var dataUser: DataUser = DataUser()
    val TAG = "Pg6SourceFiberViewModel"

    private var _text_page6_egg = MutableLiveData<Boolean>(false)
    val live_text_page6_egg: LiveData<Boolean> = _text_page6_egg

    private var _text_page6_chees = MutableLiveData<Boolean>(false)
    val live_text_page6_chees: LiveData<Boolean> = _text_page6_chees

    private var _text_page6_nut = MutableLiveData<Boolean>(false)
    val live_text_page6_nut: LiveData<Boolean> = _text_page6_nut

    private var _text_page6_cottage_cheese = MutableLiveData<Boolean>(false)
    val live_text_page6_cottage_cheese: LiveData<Boolean> = _text_page6_cottage_cheese

    private var _text_page6_kefir = MutableLiveData<Boolean>(false)
    val live_text_page6_kefir: LiveData<Boolean> = _text_page6_kefir

    private var _text_page6_yogurt = MutableLiveData<Boolean>(false)
    val live_text_page6_yogurt: LiveData<Boolean> = _text_page6_yogurt

//    text_page6_egg
//    text_page6_chees
//    text_page6_nut
//    text_page6_cottage_cheese
//    text_page6_kefir
//    text_page6_yogurt

    fun initLive() {
        _text_page6_egg.value = dataUser.egg
        _text_page6_chees.value = dataUser.cheese
        _text_page6_nut.value = dataUser.nuts
        _text_page6_cottage_cheese.value = dataUser.cottage
        _text_page6_kefir.value = dataUser.kefir
        _text_page6_yogurt.value = dataUser.yogurt
    }

    fun changetextPage6Egg(bool: Boolean) {
        _text_page6_egg.value = bool
        dataUser.egg  = bool
    }
    fun changetextPage6Chees(bool: Boolean) {
        _text_page6_chees.value = bool
        dataUser.cheese  = bool
    }
    fun changetextPage6Nut(bool: Boolean) {
        _text_page6_nut.value = bool
        dataUser.nuts  = bool
    }
    fun changetextPage6CottageCheese(bool: Boolean) {
        _text_page6_cottage_cheese.value = bool
        dataUser.cottage  = bool
    }
    fun changetextPage6Kefir(bool: Boolean) {
        _text_page6_kefir.value = bool
        dataUser.kefir  = bool
    }
    fun changetextPage6Yogurt(bool: Boolean) {
        _text_page6_yogurt.value = bool
        dataUser.yogurt  = bool
    }
}