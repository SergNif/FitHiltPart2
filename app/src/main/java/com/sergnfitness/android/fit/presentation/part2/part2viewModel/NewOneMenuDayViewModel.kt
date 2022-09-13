package com.sergnfitness.android.fit.presentation.part2.part2viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergnfitness.android.fit.presentation.adapter.RecyclerViewAdapter
import com.sergnfitness.domain.models.MenuDay
import com.sergnfitness.domain.models.MenuDayList
import com.sergnfitness.domain.models.UserMenuDay
import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.ApiRepository
import com.sergnfitness.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class NewOneMenuDayViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
) : ViewModel() {


    val taG = "NewOneMenuDayViewModel "

    lateinit var userClass: User
    lateinit var dataUser: DataUser
    lateinit var listMenuDay: List<MenuDay>

    var positionRecycklerViewEdit: Int? = null

    private val _newOneMenuDayResourceLiveData = MutableLiveData<Resource<Any>>()
    val newOneMenuDayResourceLiveData: LiveData<Resource<Any>> = _newOneMenuDayResourceLiveData

    private val _newOneMenuDayLiveData = MutableLiveData<UserMenuDay>()
    val newOneMenuDayLiveData: LiveData<UserMenuDay> = _newOneMenuDayLiveData

    private val _menuDayListLiveData = MutableLiveData<MenuDayList>()
    val menuDayListLiveData: LiveData<MenuDayList> = _menuDayListLiveData

    private var _startDataAPI: MutableLiveData<String> =
        MutableLiveData(LocalDateTime.now().plusDays(0).toString().split("T")[0])
    val startDataAPI: LiveData<String>
        //    formatterDD_MMM_YYYY.format(formatterYYYY_MM_DD.parse(_startDataAPI.value.toString()))
        get() = _startDataAPI

    private var _endDataAPI: MutableLiveData<String> =
        MutableLiveData(LocalDateTime.now().plusDays(1).toString().split("T")[0])
    val endDataAPI: LiveData<String>
        get() = _endDataAPI


    fun createUserMenuDay(
//        id_tab: Int,
        id: Int,
        age: Int,
        date: String,
        time: String,
        desired_weight: Double,
        height: Int,
        weight: Double,
        fitness_id: Int,
        header: String,
        menu: String,
    ): UserMenuDay {
        var _classUserMenuDay = UserMenuDay(
            0,
            id,
            age,
            date,
            time,
            desired_weight,
            height,
            weight,
            fitness_id,
            header,
            menu)
        return _classUserMenuDay
    }

    fun launchPostMenuDay(userParam: UserMenuDay, position: Int) = viewModelScope.launch {
        postMenuDay(userParam, position)
    }

    private suspend fun postMenuDay(userParam: UserMenuDay, position: Int) {
        _newOneMenuDayResourceLiveData.postValue(Resource.Loading())
//        val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)

        val call = apiRepository.postQueryCreateMenuDay(menuDay = userParam,
            position = position)//, date = java.sql.Date(2022872))
        call.enqueue(object : Callback<UserMenuDay> {
            override fun onFailure(call: Call<UserMenuDay>, t: Throwable) {
                Log.e(taG, "POST MENU Day NULL")
                _newOneMenuDayResourceLiveData.postValue(Resource.Error(t.message.toString()))
                _newOneMenuDayLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UserMenuDay>, response: Response<UserMenuDay>) {
                _newOneMenuDayResourceLiveData.postValue(Resource.Loading(response))
                if (response.isSuccessful && response.body() != null) {
                    Log.e(taG, "POST MENU Day IS ${response.body()}")
                    _newOneMenuDayResourceLiveData.postValue(Resource.Success(response))
                    _newOneMenuDayLiveData.postValue(response.body())

                } else {
                    Log.e(taG, "POST MENU Day ELSE ${response.body()}")
                    _newOneMenuDayResourceLiveData.postValue(Resource.Error("${response.message()} No this data"))
                    _newOneMenuDayLiveData.postValue(null)
                }
            }
        }
        )
    }


    fun launchGetMenuList(startData: String, endData: String) = viewModelScope.launch {
        delay(500)
        dataUser.id?.let { getMenuList(it, startData, endData) }
    }

    private suspend fun getMenuList(id: Int, startData: String, endData: String) {
        _newOneMenuDayResourceLiveData.postValue(Resource.Loading())
        Log.e(taG, " API query startData ${startData}  endData ${endData}")
//        val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)
//        val call = retrofitInstance.getMenuStrings(id = getIdFromSharedPreferenses(), date = LocalDateTime.now().plusDays(dateMenuRecyle).toString().split("T")[0])
        val call =
            apiRepository.getMenuDayStrings(id = id, startDate = startData, endDate = endData)
        call.enqueue(object : Callback<MenuDayList> {
            override fun onFailure(call: Call<MenuDayList>, t: Throwable) {
                Log.e(taG, "Get Menu List NULL")
                _newOneMenuDayResourceLiveData.postValue(Resource.Error(t.message.toString()))
                _menuDayListLiveData.postValue(null)
            }

            override fun onResponse(call: Call<MenuDayList>, response: Response<MenuDayList>) {
                _newOneMenuDayResourceLiveData.postValue(Resource.Loading(response))
                if (response.isSuccessful && response.body() != null) {
                    //получен ответ от сервера
                    Log.e(taG, "Get Menu List IS ${response.body()}")
                    _newOneMenuDayResourceLiveData.postValue(Resource.Success(response))
                    _menuDayListLiveData.postValue(response.body())

                } else {
                    Log.e(taG, "Get Menu List ELSE ${response.body()}")
                    _newOneMenuDayResourceLiveData.postValue(Resource.Error("${response.message()} No this data"))
                    _menuDayListLiveData.postValue(null)
                }
            }
        }
        )
    }

    fun launchDeleteOneMenuDay(userId:Int, position: Int?) = viewModelScope.launch {
        val vde = "fgr"
        deleteOneMenuDay(userId, position)
    }

    private suspend fun deleteOneMenuDay(userId: Int, pos: Int?) {
        _newOneMenuDayResourceLiveData.postValue(Resource.Loading())
//        val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)

        pos?.let { apiRepository.deleteOneMenuDay(user_id = userId, position = it) }
            ?.enqueue(object : Callback<MenuDayList> {
                override fun onResponse(call: Call<MenuDayList>, response: Response<MenuDayList>) {
                    _newOneMenuDayResourceLiveData.postValue(Resource.Loading(response))
                    if (response.isSuccessful && response.body() != null) {
                        _newOneMenuDayResourceLiveData.postValue(Resource.Success(response))
                        Log.e(taG, "POST MENU Day IS ${response.body()}")
                    } else {
                        _newOneMenuDayResourceLiveData.postValue(Resource.Error("${response.message()} No this data"))
                        Log.e(taG, "POST MENU Day ELSE ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<MenuDayList>, t: Throwable) {
                    _newOneMenuDayResourceLiveData.postValue(Resource.Error(t.message.toString()))
                    _menuDayListLiveData.postValue(null)
                    Log.e(taG, "POST MENU Day NULL")
                }
            }
            )
//        http://195.234.208.168:8085/fit_delete_one_day_menu/24/12
//        http://195.234.208.168:8085/fit_delete_one_day_menu/24/12/
    }
}