package com.sergnfitness.android.fit.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.ApiRepository
import com.sergnfitness.domain.repository.UserRepository
import com.sergnfitness.domain.usecase.*
import com.sergnfitness.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val gGetUserOfEmailPasswordApiUseCase: GetUserOfEmailPasswordApiUseCase,
    private val getUserOfIdApiUseCase: GetUserOfIdApiUseCase,
    private val apiRepository: ApiRepository,
    private val userRepository: UserRepository,
    private val getUserSharedPreferenceUseCase: GetUserSharedPreferenceUseCase,
    private val saveUserSharedPreferenceUseCase: SaveUserSharedPreferenceUseCase,
) : ViewModel() {

    val taG = "LoginFragmentViewModel"

    var dataUser: DataUser = DataUser()
    var user: User = User()

    private val _resultLive = MutableLiveData<String>()
    val resultLive: LiveData<String> = _resultLive

    private val _userLiveData = MutableLiveData<User?>()
    val userLiveData: LiveData<User?> = _userLiveData

    private val _dataUserLiveData = MutableLiveData<DataUser?>()
    val dataUserLiveData: LiveData<DataUser?> = _dataUserLiveData

    private val _userResourceLiveData = MutableLiveData<Resource<Any>>()
    val userResourceLiveData: LiveData<Resource<Any>> = _userResourceLiveData

    private val _dataUserResourceLiveData = MutableLiveData<Resource<Any>>()
    val dataUserResourceLiveData: LiveData<Resource<Any>> = _dataUserResourceLiveData


    fun isNewUser(): Boolean {
        return getUserSharedPreferenceUseCase.execute().id == 85000
    }


    //************  GET USER classs
    fun queryOfEmaiPassword(email: String, password: String) = viewModelScope.launch {
        Log.e(taG, "inside query")

        safeCallGetUserOfEmailPasswordViewModel(email, password)
    }

    suspend fun safeCallGetUserOfEmailPasswordViewModel(email: String, password: String) {
        _userResourceLiveData.postValue(Resource.Loading())

//        val retroService = RetrofitInstanceModule.getRetroInstance().create(ApiServer::class.java)
        val call =  apiRepository.getUserOfEmailPasswordRepos(emailQuery = email, passwQuery = password)
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
    //*********************   GET USER class


    //***************  GET DataUSER  **********
    fun queryOfEmaiPasswordDataUser(id:Int)//email: String)//, password: String)
    = viewModelScope.launch {
        Log.e(taG, "inside query")

        safeCallGetDataUserOfEmailPasswordViewModel(id)//email)//, password)
    }

    suspend fun safeCallGetDataUserOfEmailPasswordViewModel(idQuery:Int){//email: String){//, password: String) {
        _dataUserResourceLiveData.postValue(Resource.Loading())

//        val retroService = RetrofitInstanceModule.getRetroInstance().create(ApiServer::class.java)
        val call =  apiRepository.getDataUserOfEmailPasswordRepos(idQuery = idQuery)// emailQuery = email)//, passwQuery = password)
        call.enqueue(object : Callback<DataUser> {
            override fun onFailure(call: Call<DataUser>, t: Throwable) {
                Log.e(taG, "Retrofit 1")
                _dataUserResourceLiveData.postValue(Resource.Error(t.message.toString()))
                _userLiveData.postValue(null)
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
    //***************  GET DataUSER  **********





    fun saveUserToSharedPref(it: User) {
        userRepository.saveDataUser(it)
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