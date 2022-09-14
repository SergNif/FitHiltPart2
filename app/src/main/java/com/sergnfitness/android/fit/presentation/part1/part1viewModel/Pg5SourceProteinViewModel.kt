package com.sergnfitness.android.fit.presentation.part1.part1viewModel

import android.util.Log
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
    val taG = "Pg5SourceProteinViewModel"

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

//    private var withoutMeat: Boolean = false
//    private var withoutFish: Boolean = false
    private var withoutMeat = _page5_button_without_meat.value
    private var withoutFish = _text_page5_without_fish.value

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

    fun changeChiken() {
        _text_page5_chiken.value.let { _text_page5_chiken.value = !(it)!! }
        if (_page5_button_without_meat.value == false) {
            dataUser.chicken = !dataUser.chicken
        }else{
            _text_page5_chiken.value.let {_text_page5_chiken.value = false}
            dataUser.chicken = false
        }
    }

    fun changeTurkey() {
        _text_page5_turkey.value.let {_text_page5_turkey.value = !(it)!!}
        if (_page5_button_without_meat.value == false) {
            dataUser.tyrkey = !dataUser.tyrkey
        }else{
            _text_page5_turkey.value.let {_text_page5_turkey.value = false}
            dataUser.tyrkey = false
        }
    }

    fun changePork() {
        _page5_button_pork.value.let {_page5_button_pork.value = !(it)!!}
        if (_page5_button_without_meat.value == false) {
            dataUser.pork = !dataUser.pork
        }else{
            _page5_button_pork.value.let {_page5_button_pork.value = false}
            dataUser.pork = false
        }
    }

    fun changeBeef() {
        _page5_beef.value.let {_page5_beef.value = !(it)!!}
        if (_page5_button_without_meat.value == false) {
            dataUser.meat = !dataUser.meat
        }else{
            _page5_beef.value.let {_page5_beef.value = false}
            dataUser.meat = false
        }
    }

    fun changeFish() {
        _page5_button_fish.value.let {_page5_button_fish.value = !(it)!!}
        if (_text_page5_without_fish.value == false) {
            dataUser.fish = !dataUser.fish
        }else{
            _page5_button_fish.value.let {_page5_button_fish.value = false}
            dataUser.fish = false
        }
    }

    fun changeSeafood() {
        _page5_seafood.value.let {_page5_seafood.value = !(it)!!}
        if (_text_page5_without_fish.value == false) {
            dataUser.fish = !dataUser.fish
        }else{
            _page5_seafood.value.let {_page5_seafood.value = false}
            dataUser.fish = false
        }
    }

    fun changeWithoutMeat() {

        _page5_button_without_meat.value.let {_page5_button_without_meat.value = !(it)!!}
        dataUser.withoutMeat.let { dataUser.withoutMeat = !(it)}
        if (_page5_button_without_meat.value == true) {
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

    fun changeWithoutFish() {
        _text_page5_without_fish.value.let {_text_page5_without_fish.value = !(it)!!}
        dataUser.withoutFish.let { dataUser.withoutFish = !(it)}
        if (_text_page5_without_fish.value == true) {
            _page5_button_fish.value = false
            _page5_seafood.value = false
            dataUser.fish = false
            dataUser.seaFood = false
//            dataUser.withoutMeat = false

        }
    }


//    text_page5_chiken
}
