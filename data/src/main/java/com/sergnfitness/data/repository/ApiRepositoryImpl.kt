package com.sergnfitness.data.repository

import android.app.Application
import android.util.Log
import com.sergnfitness.data.api.ApiServer
import com.sergnfitness.domain.models.UserMenuDay
import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.MenuDayList
import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.ApiRepository
import retrofit2.Call
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val api: ApiServer,
    appContext: Application,
) : ApiRepository {

    init {
        val appName =
            appContext.getString(androidx.core.R.string.status_bar_notification_info_overflow)
        Log.e("ApiRepositoryImpl", "Heloo from ApiRepositoryImpl $appName")
    }

    override suspend fun getUserOfIdRepos(id: Int): Call<User> {
        return api.getUserOfId(id = id)//, emailQuery = email, passwQuery = password) //.body().toUser()
    }

    override suspend fun getUserOfEmailPasswordRepos(
        emailQuery: String,
        passwQuery: String,
    ): Call<User> {
        return api.getUserOfEmailPassword(emailQuery = emailQuery,
            passwQuery = passwQuery)//, emailQuery = email, passwQuery = password) //.body().toUser()
    }
    override suspend fun getDataUserOfEmailPasswordRepos(
//        emailQuery: String,
//        passwQuery: String,
    idQuery: Int,
    ): Call<DataUser> {
        return api.getDataUserOfEmail(
            user_id = idQuery,
//            user_email = emailQuery,
//            passwQuery = passwQuery
        )//, emailQuery = email, passwQuery = password) //.body().toUser()
    }


    override suspend fun saveNewUserOfEmailPasswordRepos(
        user: User,
    ): Call<User> {
        return api.newUserOfEmailPassword(user)//, emailQuery = email, passwQuery = password) //.body().toUser()
    }

    override suspend fun postQueryCreateUserRepos(
        email:String,
        user: DataUser,
    ): Call<DataUser> {
        return api.postDataUser(user_email = email, params = user)//, emailQuery = email, passwQuery = password) //.body().toUser()
    }

    override suspend fun getMenuDayStrings(
        id: Int,
        startDate: String,
        endDate: String
    ): Call<MenuDayList> {
        return api.getMenuStrings(
            id  = id,
            startDate = startDate,
            endDate = endDate)
    }

    override suspend fun pathUpdateWeigth(
        user_id:Int,
        userData: DataUser):Call<DataUser>{
        return api.pathUpdateWeigth(
            user_id = user_id,
            userData = userData
        )
    }

    override suspend fun postQueryCreateMenuDay(
        menuDay: UserMenuDay,
        position:Int): Call<UserMenuDay>{
        return api.postQueryCreateMenuDay(
            menuDay = menuDay,
            position = position
        )
    }
   override suspend fun deleteOneMenuDay(
       user_id:Int,
       position:Int): Call<MenuDayList>{
       return api.deleteOneMenuDay(
           user_id = user_id,
           position = position
       )
   }

    override suspend fun updateNamePassword(
        user_id: Int,
        userName: User,
    ): Call<User>{
        return api.updateNamePassword(
            user_id = user_id,
            userName = userName
        )
    }

//    suspend fun getMenu() = api.getHeadLines(5, "28-05-2022" )
}
