package com.sergnfitness.android.fit.presentation.part1.part1viewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergnfitness.android.model.CoinListState
import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.ApiRepository

import com.sergnfitness.domain.usecase.GetUserOfIdApiUseCase
import com.sergnfitness.domain.usecase.GetUserSharedPreferenceUseCase
import com.sergnfitness.domain.usecase.SaveUserSharedPreferenceUseCase
import com.sergnfitness.domain.util.Resource

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class Pg1MaleFemaleViewModel @Inject constructor(
    private val getUserOfIdApiUseCase: GetUserOfIdApiUseCase,
    private val apiRepository: ApiRepository,
//    private val NOTuSEsGetUserOfIdApiUseCase: NOTuSEsGetUserOfIdApiUseCase,
    private val getUserSharedPreferenceUseCase: GetUserSharedPreferenceUseCase,
    private val saveUserSharedPreferenceUseCase: SaveUserSharedPreferenceUseCase,
) : ViewModel() {

    //   private val userRepository: UserRepositoryImpl by lazy(LazyThreadSafetyMode.NONE) { UserRepositoryImpl(userStorage = SharedPrefUserStorage(requireContext().applicationContext)) }
//    private val getUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) { GetUserNameUseCase(userRepository = userRepository) }
//    private val getIdSharedPreference = GetIdSharedPreference()
//    private val saveUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) { SaveUserNameUseCase(userRepository = userRepository) }
    val taG = "Pg1MaleFemaleViewModel"

    lateinit var userClass: User
    lateinit var dataUser: DataUser

    private val _resultLive = MutableLiveData<String>()
    val resultLive: LiveData<String> = _resultLive

    private val _man = MutableLiveData<Boolean>()
    val live_man: LiveData<Boolean> = _man

    private val _woman = MutableLiveData<Boolean>()
    val live_woman: LiveData<Boolean> = _woman

    private val _state = MutableLiveData<CoinListState>()
    val state: LiveData<CoinListState> = _state

    private val _mm = MutableLiveData<User>()
    val mm: LiveData<User> = _mm

    private val _userLiveData = MutableLiveData<User?>()
    val userLiveData: LiveData<User?> = _userLiveData

    private val _userResourceLiveData = MutableLiveData<Resource<Any>>()
    val userResourceLiveData: LiveData<Resource<Any>> = _userResourceLiveData

    init {
        Log.e(taG, "init Pg1MaleFemaleViewModel")
//        getCoins()
        Log.e(taG, "init Pg1MaleFemaleViewModel")
    }

    fun save(textv: String) {

//        val params = SaveUserNameParam(email = textv.toString(),
//            idUser = 5)//User(5, "fulname", textv.toString(), "123", 5))
        val params = User(id = 5,
            fullName = textv.toString(),
            email = "em@il",
            password = "123",
            fitness_id = 25)
        val result: Boolean = saveUserSharedPreferenceUseCase.execute(params)
//        val result: Boolean = saveUserNameUseCase.execute(param = params)
        _resultLive.value = "Save $textv = $result"

    }

    fun load() {
        val userName: User = getUserSharedPreferenceUseCase.execute()

        val result: String =
            "Save ${userName.email} = ${userName.password} = ${userName.id} = ${userName.fullName}"
        _resultLive.value = result
    }

    fun queryOfEmaiPassword(email: String, password: String) = viewModelScope.launch {
        Log.e(taG, "inside query")

        safeCallGetUserOfEmailPasswordViewModel(email, password)
    }

    private suspend fun safeCallGetUserOfEmailPasswordViewModel(email: String, password: String) {
//        _newsLiveData.postValue(Resource.Loading())

//        val retroService = RetrofitInstanceModule.getRetroInstance().create(ApiServer::class.java)
        val call =
            apiRepository.getUserOfEmailPasswordRepos(emailQuery = email, passwQuery = password)
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e(taG, "Retrofit 1")
                _userLiveData.postValue(null)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    // получен ответ от сервера после записи данных
                    Log.e(taG, "Retrofit 2 ${response.body()?.id}")
                    response.body().let { res ->
                        _userLiveData.postValue(res)
                    }
                } else {
                    _userLiveData.postValue(null)
                    Log.e(taG, "Retrofit 3")
                }
            }
        })
    }

    fun queryOfId(id: Int) = viewModelScope.launch {
        Log.e(taG, "inside query")

        safeCallGetUserOfId(id)
    }

    suspend fun safeCallGetUserOfId(id: Int) {
        _userResourceLiveData.postValue(Resource.Loading())
        Log.e(taG, "Retrofit 0")
//        val retroService = RetrofitInstanceModule.getRetroInstance().create(ApiServer::class.java)
        val call = apiRepository.getUserOfIdRepos(id = id)
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e(taG, "Retrofit 1")
                _userResourceLiveData.postValue(Resource.Error(t.message.toString()))
                _userLiveData.postValue(null)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                _userResourceLiveData.postValue(Resource.Loading(response))
                Log.e(taG, "Retrofit 2 ${response.body()?.id}")
                if (response.isSuccessful && response.body() != null) {

                    // получен ответ от сервера после записи данных
                    response.body().let { res ->
                        _userLiveData.postValue(res)
                        _userResourceLiveData.postValue(Resource.Success(res))
                    }
                } else {
                    Log.e(taG, "${response.message()} Retrofit 3")
                    _userLiveData.postValue(null)
                    _userResourceLiveData.postValue(Resource.Error("${response.message()} No this data"))
                }
            }
        })
    }

    fun changeimageViewBoy() {
        _man.value = true
        _woman.value = false
        dataUser.man = true
        dataUser.woman = false
        Log.e(taG, "woman ${dataUser.woman}  man    ${dataUser.man}")
    }

    fun changeimageViewGirl() {
        _man.value = false
        _woman.value = true
        dataUser.man = false
        dataUser.woman = true
        Log.e(taG, "woman ${dataUser.woman}  man    ${dataUser.man}")
    }
}