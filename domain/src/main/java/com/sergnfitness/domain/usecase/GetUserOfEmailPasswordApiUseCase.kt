package com.sergnfitness.domain.usecase

import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.ApiRepository
import com.sergnfitness.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetUserOfEmailPasswordApiUseCase @Inject constructor(
    private val repository: ApiRepository,
) {
    suspend fun invoke(emailQuery: String, passwQuery: String) =
        repository.getUserOfEmailPasswordRepos(emailQuery = emailQuery, passwQuery = passwQuery)
}
