package com.moeiny.reza.servian_test.data.userrepository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.moeiny.reza.servian_test.core.result.Result
import com.moeiny.reza.servian_test.data.model.apimodel.user.UserModel
import com.moeiny.reza.servian_test.data.retrofit.ApiService
import javax.inject.Inject

interface UserRepository {
    fun fetchUsersInfo(onResult: (result: Result<List<UserModel>>) -> Unit)
}

class UserRepositoryDefault @Inject constructor(private val apiService: ApiService) : UserRepository{
    override fun fetchUsersInfo(onResult: (result: Result<List<UserModel>>) -> Unit) {

        onResult(Result.Loading)

        apiService.getUsersInfo().enqueue(object :Callback<List<UserModel>>{
            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                onResult(Result.Error(t))
            }

            override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>) {
                response.body()?.let {
                    onResult(Result.Success(it))
                }
            }
        })
    }
}

