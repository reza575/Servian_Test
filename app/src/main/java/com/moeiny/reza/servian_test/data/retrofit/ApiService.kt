package com.moeiny.reza.servian_test.data.retrofit


import com.moeiny.reza.servian_test.data.model.apimodel.PhotoModel
import com.moeiny.reza.servian_test.data.model.apimodel.user.UserModel
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // Communicates responses from a server are executed on the background thread which performed the request
    @GET("users")
    fun getUsersInfo():Call<List<UserModel>>

    @GET("photos")
    fun getPhotosInfo():Call<List<PhotoModel>>
}