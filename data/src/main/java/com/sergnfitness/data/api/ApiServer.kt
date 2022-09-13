package com.sergnfitness.data.api


import com.sergnfitness.domain.models.UserMenuDay
import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.MenuDayList
import com.sergnfitness.domain.models.user.User
import retrofit2.Call
import retrofit2.http.*


interface ApiServer {

    @POST("fit_new_user_hilt/{user_email}")
    fun postDataUser(
        @Path("user_email") user_email:String,
        @Body params: DataUser
    ): Call<DataUser>

    @GET("/get_one_data_user/{user_id}")
    fun getDataUserOfEmail(
        @Path("user_id") user_id: Int
//        @Query("emailQuery")
//        emailQuery: String,
//        @Query("passwQuery")
//        passwQuery: String,
    ): Call<DataUser>




    @POST("new_user/")
    fun newUserOfEmailPassword(
        @Body params: User
    ): Call<User>

    @GET("/fit_get_one_user_email/")
    fun getUserOfEmailPassword(
//        @Path("user_id") id: Int
        @Query("emailQuery")
        emailQuery: String,
        @Query("passwQuery")
        passwQuery: String,
    ): Call<User>

    @GET("/get_one_user/{user_id}")
    fun getUserOfId(
        @Path("user_id") id: Int
//        @Query("emailQuery")
//        emailQuery: String,
//        @Query("passwQuery")
//        passwQuery: String,
    ): Call<User>

    @POST("fit_new_menu_day/{position}/")
    fun postQueryCreateMenuDay(
        @Path("position") position:Int,
//        @Body user: UsersData,
        @Body menuDay: UserMenuDay,

        ): Call<UserMenuDay>
    // *******   CHART  ***********

    @GET("fit_get_menu_string/")
    fun getMenuStrings(
        @Query("userMenuQiery") id: Int,
        @Query("startDataMenu") startDate: String,
        @Query("endDataMenu") endDate: String,

        ): Call<MenuDayList>

    @PATCH("fit_update/{user_id}/")
    fun pathUpdateWeigth(
        @Path("user_id") user_id: Int,
        @Body userData: DataUser,
//        @Query("data") date: Date,
    ): Call<DataUser>
    // *******   CHART  ***********

    @DELETE("fit_delete_one_day_menu/{user_id}/{position}/")
    fun deleteOneMenuDay(
        @Path("user_id") user_id:Int,
        @Path("position") position:Int,
    ): Call<MenuDayList>


    @PATCH("fit_update_to_user/{user_id}/")
    fun updateNamePassword(
        @Path("user_id") user_id: Int,
        @Body userName: User,
//        @Query("data") date: Date,
    ): Call<User>


    //    @GET("/get_one1_user/{user_id}")
//    suspend fun getUserOfId(
//        @Path("user_id") id: Int
////        @Query("emailQuery")
////        emailQuery: String,
////        @Query("passwQuery")
////        passwQuery: String,
//    ): Response<User>


    //    ): Response<MenuDayListStorage>
//        @Query("dataMenu") date: String
//        @Query("userMenuQiery") id: Int,
//    suspend fun getHeadLines(
//    @GET("/fit_get_one_user_email/")
//    suspend fun getUserOfEmailPassword(
////        @Path("user_id") id: Int
//        @Query("emailQuery")
//        emailQuery: String,
//        @Query("passwQuery")
//        passwQuery: String,


}
