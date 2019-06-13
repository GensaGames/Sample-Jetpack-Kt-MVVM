package sample.settings.gensagames.samplejetpackmvvm.view.inject

import dagger.Component
import sample.settings.gensagames.samplejetpackmvvm.view.viewmodel.MainViewModel
import javax.inject.Singleton


@Singleton
@Component(modules = [MainApiModule::class])
interface ViewModelComponent {

    /**
     * Injects required dependencies into the specified BaseViewModel.
     * @param viewModel BaseViewModel in which to inject the dependencies
     */
    fun inject(viewModel: MainViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelComponent

        fun module(module: MainApiModule): Builder
    }
}