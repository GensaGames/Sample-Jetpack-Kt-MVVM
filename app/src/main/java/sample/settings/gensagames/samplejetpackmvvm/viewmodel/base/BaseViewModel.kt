package sample.settings.gensagames.samplejetpackmvvm.viewmodel.base

import androidx.lifecycle.ViewModel
import sample.settings.gensagames.samplejetpackmvvm.view.inject.DaggerViewModelComponent
import sample.settings.gensagames.samplejetpackmvvm.view.inject.MainApiModule
import sample.settings.gensagames.samplejetpackmvvm.view.inject.ViewModelComponent
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.MainViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelComponent = DaggerViewModelComponent
        .builder()
        .module(MainApiModule())
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MainViewModel -> injector.inject(this)
        }
    }
}