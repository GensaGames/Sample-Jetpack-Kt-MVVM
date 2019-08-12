package sample.settings.gensagames.samplejetpackmvvm.view.adapter

import com.facebook.litho.annotations.Event
import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject


@Event
class AdapterItemClickEvent {
    lateinit var info : InfoObject
}