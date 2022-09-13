package com.sergnfitness.android.fit.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.sergnfitness.android.fit.app.App
import com.sergnfitness.android.fit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val KEY_SP = "sp"
const val KEY_CURRENT_THEME = "current_theme"
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    git push -f origin main
    //    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


//    lateinit var vmFactory: MainViewModelFactory
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setNightMode(getCurrentTheme())
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



//        (applicationContext as App).appComponent.inject(this)
//
//        viewModel = ViewModelProvider(this,vmFactory).get(MainViewModel::class.java)


//        val logFragment: LoginFragment = LoginFragment()
//        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(logFragment::class.java.simpleName)
//
//        if (fragment != logFragment){
//        supportFragmentManager.beginTransaction()
//            .add(LoginFragment,"Fragment Login",LoginFragment,LoginFragment::class.java.simpleName)
//
    }
//    fun setNightMode(isNightModeOn: Boolean) {
//        val shardPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
//        val editor = shardPreferences.edit()
//        editor.putBoolean(KEY_CURRENT_THEME, isNightModeOn)
//        editor.apply()
//        if (isNightModeOn) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//        } else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        }
//    }
//    fun getCurrentTheme(): Boolean {
//        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
//        return sharedPreferences.getBoolean(KEY_CURRENT_THEME, false)
//    }
}