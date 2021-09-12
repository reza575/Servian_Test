package com.moeiny.reza.servian_test.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moeiny.reza.servian_test.core.result.Result
import com.moeiny.reza.servian_test.data.model.uimodel.ShowUserModel
import com.moeiny.reza.servian_test.data.userrepository.UserRepository
import javax.inject.Inject


class UsersViewModel @Inject constructor (private val userRepository: UserRepository): ViewModel() {

    val showUsersLiveData = MutableLiveData<String>()

    val usersLiveData = MutableLiveData<List<ShowUserModel>>()

    val loadingLiveData = MutableLiveData<Boolean>()

    val errorLiveData = MutableLiveData<Boolean>()

    fun getAllUsers() {
        userRepository.fetchUsersInfo { result ->
            if (result is Result.Success) {

                /**
                 * Map Users Data from API model to UI Model
                 */
                val userList = result.data.map { users ->
                    ShowUserModel(
                        id = users.id.toString().orEmpty(),
                        name = users.name?.orEmpty(),
                        email = users.email?.orEmpty(),
                        phone = users.phone?.orEmpty()
                    )
                }
                usersLiveData.postValue(userList)
            } else if (result is Result.Loading) {
                loadingLiveData.postValue(true)
            } else if (result is Result.Error) {
                errorLiveData.postValue(true)
            }
        }
    }

    fun onCardClicked(id: String) {
        showUsersLiveData.postValue(id)
    }
}