package sample.settings.gensagames.samplejetpackmvvm.view.viewmodel.base

import androidx.lifecycle.ViewModel
import sample.settings.gensagames.samplejetpackmvvm.view.inject.DaggerBaseComponent
import sample.settings.gensagames.samplejetpackmvvm.view.inject.MainApiModule
import sample.settings.gensagames.samplejetpackmvvm.view.inject.BaseComponent
import sample.settings.gensagames.samplejetpackmvvm.view.viewmodel.MainViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: BaseComponent = DaggerBaseComponent
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