package com.moeiny.reza.servian_test.ui.photoes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moeiny.reza.servian_test.core.result.Result
import com.moeiny.reza.servian_test.data.model.uimodel.ShowPhotoModel
import com.moeiny.reza.servian_test.data.photorepository.PhotoRepository
import javax.inject.Inject

class PhotoViewModel @Inject constructor(private val photoRepository: PhotoRepository) : ViewModel() {

    val showPhotoLiveData = MutableLiveData<ShowPhotoModel>()

    val photoLiveData = MutableLiveData<List<ShowPhotoModel>>()

    val loadingLiveData = MutableLiveData<Boolean>()

    val errorLiveData = MutableLiveData<Boolean>()

    fun getAllPhoto(albumid:String) {
        photoRepository.fetchPhotoInfo { result ->
            if (result is Result.Success) {
                val selectedId =
                    result.data.filter { it.albumId == albumid.toInt() }

                /**
                 * Map Photo Data from API model to UI Model
                 */
                val photoList = selectedId.map { photo ->
                    ShowPhotoModel(
                        albumId = photo.albumId.toString().orEmpty(),
                        id = photo.id?.toString().orEmpty(),
                        thumbnailUrl = photo.thumbnailUrl?.orEmpty(),
                        title = photo.title?.orEmpty(),
                        url = photo.url?.orEmpty()
                    )

                }
                photoLiveData.postValue(photoList)
            } else if (result is Result.Loading) {
                loadingLiveData.postValue(true)
            } else if (result is Result.Error) {
                errorLiveData.postValue(true)
            }
        }
    }

    fun onCardClicked(photo: ShowPhotoModel) {
        showPhotoLiveData.postValue(photo)
    }
}