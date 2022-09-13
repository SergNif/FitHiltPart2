package com.sergnfitness.cleanarchitect.data.storage

import android.content.Context
import com.sergnfitness.data.storage.SharedPrefsInterfaceStorage
import com.sergnfitness.data.storage.storageModel.DataUserStorage
import com.sergnfitness.data.storage.storageModel.UserStorage
import javax.inject.Inject


private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_USER_ID = "USER_ID"
private const val KEY_USER_FULLNAME = "USER_FULLNAME"
private const val KEY_USER_EMAIL = "USER_EMAIL"
private const val KEY_USER_PASSWORD = "USER_PASSWORD"
private const val KEY_USER_FITNESS_ID = "USER_FITNESS_ID"

private const val DEFAULT_CURRENT = "default"
private const val DEFAULT_KEY_USER_ID = "85000"
private const val DEFAULT_KEY_USER_FULLNAME = "TestUser"
private const val DEFAULT_KEY_USER_EMAIL = "TestUser"
private const val DEFAULT_KEY_USER_PASSWORD = "TestUser"
private const val DEFAULT_KEY_USER_FITNESS_ID = "85003"
private const val KEY_LAST_NAME = "Repository Impl"
private const val DEFAULT_LAST_NAME = "Default last name"
private const val DEFAULT_FIRST_NAME = "Default first name"

private const val KEY_STARTDATA = "Start Data name"
private const val KEY_ENDDATA = "End Data name"


//@Module
//@InstallIn(SingletonComponent::class)
class SharedPrefsImplStorage @Inject constructor(context: Context) : SharedPrefsInterfaceStorage {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveUser(user: UserStorage): Boolean {
        user.id?.let { sharedPreferences.edit().putInt(KEY_USER_ID, it).apply() }
        sharedPreferences.edit().putString(KEY_USER_FULLNAME, user.fullName.toString()).apply()
        sharedPreferences.edit().putString(KEY_USER_EMAIL, user.email.toString()).apply()
        sharedPreferences.edit().putString(KEY_USER_PASSWORD, user.password.toString()).apply()
        user.fitness_id?.let { sharedPreferences.edit().putInt(KEY_USER_FITNESS_ID, it).apply() }
        return true
    }

    override fun getUser(): UserStorage {
        return UserStorage(
            id = sharedPreferences.getInt(KEY_USER_ID, DEFAULT_KEY_USER_ID.toInt())
                ?: DEFAULT_KEY_USER_ID.toInt(),
            fullName = sharedPreferences.getString(KEY_USER_FULLNAME, DEFAULT_KEY_USER_FULLNAME)
                ?: DEFAULT_KEY_USER_FULLNAME,
            email = sharedPreferences.getString(KEY_USER_EMAIL, DEFAULT_KEY_USER_EMAIL)
                ?: DEFAULT_KEY_USER_EMAIL,
            password = sharedPreferences.getString(KEY_USER_PASSWORD, DEFAULT_KEY_USER_PASSWORD)
                ?: DEFAULT_KEY_USER_PASSWORD,
            fitness_id = sharedPreferences.getInt(KEY_USER_FITNESS_ID,
                DEFAULT_KEY_USER_FITNESS_ID.toInt()) ?: DEFAULT_KEY_USER_FITNESS_ID.toInt(),

            )
    }


    override fun saveUserClass(user: UserStorage): Boolean {
        // user.javaClass.simpleName - выдаёт имя класса user
        for (jj in user.javaClass.declaredFields) {

            user.javaClass.getDeclaredField(jj.name).let {
                sharedPreferences.edit().putString(jj.name.toString(), jj.toString()).apply()
            }
            println(jj.name)
        }
        return true
    }

    override fun getUserClass(): MutableList<String> {

        lateinit var list: MutableList<String>
        UserStorage::class.java.declaredFields.forEach() { member ->
//            user.javaClass.getDeclaredField(member.name)
            list.add(sharedPreferences.getString(member.name, DEFAULT_CURRENT) ?: DEFAULT_CURRENT)
            println("${member.name}")
        }
        return list
    }

    override fun saveDataUserClass(user: DataUserStorage): Boolean {
        // user.javaClass.simpleName - выдаёт имя класса user
        for (jj in user.javaClass.declaredFields) {

            user.javaClass.getDeclaredField(jj.name).let {
                sharedPreferences.edit().putString(jj.name.toString(), jj.toString()).apply()
            }
            println(jj.name)
        }
        return true
    }

    override fun getDataUserClass(): MutableList<String> {
        lateinit var list: MutableList<String>
        DataUserStorage::class.java.declaredFields.forEach() { member ->
//            user.javaClass.getDeclaredField(member.name)
            list.add(sharedPreferences.getString(member.name, DEFAULT_CURRENT) ?: DEFAULT_CURRENT)
            println("${member.name}")
        }
        return list
    }

    override fun saveData(startData: String, endData: String): Boolean {
        sharedPreferences.edit().putString(KEY_STARTDATA, startData).apply()
        sharedPreferences.edit().putString(KEY_ENDDATA, endData).apply()
        return true
    }

}