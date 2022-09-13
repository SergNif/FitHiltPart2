package com.sergnfitness.android.fit.presentation.part2.part2viewModel

import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.ApiRepository
import com.sergnfitness.domain.repository.UserRepository
import com.sergnfitness.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class Part2Fragment1ToUserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val apiRepository: ApiRepository,
) : ViewModel() {

    val taG = "Part2Fragment1ToUserViewModel "

    lateinit var userClass: User
    lateinit var dataUser: DataUser

    private var _startData: MutableLiveData<String> =
        MutableLiveData(userRepository.converStringToData(LocalDateTime.now().plusDays(0).toString()
            .split("T")[0], 1))
    val startData: LiveData<String>
        get() = _startData

    private var _id: MutableLiveData<Int> = MutableLiveData(0)
    val id: LiveData<Int> = _id
    private var _fullName: MutableLiveData<String> = MutableLiveData("fulName")
    val fullName: LiveData<String> = _fullName
    private var _email: MutableLiveData<String> = MutableLiveData("f@f.rt")
    val email: LiveData<String> = _email
    private var _password: MutableLiveData<String> = MutableLiveData("pasw")
    val password: LiveData<String> = _password


    private val _toUserResourceLiveData = MutableLiveData<Resource<Any>>()
    val toUserResourceLiveData: LiveData<Resource<Any>> = _toUserResourceLiveData

    private val _toUserLiveData = MutableLiveData<User>()
    val toUserLiveData: LiveData<User> = _toUserLiveData


    fun saveChangeNamePassword(newPassword: String?, newName: String) {

        if (newPassword != null) {
            if (newPassword != "null" && newPassword.isNotEmpty()) {
                userClass.password = newPassword
                _password.value = newPassword
            } else {
                _password.value = userClass.password
            }
        }
        if (newName != null) {
            if (newName != "null" && newName.isNotEmpty()) {
                userClass.fullName = newName
                _fullName.value = newName
            } else {
                _fullName.value = userClass.fullName
            }
        }
        launchUpdateNamePassword(userClass)
    }


    fun launchUpdateNamePassword(userName: User) = viewModelScope.launch {
        updateNamePassword(userName)
    }

    private suspend fun updateNamePassword(userName: User) {
        _toUserResourceLiveData.postValue(Resource.Loading())
        userName.id?.let {
            apiRepository.updateNamePassword(user_id = it,
                userName = userName)
        }?.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e(taG, "Data Page3 NULL")
                _toUserResourceLiveData.postValue(Resource.Error(t.message.toString()))
                _toUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                _toUserResourceLiveData.postValue(Resource.Loading(response))
                if (response.isSuccessful && response.body() != null) {
                    userClass = response.body()!!
                    Log.e(taG, "Data Page3 IS ${response.body()} ${userClass}")
                    _toUserLiveData.postValue(response.body())
                } else {
                    Log.e(taG, "Data Page3 ELSE ${response.body()}")
                    _toUserResourceLiveData.postValue(Resource.Error("${response.message()} No this data"))
                    _toUserLiveData.postValue(null)
                }
            }
        }
        )
    }

    fun changeName(text: Editable) {
        if (text.toString() != "null" && text.isNotEmpty()) {
            _fullName.value = text.toString()
        }
    }
}