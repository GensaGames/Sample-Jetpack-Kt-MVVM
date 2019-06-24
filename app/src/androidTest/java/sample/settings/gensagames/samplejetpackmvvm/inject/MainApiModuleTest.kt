package sample.settings.gensagames.samplejetpackmvvm.inject

import dagger.Module
import dagger.Provides
import sample.settings.gensagames.samplejetpackmvvm.model.net.SampleApi
import sample.settings.gensagames.samplejetpackmvvm.model.net.SampleApi2
import sample.settings.gensagames.samplejetpackmvvm.view.inject.MainApiModule
import javax.inject.Singleton

const val API_TEST_TAG : String = "SampleApi2-Tag-Test"

@Module
class MainApiModuleTest {

    @Provides
    @Singleton
    fun provideSampleApi2(): SampleApi2 {
        return SampleApi2(API_TEST_TAG)
    }

    @Provides
    @Singleton
    fun provideSampleApi(): SampleApi {
        return SampleApi()
    }
}