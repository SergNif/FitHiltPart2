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
import com.sergnfitness.domain.usecase.GetUserOfIdApiUseCase
import com.sergnfitness.domain.usecase.GetUserSharedPreferenceUseCase
import com.sergnfitness.domain.usecase.SaveUserSharedPreferenceUseCase
import com.sergnfitness.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MenuDayPart2ViewModel @Inject constructor(
private  val userRepository: UserRepository,
    private val getUserOfIdApiUseCase: GetUserOfIdApiUseCase,
    private val apiRepository: ApiRepository,
//    private val NOTuSEsGetUserOfIdApiUseCase: NOTuSEsGetUserOfIdApiUseCase,
    private val getUserSharedPreferenceUseCase: GetUserSharedPreferenceUseCase,
    private val saveUserSharedPreferenceUseCase: SaveUserSharedPreferenceUseCase,
//    private val saveUserNameUseCase:
) : ViewModel() {
    val taG = "MenuDayPart2ViewModel "

    lateinit var userClass: User
    lateinit var dataUser: DataUser
    lateinit var dataUserMenuDay: UserMenuDay
    lateinit var dataMenuDay: MenuDay
    lateinit var listMenuDay: List<MenuDay>
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    var positionRecycklerViewEdit: Int? = null

    lateinit var recyclerListData: MutableLiveData<MenuDayList>

    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")

    init {
        recyclerListData = MutableLiveData<MenuDayList>()
    }

    private var _listWeightForChart = mutableListOf<Entry>()
    val listWeightForChart: MutableList<Entry>
        get() = _listWeightForChart

    val us = userRepository
    private val CHART_LABEL = "DAY_CHART"
    private val dayData = mutableListOf<Entry>()
    private val _lineDataSet = MutableLiveData(LineDataSet(listWeightForChart, CHART_LABEL))
    val lineDataSet: LiveData<LineDataSet> = _lineDataSet

    private var _pickerCalendarData = MutableLiveData<Pair<Long, Long>>()
    val pickerCalendarData: MutableLiveData<Pair<Long, Long>>
        get() = _pickerCalendarData

    private var _startData: MutableLiveData<String> =
        MutableLiveData(userRepository.converStringToData(LocalDateTime.now().plusDays(0).toString().split("T")[0],1))
    val startData: LiveData<String>
        get() = _startData

    private var _endData: MutableLiveData<String> =
        MutableLiveData(userRepository.converStringToData(LocalDateTime.now().plusDays(1).toString().split("T")[0],1))
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


    val outputDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    val outputDateFormatAPI = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    private val _menuListResourceLiveData = MutableLiveData<Resource<Any>>()
    val menuListResourceLiveData: LiveData<Resource<Any>> = _menuListResourceLiveData

    fun getMenuListObserverable(): MutableLiveData<MenuDayList> {
        return recyclerListData
    }


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

    fun oneChangeStartEndData(i: Int) {

//        Log.e(TAG, "3 ${_startData.value}")
        _startData.value = userRepository.converStringToData(_startData.value.toString(), i.toLong()).toString()
//        Log.e(TAG, "33 ${_startData.value}")
//        Log.e(TAG, "1 ${_endData.value}")
        _endData.value = userRepository.converStringToData(_endData.value.toString(), i.toLong()).toString()
//        Log.e(TAG, "11 ${_endData.value}")
//
//        Log.e(TAG, "2 ${_endDataAPI.value}")
//        _endDataAPI.value = LocalDate.parse(_endDataAPI.value).plusDays(i.toLong()).toString()
////        _endDataAPI.value = converStringToData(_endDataAPI.value.toString(), i.toLong()).toString()
//        Log.e(TAG, "4 ${_startDataAPI.value}")
//        _startDataAPI.value = LocalDate.parse(_startDataAPI.value).plusDays(i.toLong()).toString()
//        val d = LocalDate.parse(_endDataAPI.value).plusDays(i.toLong()).toString()
//        _startDataAPI.value = converStringToData(_startDataAPI.value.toString(), i.toLong()).toString()
        changeStartEndDataAPI()
        saveDataStartDataCalendar(startData.value.toString(), endData.value.toString())
    }

    fun saveDataStartDataCalendar(startData: String, endData: String) {
        val saveDataShared: Boolean =
            saveUserSharedPreferenceUseCase.executeSaveData(startData = startData,
                endData = endData)
    }


//    fun converStringToData(dt: String, i: Long): String? {
//        var date85: Date?
//        val formatter25 = SimpleDateFormat("dd MMMM yyyy")
//        try {
//            val formatter2 = DateTimeFormatter.ofPattern("d MMMM yyyy")
//            Log.e(taG, "conv 1 ${dt}")
//            val date2 = LocalDate.parse(dt, formatter2).plusDays(i)
//            Log.e(taG, "conv 2 ${date2}")
//
//            val formatter15 = SimpleDateFormat("yyyy-MM-dd")
//            //  "EEE, MMM d, ''yy"
//            date85 = formatter15.parse(date2.toString())
//            val formatter25 = SimpleDateFormat("dd MMMM yyyy")
//
//        } catch (e: DateTimeParseException) {
//            val formatter15 = SimpleDateFormat("yyyy-MM-dd")
//            //  "EEE, MMM d, ''yy"
//            date85 = formatter15.parse(dt.toString())
//        }
//        return date85?.let { formatter25.format(it) }
//    }

//    fun converStringToDataLive(dt: String, i: Long): String? {
//
////        val formatter2 = DateTimeFormatter.ofPattern("d MMM yyyy")
//        Log.e(taG, "conv 1 ${dt}")
////        val date2 = LocalDate.parse(dt, formatter2).plusDays(i)
////        Log.e(taG, "conv 2 ${date2}")
//
//        val formatter15 = SimpleDateFormat("yyyy-MM-dd")
//        //  "EEE, MMM d, ''yy"
//        val date85 = formatter15.parse(dt.toString())
//        val formatter25 = SimpleDateFormat("dd MMMM yyyy")
//
//        return date85?.let { formatter25.format(it) }
//    }

    fun changeStartEndDataAPI() {
        Log.e(taG, "startData ${startData.value} endData ${endData.value}")
        val formatter2 = DateTimeFormatter.ofPattern("d MMMM yyyy")
        try {
            _startDataAPI.value = LocalDate.parse(startData.value, formatter2).toString()
            _endDataAPI.value = LocalDate.parse(endData.value, formatter2).toString()
        } catch (e: DateTimeParseException) {
            _startDataAPI.value = LocalDate.parse(startData.value.toString())
                .toString()//, DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()
            _endDataAPI.value = LocalDate.parse(endData.value.toString())
                .toString()//, DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()
        }
    }

    fun launchGetMenuList(startData: String, endData: String) = viewModelScope.launch {
        delay(500)
        dataUser.id?.let { getMenuList(it, startData, endData) }
    }

    private suspend fun getMenuList(id: Int, startData: String, endData: String) {
        _menuListResourceLiveData.postValue(Resource.Loading())
        Log.e(taG, " API query startData ${startData}  endData ${endData}")
//        val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)
//        val call = retrofitInstance.getMenuStrings(id = getIdFromSharedPreferenses(), date = LocalDateTime.now().plusDays(dateMenuRecyle).toString().split("T")[0])
        val call =
            apiRepository.getMenuDayStrings(id = id, startDate = startData, endDate = endData)
        call.enqueue(object : Callback<MenuDayList> {
            override fun onFailure(call: Call<MenuDayList>, t: Throwable) {
                Log.e(taG, "Get Menu List NULL")
                _menuListResourceLiveData.postValue(Resource.Error(t.message.toString()))
                recyclerListData.postValue(null)
            }

            override fun onResponse(call: Call<MenuDayList>, response: Response<MenuDayList>) {
                _menuListResourceLiveData.postValue(Resource.Loading(response))
                if (response.isSuccessful && response.body() != null) {
                    //получен ответ от сервера
                    Log.e(taG, "Get Menu List IS ${response.body()}")
                    _menuListResourceLiveData.postValue(Resource.Success(response))
                    recyclerListData.postValue(response.body())

                } else {
                    Log.e(taG, "Get Menu List ELSE ${response.body()}")
                    _menuListResourceLiveData.postValue(Resource.Error("${response.message()} No this data"))
                    recyclerListData.postValue(null)
                }
            }
        }
        )
    }
}