package sample.settings.gensagames.samplejetpackmvvm.inject

import dagger.Component
import dagger.android.AndroidInjectionModule
import sample.settings.gensagames.samplejetpackmvvm.MainActivityTest
import sample.settings.gensagames.samplejetpackmvvm.view.inject.ActivityComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, TestActivityModule::class])
interface TestActivityComponent : ActivityComponent {

    fun inject(test: MainActivityTest)
}