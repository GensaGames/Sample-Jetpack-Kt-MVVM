package sample.settings.gensagames.samplejetpackmvvm.inject

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import org.mockito.Mockito
import sample.settings.gensagames.samplejetpackmvvm.model.SampleContextHelper
import sample.settings.gensagames.samplejetpackmvvm.view.MainActivity
import javax.inject.Singleton


@Module
abstract class TestActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeActivityInjector(): MainActivity

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