package sample.settings.gensagames.samplejetpackmvvm.inject.models

import dagger.Component
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.MainViewModel
import javax.inject.Singleton


@Singleton
@Component(modules = [ModelModule::class])
interface ModelComponent {

    /**
     * Injects required dependencies into the specified BaseViewModel.
     * @param viewModel BaseViewModel in which to inject the dependencies
     */
    fun inject(viewModel: MainViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ModelComponent

        fun module(module: ModelModule): Builder
    }
}