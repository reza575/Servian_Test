package com.moeiny.reza.servian_test.ui.show

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.moeiny.reza.servian_test.AndroidApplication
import com.moeiny.reza.servian_test.R
import com.moeiny.reza.servian_test.data.model.uimodel.ShowPhotoModel
import com.moeiny.reza.servian_test.databinding.ActivityShowBinding
import javax.inject.Inject

class ShowActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityShowBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as AndroidApplication).component.injectShowActivity(this)

        setUpView()

        var albumId = intent.extras?.getString(ALBUMID)
        var photoId = intent.extras?.getString(PHOTOID)
        var thumbNailUrl = intent.extras?.getString(THUMBNAILURL)
        var title = intent.extras?.getString(TITLE)
        var url = intent.extras?.getString(URL)

        if (!photoId.isNullOrEmpty()) {
            var photoShow=ShowPhotoModel(albumId,photoId,thumbNailUrl,title,url)
            mBinding.photoshow=photoShow
        }
    }

    private fun setUpView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_show)
    }

    companion object {
        const val ALBUMID = "albumId"
        const val PHOTOID = "id"
        const val THUMBNAILURL = "thumbNailaurl"
        const val TITLE = "title"
        const val URL = "url"
    }
}
