package com.sergnfitness.android.fit.presentation.part1.part1viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Pg5SourceProteinViewModel @Inject constructor() : ViewModel() {
    var userClass: User = User()

    var dataUser: DataUser = DataUser()
    val TAG = "Pg5SourceProteinViewModel"

    private var _text_page5_chiken = MutableLiveData<Boolean>(false)
    val live_text_page5_chiken: LiveData<Boolean> = _text_page5_chiken

    private var _text_page5_turkey = MutableLiveData<Boolean>(false)
    val live_text_page5_turkey: LiveData<Boolean> = _text_page5_turkey

    private var _page5_button_pork = MutableLiveData<Boolean>(false)
    val live_page5_button_pork: LiveData<Boolean> = _page5_button_pork

    private var _page5_beef = MutableLiveData<Boolean>(false)
    val live_page5_beef: LiveData<Boolean> = _page5_beef

    private var _page5_button_fish = MutableLiveData<Boolean>(false)
    val live_page5_button_fish: LiveData<Boolean> = _page5_button_fish

    private var _page5_seafood = MutableLiveData<Boolean>(false)
    val live_page5_seafood: LiveData<Boolean> = _page5_seafood

    private var _page5_button_without_meat = MutableLiveData<Boolean>(false)
    val live_page5_button_without_meat: LiveData<Boolean> = _page5_button_without_meat

    private var _text_page5_without_fish = MutableLiveData<Boolean>(false)
    val live_text_page5_without_fish: LiveData<Boolean> = _text_page5_without_fish

    private var withoutMeat: Boolean = false
    private var withoutFish: Boolean = false

    //    text_page5_without_fish
//    page5_button_without_meat
//    page5_seafood
//    page5_button_fish
//    page5_beef
//    page5_button_pork
//    text_page5_turkey

    fun initLive() {
        _text_page5_chiken.value = dataUser.chicken
        _text_page5_turkey.value = dataUser.tyrkey
        _page5_button_pork.value = dataUser.pork
        _page5_beef.value = dataUser.meat
        _page5_button_fish.value = dataUser.fish
        _page5_seafood.value = dataUser.seaFood
        _page5_button_without_meat.value = dataUser.withoutMeat
        _text_page5_without_fish.value = dataUser.withoutFish
    }

    fun changeChiken(bool: Boolean) {
        if (!withoutMeat) {
            _text_page5_chiken.value = bool
            dataUser.chicken = bool
        }
    }

    fun changeTurkey(bool: Boolean) {
        if (!withoutMeat) {
            _text_page5_turkey.value = bool
            dataUser.tyrkey = bool
        }
    }

    fun changePork(bool: Boolean) {
        if (!withoutMeat) {
            _page5_button_pork.value = bool
            dataUser.pork = bool
        }
    }

    fun changeBeef(bool: Boolean) {
        if (!withoutMeat) {
            _page5_beef.value = bool
            dataUser.meat = bool
        }
    }

    fun changeFish(bool: Boolean) {
        if (!withoutFish) {
            _page5_button_fish.value = bool
            dataUser.fish = bool
        }
    }

    fun changeSeafood(bool: Boolean) {
        if (!withoutFish) {
            _page5_seafood.value = bool
            dataUser.seaFood = bool
        }
    }

    fun changeWithoutMeat(bool: Boolean) {
        withoutMeat = bool
        _page5_button_without_meat.value = bool
        dataUser.withoutMeat = bool
        if (bool) {
            _text_page5_chiken.value = false
            _text_page5_turkey.value = false
            _page5_button_pork.value = false
            _page5_beef.value = false
            dataUser.chicken = false
            dataUser.tyrkey = false
            dataUser.pork = false
            dataUser.meat = false

        }
    }

    fun changeWithoutFish(bool: Boolean) {
        withoutFish = bool
        _text_page5_without_fish.value = bool
        dataUser.withoutFish = bool
        if (bool) {
            _page5_button_fish.value = false
            _page5_seafood.value = false
            dataUser.fish = false
            dataUser.seaFood = false
//            dataUser.withoutMeat = false
//            dataUser.withoutFish = false
        }
    }


//    text_page5_chiken
}
