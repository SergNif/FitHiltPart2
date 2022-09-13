package com.sergnfitness.android.fit.presentation.activity

import androidx.lifecycle.ViewModel
import com.sergnfitness.domain.usecase.GetUserSharedPreferenceUseCase
import com.sergnfitness.domain.usecase.SaveUserSharedPreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserSharedPreferenceUseCase: GetUserSharedPreferenceUseCase,
    private val saveUserSharedPreferenceUseCase: SaveUserSharedPreferenceUseCase,
): ViewModel() {

fun start(){

    if (getUserSharedPreferenceUseCase.execute().id == 85000) {
        val rt = 8
//        val logFragment: LoginFragment = LoginFragment()
//        val fragment:Fragment
//
//        supportFragmentManager.find

    }
}

}