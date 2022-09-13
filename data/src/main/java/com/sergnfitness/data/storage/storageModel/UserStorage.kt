package com.sergnfitness.data.storage.storageModel

import com.sergnfitness.domain.models.user.User


data class UserStorage(

    var id: Int? = null,
    var fullName: String? = null,
    var email: String? = null,
    var password: String? = null,
    var fitness_id: Int? = null,
)

fun UserStorage.toUser(): User{
    return User(
    id = id,
    fullName = fullName,
    email = email,
    password = password,
    fitness_id = fitness_id,
    )
}