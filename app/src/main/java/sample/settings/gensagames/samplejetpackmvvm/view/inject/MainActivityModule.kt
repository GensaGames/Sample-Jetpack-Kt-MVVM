package sample.settings.gensagames.samplejetpackmvvm.view.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sample.settings.gensagames.samplejetpackmvvm.view.MainActivity


@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeActivityInjector(): MainActivity

}