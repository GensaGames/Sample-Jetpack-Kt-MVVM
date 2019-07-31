package sample.settings.gensagames.samplejetpackmvvm.inject

import dagger.Component
import dagger.android.AndroidInjectionModule
import sample.settings.gensagames.samplejetpackmvvm.MainFragmentTest
import javax.inject.Singleton

/**
 * It makes no sense to mock injected object only for one element in Activity,
 * However, this is just an example for using it here.
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class,
    TestViewModule::class, VmFactoriesModule::class])
interface TestApplicationComponent : ApplicationComponent {

    fun inject(test: MainFragmentTest)
}