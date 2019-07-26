package sample.settings.gensagames.samplejetpackmvvm.view.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sample.settings.gensagames.samplejetpackmvvm.view.MainActivity
import sample.settings.gensagames.samplejetpackmvvm.view.MainFragment


@Module
abstract class ApplicationModule {

    @ContributesAndroidInjector
    abstract fun contributeActivityInjector(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeFragmentInjector(): MainFragment

}