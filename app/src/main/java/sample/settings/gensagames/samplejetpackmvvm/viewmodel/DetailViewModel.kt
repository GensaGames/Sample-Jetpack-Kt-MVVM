package sample.settings.gensagames.samplejetpackmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thedeanda.lorem.LoremIpsum
import sample.settings.gensagames.samplejetpackmvvm.model.dto.HeaderIntroObject
import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.utils.TAG
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.base.BaseViewModel

class DetailViewModel constructor(info: InfoObject) : BaseViewModel() {

    val infoObject: MutableLiveData<InfoObject> = MutableLiveData()

    init {
        Log.d(TAG, "Received InfoObject: $info")
        infoObject.value = info
    }

    val textContent: MutableLiveData<String> = MutableLiveData()
        get() {
            field.value?:let {
                field.value = LoremIpsum
                    .getInstance().getHtmlParagraphs(10, 20)
            }
            return field
        }


}
