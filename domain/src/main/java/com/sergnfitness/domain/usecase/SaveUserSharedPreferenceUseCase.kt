package com.sergnfitness.domain.usecase

import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserSharedPreferenceUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    val TAG = "SaveUserSharedPreference"
    fun execute(param: User): Boolean {
        if (param.fullName.isNullOrBlank()) {
            return false
        } else {
            val result: Boolean = userRepository.saveUser(saveParam = param)
            return result
        }
    }
    fun executeSaveData(startData: String ="", endData:String =""):Boolean{
        val result: Boolean = userRepository.saveDataStartDataCalendar(startData = startData, endData = endData)
        return result
    }
}