package sample.settings.gensagames.samplejetpackmvvm.viewmodel.base

import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject

interface OnLoadingInfo {

    fun onLoaded(objects : List<InfoObject> )
}