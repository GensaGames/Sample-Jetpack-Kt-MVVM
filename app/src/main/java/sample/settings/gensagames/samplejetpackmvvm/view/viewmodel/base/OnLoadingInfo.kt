package sample.settings.gensagames.samplejetpackmvvm.view.viewmodel.base

import sample.settings.gensagames.samplejetpackmvvm.model.`object`.InfoObject

interface OnLoadingInfo {

    fun onLoaded(objects : List<InfoObject> )
}