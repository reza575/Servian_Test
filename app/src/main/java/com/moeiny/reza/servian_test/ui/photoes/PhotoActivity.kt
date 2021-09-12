package com.moeiny.reza.servian_test.ui.photoes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moeiny.reza.servian_test.AndroidApplication
import com.moeiny.reza.servian_test.databinding.ActivityPhotoBinding
import com.moeiny.reza.servian_test.R
import com.moeiny.reza.servian_test.data.model.uimodel.ShowPhotoModel
import com.moeiny.reza.servian_test.ui.show.ShowActivity
import javax.inject.Inject


class PhotoActivity : AppCompatActivity() {
    lateinit var viewModel: PhotoViewModel
    lateinit var mBinding: ActivityPhotoBinding

    @Inject
    lateinit var viewModelFactory:ViewModelProvider.Factory

    private val adapter: PhotoAdapter by lazy {
        PhotoAdapter(viewModel::onCardClicked)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as AndroidApplication).component.injectPhotoActivity(this)

        setUpView()
        var albumid = intent.extras?.getString(ALBUMID)
        if (!albumid.isNullOrEmpty()) {
            /**
             * find the photoes with the selected albumId in the list
             */
            var photoShow=ShowPhotoModel(albumid,"","","","")
            mBinding.photoshow = photoShow
            viewModel.getAllPhoto(albumid)
        }

        setupLiveData()
    }

    private fun setupLiveData() {

        /**
         * observing the list of rows of API to update data on recycler view after any changing
         */
        viewModel.photoLiveData.observe(this, Observer { photoList ->
            mBinding.photoloadingPanel.visibility =
                if (photoList.isNotEmpty()) View.GONE else View.VISIBLE
            adapter.listPhoto = photoList

        })

        /**
         * observing data fetching from API to update loading state
         */
        viewModel.loadingLiveData.observe(this, Observer {
            mBinding.photoloadingPanel.visibility = if (it) View.VISIBLE else View.GONE
        })

        /**
         * observing for any error during data fetching from API to update error state
         */
        viewModel.errorLiveData.observe(this, Observer {
            Toast.makeText(this, "error on loading Data", Toast.LENGTH_SHORT).show()
        })

        viewModel.showPhotoLiveData.observe(this, Observer { photo ->
            val intent = Intent(this, ShowActivity::class.java)
            intent.putExtra(ShowActivity.ALBUMID, photo.albumId)
            intent.putExtra(ShowActivity.PHOTOID, photo.id)
            intent.putExtra(ShowActivity.THUMBNAILURL, photo.thumbnailUrl)
            intent.putExtra(ShowActivity.TITLE, photo.title)
            intent.putExtra(ShowActivity.URL, photo.url)
            this.startActivity(intent)
        })
    }

    private fun setUpView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo)
        viewModel = viewModelFactory.create(PhotoViewModel::class.java)
        mBinding.photoRecyclerview.adapter = adapter
    }

    companion object {
        const val ALBUMID = "albumId"
    }
}
