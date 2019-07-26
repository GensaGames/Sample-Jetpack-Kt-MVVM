package sample.settings.gensagames.samplejetpackmvvm.viewmodel.base

import androidx.lifecycle.ViewModel
import sample.settings.gensagames.samplejetpackmvvm.inject.models.DaggerModelComponent
import sample.settings.gensagames.samplejetpackmvvm.inject.models.ModelModule
import sample.settings.gensagames.samplejetpackmvvm.inject.models.ModelComponent
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.MainViewModel


abstract class BaseViewModel : ViewModel() {

    private val injector: ModelComponent = DaggerModelComponent
        .builder()
        .module(ModelModule())
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