package sample.settings.gensagames.samplejetpackmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.base.BaseViewModel
import timber.log.Timber

class AdapterViewModel : BaseViewModel() {

    interface OnNavigate {
        fun onNavigateToInfo()
    }

    val infoObject : MutableLiveData<InfoObject> = MutableLiveData()
    lateinit var onNavigate : OnNavigate

    fun bind(info: InfoObject, onNav : OnNavigate){
        infoObject.value = info
        onNavigate = onNav
    }

    fun navigateToInfo() {
        Timber.d( "navigateToInfo: $this")
        onNavigate.onNavigateToInfo()
    }
}