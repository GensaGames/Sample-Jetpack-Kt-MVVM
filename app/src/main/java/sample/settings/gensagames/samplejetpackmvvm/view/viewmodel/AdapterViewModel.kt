package sample.settings.gensagames.samplejetpackmvvm.view.viewmodel

import androidx.lifecycle.MutableLiveData
import sample.settings.gensagames.samplejetpackmvvm.model.`object`.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.view.viewmodel.base.BaseViewModel

class AdapterViewModel : BaseViewModel() {

    val header = MutableLiveData<String>()
    val contact = MutableLiveData<String>()
    val summary = MutableLiveData<String>()

    fun bind(info: InfoObject){
        header.value = info.name
        contact.value = info.contact
        summary.value = info.summary
    }
}