package com.sergnfitness.android.fit.presentation.part1.part1viewModel


import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Pg3DataAgeHightWeightViewModel @Inject constructor(
//    private val getUserOfIdApiUseCase: GetUserOfIdApiUseCase,
//    private val apiRepository: ApiRepository,
////    private val NOTuSEsGetUserOfIdApiUseCase: NOTuSEsGetUserOfIdApiUseCase,
//    private val getUserSharedPreferenceUseCase: GetUserSharedPreferenceUseCase,
//    private val saveUserSharedPreferenceUseCase: SaveUserSharedPreferenceUseCase,
//    private val createExemplarClassDataUserStorageUseCase: CreateExemplarClassDataUserStorageUseCase,

) : ViewModel() {
    var userClass: User = User()
    var dataUser: DataUser = DataUser()

    val taG = "Pg3DataAgeHightWeightViewModel"

    var list = mutableMapOf<String, String>()

    private val _age = MutableLiveData<Int>()
    val live_age: LiveData<Int> = _age

    private val _height = MutableLiveData<Int>()
    val live_height: LiveData<Int> = _height

    private val _weight = MutableLiveData<String>()
    val live_weight: LiveData<String> = _weight

    private val _desired_weight = MutableLiveData<String>()
    val live_desired_weight: LiveData<String> = _desired_weight


    fun changeAge(text: String) {
        println(" viewModel vm  $text")
        _age.value = text.toInt()
        live_age.value?.let { dataUser.age = it }
    }

    fun changeHeight(text: String) {
        _height.value = text.toInt()
        live_height.value?.let { dataUser.height = it }
    }

    fun changeWeight(text: String) {
        _weight.value = text
        live_weight.value?.let { dataUser.weight = it }
    }

    fun changedesireWeight(text: String) {
        _desired_weight.value = text
        live_desired_weight.value?.let { dataUser.desired_weight = it }
    }

    //
//    fun changeAge2(text: MutableMap<String, String>) {
//        _age.value = text.get("age")?.toInt()
//        _height.value = text.get("height")?.toInt()
//        _weight.value = text.get("weight")
//        _desired_weight.value = text.get("desired_weight")
//        }
    fun creatUserClass() {
        { dataUser.age }.let { live_age.value };  // list["age"]?.toInt()!!
        { dataUser.height }.let { live_height.value };  // list["height"]?.toInt()!!
        { dataUser.weight }.let { live_weight.value };  // list["weight"]!!
        { dataUser.desired_weight }.let { live_desired_weight.value }  // list["desired_weight"]!!
    }

    fun makeList() {
        list["age"] = dataUser.age.toString()
        list["height"] = dataUser.height.toString()
        list["weight"] = dataUser.weight.toString()
        list["desired_weight"] = dataUser.desired_weight.toString()

        _age.value = list["age"]?.toInt()
        _height.value = list["height"]?.toInt()
        _weight.value = list["weight"]
        _desired_weight.value = list["desired_weight"]
        Log.e(taG, "makeList  ${dataUser}")
    }

}