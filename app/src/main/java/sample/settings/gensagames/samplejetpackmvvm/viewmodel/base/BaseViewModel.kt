package sample.settings.gensagames.samplejetpackmvvm.viewmodel.base

import androidx.lifecycle.ViewModel
import sample.settings.gensagames.samplejetpackmvvm.view.inject.DaggerViewModelComponent
import sample.settings.gensagames.samplejetpackmvvm.view.inject.MainApiModule
import sample.settings.gensagames.samplejetpackmvvm.view.inject.ViewModelComponent
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.MainViewModel
import android.app.Application
import androidx.lifecycle.ViewModelProvider
import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.DetailViewModel


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


    class Factory(vararg params: Any) :
        ViewModelProvider.NewInstanceFactory() {
        private val params: Array<*> = params

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return when (modelClass) {
                DetailViewModel::class.java ->
                    DetailViewModel(params[0] as InfoObject) as T
                else -> super.create(modelClass)
            }
        }
    }
}