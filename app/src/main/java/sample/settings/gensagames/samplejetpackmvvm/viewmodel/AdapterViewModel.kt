package sample.settings.gensagames.samplejetpackmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import sample.settings.gensagames.samplejetpackmvvm.model.`object`.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.base.BaseViewModel

class AdapterViewModel : BaseViewModel() {

    val header = MutableLiveData<String>()
    val contact = MutableLiveData<String>()
    val summary = MutableLiveData<String>()
    val imageUrl = MutableLiveData<String>()

    fun bind(info: InfoObject){
        header.value = info.name
        contact.value = info.contact
        summary.value = info.summary
        imageUrl.value = info.imageUrl
    }
}