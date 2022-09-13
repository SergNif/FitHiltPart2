package com.sergnfitness.domain.repository

interface UserRepositoryCompanion {
    fun converStringToData(dt: String, i: Long): String?
}