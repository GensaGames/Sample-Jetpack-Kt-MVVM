package sample.settings.gensagames.samplejetpackmvvm.inject

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import sample.settings.gensagames.samplejetpackmvvm.model.SampleContextHelper
import sample.settings.gensagames.samplejetpackmvvm.model.net.SampleApi
import sample.settings.gensagames.samplejetpackmvvm.model.net.SampleApi2
import sample.settings.gensagames.samplejetpackmvvm.view.MainActivity
import sample.settings.gensagames.samplejetpackmvvm.view.inject.MainApiModule
import javax.inject.Singleton


@Module
abstract class TestActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeActivityInjector(): MainActivity

    @Module
    companion object {

        @Provides
        @Singleton
        fun provideSampleContextHelper(): SampleContextHelper? {
            return null
        }
    }
}