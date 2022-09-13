package com.sergnfitness.domain.usecase.putGetUserClass

import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.repository.UserRepository
import dagger.hilt.InstallIn
import javax.inject.Inject

class CreateExemplarClassDataUserStorageUseCase @Inject constructor(
    private val userRepository: UserRepository,
    list: MutableList<String>) {


    fun execute(list: MutableList<String>): DataUser {
        val listTagged: MutableMap<String, String> = mutableMapOf()

        val result: DataUser = userRepository.createExemplarClassUserUseRepos(nameOfCreateClass = "DataUserStorage", list = listTagged) as DataUser
        return result
    }
}