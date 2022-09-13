package com.sergnfitness.domain.usecase.putGetUserClass

import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.UserRepository
import javax.inject.Inject

class GetUserClassFromSharedPreferenceUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    val TAG = "GetUserClassFromSharedPreferenceUseCase"
    fun execute(): MutableList<String> {
        return userRepository.getDataUser()
    }
}