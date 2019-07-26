package sample.settings.gensagames.samplejetpackmvvm.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import sample.settings.gensagames.samplejetpackmvvm.model.dto.HeaderIntroObject
import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.model.net.SampleApi
import sample.settings.gensagames.samplejetpackmvvm.model.net.SampleApi2
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.base.BaseViewModel
import sample.settings.gensagames.samplejetpackmvvm.viewmodel.base.OnLoadingInfo
import timber.log.Timber
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
        Timber.d( "getLoadingInfoItems.")
        loadingInfoItems.value ?: loadInfoData()
        return loadingInfoItems
    }

    private fun loadInfoData() {
        Timber.d( "updateResponse.")
        loadingStatus.value = View.VISIBLE
        loadingInfoItems.postValue(emptyList())

        Thread(Runnable {
            Timber.d( String.format("Start updateResponse. " +
                    "Using SampleApi2 Tag: %s", sampleApi2.getInfo()))
            Thread.sleep(1000)

            onLoaded(sampleApi2.getInfoObjects())
        }).start()
    }

    override fun onLoaded(objects : List<InfoObject>) {
        Timber.d( String.format("onLoaded. Status Sample: %s",
            sampleApi.getSampleStatus("Test")))

        Timber.d( "onLoaded. Objects: $objects")
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