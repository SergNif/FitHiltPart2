package com.sergnfitness.domain.usecase

import com.sergnfitness.domain.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
class GetUserOfIdApiUseCase @Inject constructor(
    private val repository: ApiRepository,
) {
//    @Provides
//    @Singleton
    suspend fun invoke(id: Int)
         = repository.getUserOfIdRepos(id = id)
}

//suspend fun invoke(id: Int, email: String, password: String): User =
//    repository.getUser(id = id, email = email, password = password)
