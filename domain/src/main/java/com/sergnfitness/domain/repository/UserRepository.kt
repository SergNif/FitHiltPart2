package com.sergnfitness.domain.repository

import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.user.SaveUserNameParam
import com.sergnfitness.domain.models.user.User
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

interface UserRepository {
//    fun saveName(saveparam: SaveUserNameParam): Boolean
//    fun getUserSharedPref(): User
//    fun getId(): Int
    fun saveUser(saveParam: User):Boolean
    fun getUser():User

    fun saveDataUser(user: User):Boolean
    fun getDataUser(): MutableList<String>
//    fun createExemplarClassDataUserStorageUseRepos(list: MutableList<String>): DataUser
    fun createExemplarClassUserUseRepos(nameOfCreateClass:String, list: MutableMap<String, String>): Any
    fun saveDataStartDataCalendar(startData: String, endData: String): Boolean
    fun converStringToData(dt: String, i: Long): String?

}
