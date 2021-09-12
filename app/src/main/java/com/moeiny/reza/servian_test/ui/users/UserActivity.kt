package com.moeiny.reza.servian_test.ui.users

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.moeiny.reza.servian_test.AndroidApplication
import com.moeiny.reza.servian_test.R
import androidx.lifecycle.ViewModelProvider
import com.moeiny.reza.servian_test.databinding.ActivityUserBinding
import com.moeiny.reza.servian_test.ui.photoes.PhotoActivity
import javax.inject.Inject


class UserActivity : AppCompatActivity() {

    lateinit var viewModel: UsersViewModel
    lateinit var mBinding: ActivityUserBinding

    @Inject
    lateinit var viewModelFactory:ViewModelProvider.Factory

    private val adapter: UserAdapter by lazy {
        UserAdapter(viewModel::onCardClicked)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as AndroidApplication).component.injectUserActivity(this)
        /**
         * function setUpVies: Assign parameters and values
         */
        setUpView()

        /**
         * SetUp all the livedata parameters to start their job(Observing data)
         */
        setupLiveData()
    }

    private fun setupLiveData() {
        /**
         * observing the list of rows of API to update data on recycler view after any changing
         */
        viewModel.usersLiveData.observe(this, Observer { userList ->
            mBinding.loadingPanel.visibility =
                if (userList.isNotEmpty()) View.GONE else View.VISIBLE
            adapter.listUser = userList
        })

        /**
         * observing data fetching from API to update loading state
         */
        viewModel.loadingLiveData.observe(this, Observer {
            mBinding.loadingPanel.visibility = if (it) View.VISIBLE else View.GONE
        })

        /**
         * observing for any error during data fetching from API to update error state
         */
        viewModel.errorLiveData.observe(this, Observer {
            Toast.makeText(this, "error on loading Data", Toast.LENGTH_SHORT).show()
        })

        viewModel.showUsersLiveData.observe(this, Observer { id ->
            val intent = Intent(this, PhotoActivity::class.java)
            intent.putExtra(PhotoActivity.ALBUMID, id)
            this.startActivity(intent)
        })
    }

    private fun setUpView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user)
        viewModel = viewModelFactory.create(UsersViewModel::class.java)
        viewModel.getAllUsers()
        mBinding.usersRecyclerview.adapter = adapter
    }
}
