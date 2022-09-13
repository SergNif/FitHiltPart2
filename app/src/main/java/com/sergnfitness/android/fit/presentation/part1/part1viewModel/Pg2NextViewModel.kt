package com.sergnfitness.android.fit.presentation.part1.part1viewModel

import androidx.lifecycle.ViewModel
import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Pg2NextViewModel @Inject constructor():ViewModel() {
    var userClass: User = User()
    var dataUser: DataUser = DataUser()

    val taG = "Pg2NextViewModel"
}