package sample.settings.gensagames.samplejetpackmvvm.inject

import dagger.Component
import sample.settings.gensagames.samplejetpackmvvm.MainActivityTest
import sample.settings.gensagames.samplejetpackmvvm.view.inject.ViewModelComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [MainApiModuleTest::class])
interface TestComponent : ViewModelComponent {

    fun inject(test: MainActivityTest)


    @Component.Builder
    interface Builder {
        fun build(): TestComponent

        fun module(module: MainApiModuleTest): Builder
    }
}