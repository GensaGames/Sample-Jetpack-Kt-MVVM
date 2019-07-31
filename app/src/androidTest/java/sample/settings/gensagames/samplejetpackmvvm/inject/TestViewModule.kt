package sample.settings.gensagames.samplejetpackmvvm.inject

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import org.mockito.Mockito
import sample.settings.gensagames.samplejetpackmvvm.inject.detail.DetailModule
import sample.settings.gensagames.samplejetpackmvvm.model.SampleContextHelper
import sample.settings.gensagames.samplejetpackmvvm.view.DetailFragment
import sample.settings.gensagames.samplejetpackmvvm.view.MainActivity
import sample.settings.gensagames.samplejetpackmvvm.view.MainFragment
import javax.inject.Singleton


@Module
abstract class TestViewModule {

    @ContributesAndroidInjector
    abstract fun contributeMainInjector(): MainFragment

    @ContributesAndroidInjector(modules = [DetailModule::class])
    abstract fun contributeDetailInjector(): DetailFragment

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Singleton
        fun provideSampleContextHelper(): SampleContextHelper {
            return Mockito.mock(SampleContextHelper::class.java)
        }
    }
}