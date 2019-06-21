package sample.settings.gensagames.samplejetpackmvvm.inject

import dagger.Component
import sample.settings.gensagames.samplejetpackmvvm.MainViewModelTest
import sample.settings.gensagames.samplejetpackmvvm.view.inject.MainApiModule
import sample.settings.gensagames.samplejetpackmvvm.view.inject.BaseComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [MainApiModule::class])
interface TestComponent : BaseComponent {

    fun inject(test: MainViewModelTest)


    @Component.Builder
    interface Builder {
        fun build(): TestComponent

        fun module(module: MainApiModule): Builder
    }
}