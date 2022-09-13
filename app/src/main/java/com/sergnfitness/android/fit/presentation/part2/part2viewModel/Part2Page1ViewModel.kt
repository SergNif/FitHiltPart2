package com.sergnfitness.android.fit.presentation.part2.part2viewModel

import android.util.Log
import androidx.core.util.Pair
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.sergnfitness.android.fit.presentation.adapter.RecyclerViewAdapter
import com.sergnfitness.domain.models.UserMenuDay
import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.MenuDay
import com.sergnfitness.domain.models.MenuDayList
import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.ApiRepository
import com.sergnfitness.domain.repository.UserRepository
import com.sergnfitness.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

@HiltViewModel
class Part2Page1ViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val apiRepository: ApiRepository,
) : ViewModel() {
    val taG = "Part2Page1ViewModel "

    lateinit var userClass: User
    lateinit var dataUser: DataUser
    lateinit var listMenuDay: List<MenuDay>
    lateinit var recyclerViewAdapter: RecyclerViewAdapter


    private var _startData: MutableLiveData<String> =
        MutableLiveData(LocalDateTime.now().plusDays(0).toString().split("T")[0])
    val startData: LiveData<String>
        get() = _startData

    private val _input_weight = MutableLiveData<String>()

    val input_weight: LiveData<String> = _input_weight

    private var _endData: MutableLiveData<String> =
        MutableLiveData(LocalDateTime.now().plusDays(1).toString().split("T")[0])
    val endData: LiveData<String>
        get() = _endData

    private var _startDataAPI: MutableLiveData<String> =
        MutableLiveData(LocalDateTime.now().plusDays(0).toString().split("T")[0])
    val startDataAPI: LiveData<String>
        //    formatterDD_MMM_YYYY.format(formatterYYYY_MM_DD.parse(_startDataAPI.value.toString()))
        get() = _startDataAPI

    private var _endDataAPI: MutableLiveData<String> =
        MutableLiveData(LocalDateTime.now().plusDays(1).toString().split("T")[0])
    val endDataAPI: LiveData<String>
        get() = _endDataAPI

    private var _pickerCalendarData = MutableLiveData<Pair<Long, Long>>()
    val pickerCalendarData: MutableLiveData<Pair<Long, Long>>
        get() = _pickerCalendarData

    private var _listWeightForChart = mutableListOf<Entry>()

    val listWeightForChart: MutableList<Entry>
        get() = _listWeightForChart
    private val CHART_LABEL = "DAY_CHART"
    private val _lineDataSet = MutableLiveData(LineDataSet(listWeightForChart, CHART_LABEL))

    private var _positionSpinner = MutableLiveData<Int>()
    val positionSpinnerLive: LiveData<Int> = _positionSpinner

    private var _positionSpinnerStart = MutableLiveData<Int>(12-LocalDateTime.now().plusDays(0).toString().split("T")[0].split("-")[1].toInt())
    val positionSpinnerStartLive: LiveData<Int> = _positionSpinnerStart


    val lineDataSet: LiveData<LineDataSet> = _lineDataSet
    private val _userResourceLiveData = MutableLiveData<Resource<Any>>()

    val userResourceLiveData: LiveData<Resource<Any>> = _userResourceLiveData

    private val _dataUserResourceLiveData = MutableLiveData<Resource<Any>>()
    val dataUserResourceLiveData: LiveData<Resource<Any>> = _dataUserResourceLiveData

    private val _menuDayResourceLiveData = MutableLiveData<Resource<Any>>()
    val menuDayResourceLiveData: LiveData<Resource<Any>> = _menuDayResourceLiveData

    private val _menuDayListLiveData = MutableLiveData<MenuDayList?>()
    val menuDayListLiveData: LiveData<MenuDayList?> = _menuDayListLiveData

    private val _userMenuDayLiveData = MutableLiveData<UserMenuDay>()
    val userMenuDayLiveData: LiveData<UserMenuDay> = _userMenuDayLiveData

    private val _dataUserLiveData = MutableLiveData<DataUser?>()
    val dataUserLiveData: LiveData<DataUser?> = _dataUserLiveData


    lateinit var recyclerListData: MutableLiveData<MenuDayList>

    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")

    init {
        recyclerListData = MutableLiveData<MenuDayList>()
    }

    fun getMenuListObserverable(): MutableLiveData<MenuDayList> {
        return recyclerListData
    }

    fun formatDataPickerAPI(picker: Pair<Long, Long>? = pickerCalendarData.value) {
        if (picker != null) {
            _startDataAPI.value = outputDateFormatAPI.format(picker.first)
            Log.e(taG, " PICKER  ${_startDataAPI.value.toString()}")
        }
        if (picker != null) {
            _endDataAPI.value = outputDateFormatAPI.format(picker.second)
            Log.e(taG, " PICKER  ${_endDataAPI.value.toString()}")
        }
    }
//    fun saveDataStartDataCalendar(startData: String, endData: String) {
//        val saveDataShared: Boolean =
//            saveUserNameUseCase.executeSaveData(startData = startData, endData = endData)
//    }

    val outputDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    val outputDateFormatAPI = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    fun formatDataPicker(picker: Pair<Long, Long>?) {

        if (picker != null) {
            _startData.value = outputDateFormat.format(picker.first).toString()
        }
        if (picker != null) {
            _endData.value = outputDateFormat.format(picker.second).toString()
        }
        _pickerCalendarData.value = picker
    }

    //*******  RETROFIT *****
    fun launchGetMenuList(startData: String, endData: String) = viewModelScope.launch {
        delay(500)
        userClass.id?.let { getMenuList(it, startData, endData) }
    }


    private suspend fun getMenuList(id: Int, startData: String, endData: String) {
        _menuDayResourceLiveData.postValue(Resource.Loading())
        Log.e(taG, " API query startData ${startData}  endData ${endData}")

        val call =
            apiRepository.getMenuDayStrings(id = id, startDate = startData, endDate = endData)


//        val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)
//        val call = retrofitInstance.getMenuStrings(id = getIdFromSharedPreferenses(), date = LocalDateTime.now().plusDays(dateMenuRecyle).toString().split("T")[0])
//        val call = retrofitInstance.getMenuStrings(id = getIdFromSharedPreferenses(), startDate = startData, endDate = endData)
        call.enqueue(object : Callback<MenuDayList> {
            override fun onFailure(call: Call<MenuDayList>, t: Throwable) {
                Log.e(taG, "Get Menu List NULL")
                _menuDayResourceLiveData.postValue(Resource.Error(t.message.toString()))
                _menuDayListLiveData.postValue(null)
                recyclerListData.postValue(null)
            }

            override fun onResponse(call: Call<MenuDayList>, response: Response<MenuDayList>) {
                _menuDayResourceLiveData.postValue(Resource.Loading(response))
                Log.e(taG, "Get Menu List IS ${response.body()}")
                if (response.isSuccessful && response.body() != null) {
                    response.body().let { res ->
                        _menuDayListLiveData.postValue(res)
                        recyclerListData.postValue(res)
                    }
                } else {
                    Log.e(taG, "Get Menu List ELSE ${response.body()}")
                    _menuDayListLiveData.postValue(null)
                    _menuDayResourceLiveData.postValue(Resource.Error("${response.message()} No this data"))
                    recyclerListData.postValue(null)
                }
            }
        }
        )
    }
    //*******  RETROFIT *****

    fun funListWeightForChart(listMenuDay: List<MenuDay>): MutableList<Entry> {
        this.listMenuDay = listMenuDay
        Log.e(taG, "Wiegth ${listMenuDay}")
        listMenuDay.forEachIndexed { index, e ->
            _listWeightForChart.add(Entry(index.toFloat(), e.weight.toFloat()))
            Log.e(taG, "Wiegth ${e.weight.toFloat()}")
        }
        _lineDataSet.value = LineDataSet(listWeightForChart, CHART_LABEL)
        return _listWeightForChart
    }

    fun changeWeght(text: String, s: String) {
        dataUser.weight = text.toDouble().toString()
        dataUser.date = s//date//LocalDateTime.now().toString().split("T")[0]
        dataUser.fitness_id = dataUser.id

    }

    fun launchUpdateDataPage3() = viewModelScope.launch {
        dataUser.id?.let { updateDataPage3(it) }
    }

    private suspend fun updateDataPage3(id: Int) {
        _dataUserResourceLiveData.postValue(Resource.Loading())
        _dataUserLiveData

//        val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)
        val call = apiRepository.pathUpdateWeigth(
            user_id = id,
            userData3 = dataUser)

        call.enqueue(object : Callback<DataUser> {
            override fun onFailure(call: Call<DataUser>, t: Throwable) {
                _dataUserResourceLiveData.postValue(Resource.Error(t.message.toString()))
                _dataUserLiveData.postValue(null)
                Log.e(taG, "Data Page3 NULL")
            }

            override fun onResponse(call: Call<DataUser>, response: Response<DataUser>) {
                _dataUserResourceLiveData.postValue(Resource.Loading(response))
                if (response.isSuccessful && response.body() != null) {
                    Log.e(taG, "Data response Body IS ${response.body()}")
                    Log.e(taG, "Data User IS ${dataUser}")
                    _dataUserLiveData.postValue(response.body())
                    _dataUserResourceLiveData.postValue(Resource.Success(response))
                    Log.e(taG, "Retrofit 22 ${dataUserLiveData.value}")
                } else {
                    Log.e(taG, "Data Page3 ELSE ${response.body()}")
                    _dataUserLiveData.postValue(null)
                    _dataUserResourceLiveData.postValue(Resource.Error("${response.message()} No this data"))
                }
            }
        }
        )
    }

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
        _menuDayResourceLiveData.postValue(Resource.Loading())
//        val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)

        val call = apiRepository.postQueryCreateMenuDay(menuDay = userParam, position = position)
        call.enqueue(object : Callback<UserMenuDay> {
            override fun onFailure(call: Call<UserMenuDay>, t: Throwable) {
                Log.e(taG, "POST MENU Day NULL")
                _menuDayResourceLiveData.postValue(Resource.Error(t.message.toString()))
                _userMenuDayLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UserMenuDay>, response: Response<UserMenuDay>) {
                _menuDayResourceLiveData.postValue(Resource.Loading(response))
                Log.e(taG, "Retrofit 2 ${response.body()}")
                if (response.isSuccessful && response.body() != null) {
                    Log.e(taG, "POST MENU Day IS ${response.body()}")
                    _userMenuDayLiveData.postValue(response.body())
                    _menuDayResourceLiveData.postValue(Resource.Success(response))
                } else {
                    Log.e(taG, "POST MENU Day ELSE ${response.body()}")
                    _userMenuDayLiveData.postValue(null)
                    _menuDayResourceLiveData.postValue(Resource.Error("${response.message()} No this data"))
                }
            }
        }
        )
    }

    fun countEndDataAPI(i: Int): String {
        return LocalDateTime.now().plusDays(-i.toLong()).toString().split("T")[0]
    }

    fun setPositionSpinner(p2: Int) {
        _positionSpinner.value = p2
    }

}

