package com.sergnfitness.android.model

import com.sergnfitness.domain.models.user.User

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: String ="",
    val error: String = ""
)