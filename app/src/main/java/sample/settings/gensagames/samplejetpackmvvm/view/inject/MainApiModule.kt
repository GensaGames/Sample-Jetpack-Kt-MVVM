package sample.settings.gensagames.samplejetpackmvvm.view.inject

import dagger.Module
import dagger.Provides
import sample.settings.gensagames.samplejetpackmvvm.model.net.SampleApi2
import javax.inject.Singleton

@Module
open class MainApiModule {

    @Provides
    @Singleton
    open fun provideSampleApi2(): SampleApi2 {
        return SampleApi2("SampleApi2-Tag")
    }

}