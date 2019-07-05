package sample.settings.gensagames.samplejetpackmvvm.viewmodel

import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import sample.settings.gensagames.samplejetpackmvvm.R
import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.utils.TAG
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.base.BaseViewModel

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
        Log.d(TAG, "navigateToInfo: $this")
        onNavigate.onNavigateToInfo()
    }
}