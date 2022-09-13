package com.sergnfitness.domain.usecase.putGetUserClass

import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.UserRepository
import javax.inject.Inject

class CreateExemplarClassUserUseCase  @Inject constructor(
    private val userRepository: UserRepository,
    list: MutableList<String>) {


    fun execute(list: MutableList<String>): User {
        val listTagged: MutableMap<String, String> = mutableMapOf()

        val result: User = userRepository.createExemplarClassUserUseRepos(nameOfCreateClass = "User", list =  listTagged) as User
        return result
    }
}