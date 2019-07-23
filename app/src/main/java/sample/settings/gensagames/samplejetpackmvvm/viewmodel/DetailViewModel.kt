package sample.settings.gensagames.samplejetpackmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thedeanda.lorem.LoremIpsum
import sample.settings.gensagames.samplejetpackmvvm.model.dto.HeaderIntroObject
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.base.BaseViewModel

class DetailViewModel : BaseViewModel() {

    val textContent: MutableLiveData<String> = MutableLiveData()
        get() {
            field.value?:let {
                field.value = LoremIpsum
                    .getInstance().getHtmlParagraphs(100, 200)
            }
            return field
        }
}
