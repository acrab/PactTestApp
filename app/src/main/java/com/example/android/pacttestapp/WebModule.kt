package com.example.android.pacttestapp

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module
class WebModule
{
    @Singleton
    @Provides
    fun providesWikiApiService() = WikiApiService.create()
}

@Module
abstract class ActivityModule
{
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity
}

@Singleton
@Component(modules = [AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    WebModule::class,
    ActivityModule::class])
interface AppComponent
{
    @Component.Builder
    interface Builder {
        // provide Application instance into DI
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app:WebApp)

    fun inject(activity: MainActivity)

}