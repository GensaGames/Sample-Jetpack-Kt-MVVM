package sample.settings.gensagames.samplejetpackmvvm.inject

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import sample.settings.gensagames.samplejetpackmvvm.view.base.BaseApplication
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class,
    ViewsModule::class, VmFactoriesModule::class])
interface ApplicationComponent : AndroidInjector<BaseApplication> {

}