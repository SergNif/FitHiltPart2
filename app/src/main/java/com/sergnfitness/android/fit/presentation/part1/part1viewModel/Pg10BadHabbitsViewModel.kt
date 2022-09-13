package com.sergnfitness.android.fit.presentation.part1.part1viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.ApiRepository
import com.sergnfitness.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class Pg10BadHabbitsViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
) : ViewModel() {
    val taG = "Pg10BadHabbitsViewModel"

    lateinit var userClass: User
    lateinit var dataUser: DataUser

    private var _page10_fast_food = MutableLiveData<Boolean>(false)
    val live_page10_fast_food: LiveData<Boolean> = _page10_fast_food

    private var _page10_eat_nigth = MutableLiveData<Boolean>(false)
    val live_page10_eat_nigth: LiveData<Boolean> = _page10_eat_nigth

    private var _page10_fast_shugar = MutableLiveData<Boolean>(false)
    val live_page10_fast_shugar: LiveData<Boolean> = _page10_fast_shugar

    private var _page10_nothing_of_list = MutableLiveData<Boolean>(false)
    val live_page10_nothing_of_list: LiveData<Boolean> = _page10_nothing_of_list


    private val _dataUserLiveData = MutableLiveData<DataUser?>()
    val dataUserLiveData: LiveData<DataUser?> = _dataUserLiveData

    private val _userResourceLiveData = MutableLiveData<Resource<Any>>()
    val userResourceLiveData: LiveData<Resource<Any>> = _userResourceLiveData

    private val _dataUserResourceLiveData = MutableLiveData<Resource<Any>>()
    val dataUserResourceLiveData: LiveData<Resource<Any>> = _dataUserResourceLiveData

    fun initLive() {
        _page10_fast_food.value = dataUser.fastFood
        _page10_eat_nigth.value = dataUser.laterNight
        _page10_fast_shugar.value = dataUser.fastSugar
        _page10_nothing_of_list.value = dataUser.Nothing
    }

    fun changeFastFood() {
        _page10_fast_food.value = true
        _page10_eat_nigth.value = false
        _page10_fast_shugar.value = false
        _page10_nothing_of_list.value = false

        dataUser.fastFood = true
        dataUser.laterNight = false
        dataUser.fastSugar = false
        dataUser.Nothing = false
    }

    fun changeEatNigth() {
        _page10_fast_food.value = false
        _page10_eat_nigth.value = true
        _page10_fast_shugar.value = false
        _page10_nothing_of_list.value = false

        dataUser.fastFood = false
        dataUser.laterNight = true
        dataUser.fastSugar = false
        dataUser.Nothing = false
    }

    fun changeFastShugar() {
        _page10_fast_food.value = false
        _page10_eat_nigth.value = false
        _page10_fast_shugar.value = true
        _page10_nothing_of_list.value = false

        dataUser.fastFood = false
        dataUser.laterNight = false
        dataUser.fastSugar = true
        dataUser.Nothing = false
    }

    fun changeNothingOfList() {
        _page10_fast_food.value = false
        _page10_eat_nigth.value = false
        _page10_fast_shugar.value = false
        _page10_nothing_of_list.value = true

        dataUser.fastFood = false
        dataUser.laterNight = false
        dataUser.fastSugar = false
        dataUser.Nothing = true
    }

    fun createDataUserOnServer(email: String)  = viewModelScope.launch {
        Log.e(taG, "inside query")

        safeCallPostDataUserOfEmailViewModel(email)
    }

    private suspend fun safeCallPostDataUserOfEmailViewModel(email: String) {
        _dataUserResourceLiveData.postValue(Resource.Loading())

//        val retroService = RetrofitInstanceModule.getRetroInstance().create(ApiServer::class.java)
        val call =  apiRepository.postQueryCreateUserRepos(email = email, user = dataUser)
        call.enqueue(object : Callback<DataUser> {
            override fun onFailure(call: Call<DataUser>, t: Throwable) {
                Log.e(taG, "Retrofit 1")
                _dataUserResourceLiveData.postValue(Resource.Error(t.message.toString()))
                _dataUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<DataUser>, response: Response<DataUser>) {
                _dataUserResourceLiveData.postValue(Resource.Loading(response))
                Log.e(taG, "Retrofit 2 ${response.body()?.id}")
                if (response.isSuccessful && response.body() != null) {

                    // получен ответ от сервера после записи данных
                    response.body().let { res ->
                        _dataUserLiveData.postValue(res)
                        _dataUserResourceLiveData.postValue(Resource.Success(res))
                    }
                } else {
                    Log.e(taG, "${response.message()} Retrofit 3")
                    _dataUserLiveData.postValue(null)
                    _dataUserResourceLiveData.postValue(Resource.Error("${response.message()} No this data"))
                }
            }
        })
    }

}