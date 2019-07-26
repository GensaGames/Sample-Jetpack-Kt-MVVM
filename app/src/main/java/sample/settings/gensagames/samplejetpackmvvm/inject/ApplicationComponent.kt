package sample.settings.gensagames.samplejetpackmvvm.inject

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import sample.settings.gensagames.samplejetpackmvvm.view.base.MainApplication
import javax.inject.Singleton
import android.app.Application
import dagger.BindsInstance
import sample.settings.gensagames.samplejetpackmvvm.inject.detail.DetailModule


@Singleton
@Component(modules = [AndroidInjectionModule::class,
    ViewsModule::class, VmFactoriesModule::class])
interface ApplicationComponent : AndroidInjector<MainApplication> {

}