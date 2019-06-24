package sample.settings.gensagames.samplejetpackmvvm.view.inject

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import sample.settings.gensagames.samplejetpackmvvm.view.MainApplication
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class, MainActivityModule::class])
interface ActivityComponent : AndroidInjector<MainApplication> {

}