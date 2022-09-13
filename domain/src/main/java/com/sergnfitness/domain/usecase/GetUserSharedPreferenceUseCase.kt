package com.sergnfitness.domain.usecase

import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.UserRepository
import javax.inject.Inject

class GetUserSharedPreferenceUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    val TAG = "GetUserSharedPreference"
    fun execute(): User {
        val user: User = userRepository.getUser()
        return user
    }
}