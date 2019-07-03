package sample.settings.gensagames.samplejetpackmvvm.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import sample.settings.gensagames.samplejetpackmvvm.model.`object`.HeaderIntroObject
import sample.settings.gensagames.samplejetpackmvvm.model.`object`.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.model.net.SampleApi
import sample.settings.gensagames.samplejetpackmvvm.model.net.SampleApi2
import sample.settings.gensagames.samplejetpackmvvm.utils.TAG
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.base.BaseViewModel
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.base.OnLoadingInfo
import javax.inject.Inject

class MainViewModel : BaseViewModel(), OnLoadingInfo {

    @Inject
    lateinit var sampleApi: SampleApi
    @Inject
    lateinit var sampleApi2: SampleApi2

    val loadingStatus: MutableLiveData<Int> = MutableLiveData()
    private val headerIntro: MutableLiveData<HeaderIntroObject> = MutableLiveData()
    private val loadingInfoItems : MutableLiveData<List
    <InfoObject>> = MutableLiveData()

    fun getLoadingInfoItems() : LiveData<List<InfoObject>> {
        Log.d(TAG, "getLoadingInfoItems.")
        loadingInfoItems.value ?: loadInfoData()
        return loadingInfoItems
    }

    private fun loadInfoData() {
        Log.d(TAG, "updateResponse.")
        loadingStatus.value = View.VISIBLE
        loadingInfoItems.postValue(emptyList())

        Thread(Runnable {
            Log.d(TAG, String.format("Start updateResponse. " +
                    "Using SampleApi2 Tag: %s", sampleApi2.getInfo()))
            Thread.sleep(1000)

            onLoaded(sampleApi2.getInfoObjects())
        }).start()
    }

    override fun onLoaded(objects : List<InfoObject>) {
        Log.d(TAG, String.format("onLoaded. Status Sample: %s",
            sampleApi.getSampleStatus("Test")))

        Log.d(TAG, "onLoaded. Objects: $objects")
        loadingStatus.postValue(View.GONE)
        loadingInfoItems.postValue(objects)
    }

    fun getHeaderIntro() : LiveData<HeaderIntroObject>  {
        headerIntro.value ?:let {
            headerIntro.value = sampleApi2.getHeaderIntroObject()
        }
        return headerIntro
    }

}