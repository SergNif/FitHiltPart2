package com.sergnfitness.data.extensions

import com.sergnfitness.data.storage.storageModel.UserStorage
import com.sergnfitness.domain.models.user.User


fun UserStorage.toUser(): User {
    return User(
        id,   fullName,   email,    password,    fitness_id
    )
}