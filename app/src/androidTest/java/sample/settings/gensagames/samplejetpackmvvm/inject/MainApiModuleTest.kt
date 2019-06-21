package sample.settings.gensagames.samplejetpackmvvm.inject

import sample.settings.gensagames.samplejetpackmvvm.model.net.SampleApi
import sample.settings.gensagames.samplejetpackmvvm.model.net.SampleApi2
import sample.settings.gensagames.samplejetpackmvvm.view.inject.MainApiModule

const val API_TEST_TAG : String = "SampleApi2-Tag-Test"

class MainApiModuleTest : MainApiModule() {

    override fun provideSampleApi2(): SampleApi2 {
        return SampleApi2(API_TEST_TAG)
    }

    fun provideSampleApi(): SampleApi {
        return SampleApi()
    }
}