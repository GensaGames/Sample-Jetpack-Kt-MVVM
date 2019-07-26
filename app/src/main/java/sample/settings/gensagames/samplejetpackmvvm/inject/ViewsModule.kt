package sample.settings.gensagames.samplejetpackmvvm.inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sample.settings.gensagames.samplejetpackmvvm.view.DetailFragment
import sample.settings.gensagames.samplejetpackmvvm.view.MainFragment
import sample.settings.gensagames.samplejetpackmvvm.inject.detail.DetailModule


@Module
abstract class ViewsModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragmentInjector(): MainFragment

    @ContributesAndroidInjector(modules = [DetailModule::class])
    abstract fun contributeDetailFragmentInjector(): DetailFragment
}