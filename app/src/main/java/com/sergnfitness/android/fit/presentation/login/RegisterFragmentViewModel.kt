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
class RegisterFragmentViewModel @Inject constructor(
    private val getUserSharedPreferenceUseCase: GetUserSharedPreferenceUseCase,
    private val saveUserSharedPreferenceUseCase: SaveUserSharedPreferenceUseCase,
    private val apiRepository: ApiRepository,
    private val userRepository: UserRepository,
) : ViewModel() {

    val TAG = "RegisterFragmentViewModel"

    private val _userResourceLiveData = MutableLiveData<Resource<Any>>()
    val userResourceLiveData: LiveData<Resource<Any>> = _userResourceLiveData
    var dataUser: DataUser = DataUser()
    var userClass: User = User()

    fun isNewUser(): Boolean {
        return getUserSharedPreferenceUseCase.execute().id == 85000
    }


    private val _dataUserLiveData = MutableLiveData<DataUser?>()
    val dataUserLiveData: LiveData<DataUser?> = _dataUserLiveData


    private val _dataUserResourceLiveData = MutableLiveData<Resource<Any>>()
    val dataUserResourceLiveData: LiveData<Resource<Any>> = _dataUserResourceLiveData


    fun registerUserOfEmaiPassword(fullName: String, email: String, password: String) =
        viewModelScope.launch {
            Log.e(TAG, "inside query  ")
//            val listTagged: MutableList<String> = mutableListOf()
            val listTagged = mutableMapOf("fullName" to fullName, "email" to email, "password" to password)
//            var user = userRepository.createExemplarClassUserUseRepos(nameOfCreateClass = "com.sergnfitness.domain.models.user.User", list =  listTagged) as User
           var user = User()
            user.fullName = fullName
            user.email = email
            user.password = password
            safeCallRegisterNewUserOfEmailPasswordViewModel(user)
        }

    suspend fun safeCallRegisterNewUserOfEmailPasswordViewModel(
        user: User,
    ) {
        _userResourceLiveData.postValue(Resource.Loading())

//        val retroService = RetrofitInstanceModule.getRetroInstance().create(ApiServer::class.java)
        val call = apiRepository.saveNewUserOfEmailPasswordRepos(user)
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e(TAG, "Retrofit 1")
                _userResourceLiveData.postValue(Resource.Error(t.message.toString()))
//                _userLiveData.postValue(null)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                _userResourceLiveData.postValue(Resource.Loading(response))
                Log.e(TAG, "Retrofit 2 ${response.body()?.id}")
                if (response.isSuccessful && response.body() != null) {

                    // получен ответ от сервера после записи данных
                    response.body().let { res ->
//                        _userLiveData.postValue(res)
                        _userResourceLiveData.postValue(Resource.Success(res))
                    }
                } else {
                    Log.e(TAG, "${response.message()} Retrofit 3")
//                    _userLiveData.postValue(null)
                    _userResourceLiveData.postValue(Resource.Error("${response.message()} No this data"))
                }
            }
        })


    }


    fun createDataUserOnServer(email: String)  = viewModelScope.launch {
        Log.e(TAG, "inside query")

        safeCallPostDataUserOfEmailViewModel(email)
    }

    private suspend fun safeCallPostDataUserOfEmailViewModel(email: String) {
        _dataUserResourceLiveData.postValue(Resource.Loading())

//        val retroService = RetrofitInstanceModule.getRetroInstance().create(ApiServer::class.java)
        val call =  apiRepository.postQueryCreateUserRepos(email = email, user = dataUser)
        call.enqueue(object : Callback<DataUser> {
            override fun onFailure(call: Call<DataUser>, t: Throwable) {
                Log.e(TAG, "Retrofit 1")
                _dataUserResourceLiveData.postValue(Resource.Error(t.message.toString()))
                _dataUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<DataUser>, response: Response<DataUser>) {
                _dataUserResourceLiveData.postValue(Resource.Loading(response))
                Log.e(TAG, "Retrofit 2 ${response.body()?.id}")
                if (response.isSuccessful && response.body() != null) {

                    // получен ответ от сервера после записи данных
                    response.body().let { res ->
                        _dataUserLiveData.postValue(res)
                        _dataUserResourceLiveData.postValue(Resource.Success(res))
                    }
                } else {
                    Log.e(TAG, "${response.message()} Retrofit 3")
                    _dataUserLiveData.postValue(null)
                    _dataUserResourceLiveData.postValue(Resource.Error("${response.message()} No this data"))
                }
            }
        })


    }


    fun saveUserToSharedPref(it: User) {
        userRepository.saveDataUser(it)
    }

}