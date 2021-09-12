package com.moeiny.reza.servian_test.core.di.viewmodel

import androidx.lifecycle.ViewModel
import com.moeiny.reza.servian_test.ui.photoes.PhotoViewModel
import com.moeiny.reza.servian_test.ui.users.UsersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class BindViewModels {
    @Binds
    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    abstract fun bindUserViewModel(viewModel: UsersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PhotoViewModel::class)
    abstract fun bindPhotoViewModel(viewModel: PhotoViewModel): ViewModel
}