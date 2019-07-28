package sample.settings.gensagames.samplejetpackmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import com.thedeanda.lorem.LoremIpsum
import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.base.BaseViewModel
import javax.inject.Inject

class DetailViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var infoObjectNew : InfoObject

    val infoObject: MutableLiveData<InfoObject> = MutableLiveData()
        get() {
            field.value?:let {
                field.value = infoObjectNew
            }
            return field
        }


    val textContent: MutableLiveData<String> = MutableLiveData()
        get() {
            field.value?:let {
                field.value = LoremIpsum
                    .getInstance().getWords(50, 100)
            }
            return field
        }


}
