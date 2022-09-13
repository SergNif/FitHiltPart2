package com.sergnfitness.android.fit.presentation.part1.part1viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Pg6SourceFiberViewModel @Inject constructor() : ViewModel() {


    var userClass: User = User()
    var dataUser: DataUser = DataUser()
    val TAG = "Pg6SourceFiberViewModel"

    private var _text_page6_zukini = MutableLiveData<Boolean>(false)
    val live_text_page6_zukini: LiveData<Boolean> = _text_page6_zukini

    private var _text_page6_tomato = MutableLiveData<Boolean>(false)
    val live_text_page6_tomato: LiveData<Boolean> = _text_page6_tomato

    private var _text_page6_baklagan = MutableLiveData<Boolean>(false)
    val live_text_page6_baklagan: LiveData<Boolean> = _text_page6_baklagan

    private var _text_page6_color_cabbage = MutableLiveData<Boolean>(false)
    val live_text_page6_color_cabbage: LiveData<Boolean> = _text_page6_color_cabbage

    private var _text_page6_ogurz = MutableLiveData<Boolean>(false)
    val live_text_page6_ogurz: LiveData<Boolean> = _text_page6_ogurz

    private var _text_page6_broccoli = MutableLiveData<Boolean>(false)
    val live_text_page6_broccoli: LiveData<Boolean> = _text_page6_broccoli

    private var _text_page6_button_muhroms = MutableLiveData<Boolean>(false)
    val live_text_page6_button_muhroms: LiveData<Boolean> = _text_page6_button_muhroms

    private var _text_page6_avocado = MutableLiveData<Boolean>(false)
    val live_text_page6_avocado: LiveData<Boolean> = _text_page6_avocado

    fun initLive() {
        _text_page6_zukini.value = dataUser.zucchini
        _text_page6_tomato.value = dataUser.tomato
        _text_page6_baklagan.value = dataUser.eggplant
        _text_page6_color_cabbage.value = dataUser.cauliflower
        _text_page6_ogurz.value = dataUser.cucumbers
        _text_page6_broccoli.value = dataUser.broccoli
        _text_page6_button_muhroms.value = dataUser.mushrooms
        _text_page6_avocado.value = dataUser.avocado
    }

    fun changetextPage6Zukini(bool: Boolean) {
        _text_page6_zukini.value = bool
        dataUser.zucchini = bool
    }

    fun changetextPage6Tomato(bool: Boolean) {
        _text_page6_tomato.value = bool
        dataUser.tomato = bool
    }

    fun changetextPage6Baklagan(bool: Boolean) {
        _text_page6_baklagan.value = bool
        dataUser.eggplant = bool
    }

    fun changetextPage6ColorCabbage(bool: Boolean) {
        _text_page6_color_cabbage.value = bool
        dataUser.cauliflower = bool
    }

    fun changetextPage6Ogurz(bool: Boolean) {
        _text_page6_ogurz.value = bool
        dataUser.cucumbers = bool
    }

    fun changetextPage6Broccoli(bool: Boolean) {
        _text_page6_broccoli.value = bool
        dataUser.broccoli = bool
    }

    fun changetextPage6ButtonMuhroms(bool: Boolean) {
        _text_page6_button_muhroms.value = bool
        dataUser.mushrooms = bool
    }

    fun changetextPage6Avocado(bool: Boolean) {
        _text_page6_avocado.value = bool
        dataUser.avocado = bool
    }


//    text_page6_zukini
//    text_page6_tomato
//    text_page6_baklagan
//    text_page6_color_cabbage
//    text_page6_ogurz
//    text_page6_broccoli
//    text_page6_button_muhroms
//    text_page6_avocado

}