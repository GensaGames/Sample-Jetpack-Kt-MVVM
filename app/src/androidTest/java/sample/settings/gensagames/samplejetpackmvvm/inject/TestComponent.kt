package sample.settings.gensagames.samplejetpackmvvm.inject

import dagger.Component
import sample.settings.gensagames.samplejetpackmvvm.MainViewModelTest
import sample.settings.gensagames.samplejetpackmvvm.view.inject.MainApiModule
import sample.settings.gensagames.samplejetpackmvvm.view.inject.ViewModelComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [MainApiModuleTest::class])
interface TestComponent : ViewModelComponent {

    fun inject(test: MainViewModelTest)


    @Component.Builder
    interface Builder {
        fun build(): TestComponent

        fun module(module: MainApiModuleTest): Builder
    }
}