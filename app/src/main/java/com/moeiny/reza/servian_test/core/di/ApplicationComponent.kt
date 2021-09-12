package com.moeiny.reza.servian_test.core.di

import com.moeiny.reza.servian_test.core.di.viewmodel.BindViewModels
import com.moeiny.reza.servian_test.core.di.viewmodel.ViewModelModule
import com.moeiny.reza.servian_test.ui.photoes.PhotoActivity
import com.moeiny.reza.servian_test.ui.show.ShowActivity
import com.moeiny.reza.servian_test.ui.users.UserActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ViewModelModule::class,
        BindViewModels::class
    ]
)
interface ApplicationComponent {
    fun injectUserActivity(activity: UserActivity)
    fun injectPhotoActivity(activity: PhotoActivity)
    fun injectShowActivity(activity: ShowActivity)
}