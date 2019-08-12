package sample.settings.gensagames.samplejetpackmvvm.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.facebook.litho.EventDispatcher
import com.facebook.litho.EventHandler
import com.facebook.litho.HasEventDispatcher
import sample.settings.gensagames.samplejetpackmvvm.model.dto.HeaderIntroObject
import sample.settings.gensagames.samplejetpackmvvm.model.dto.InfoObject
import sample.settings.gensagames.samplejetpackmvvm.model.net.SampleApi
import sample.settings.gensagames.samplejetpackmvvm.model.net.SampleApi2
import sample.settings.gensagames.samplejetpackmvvm.utils.Event
import sample.settings.gensagames.samplejetpackmvvm.view.adapter.AdapterItemClickEvent
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

    val headerIntro: MutableLiveData<HeaderIntroObject> = MutableLiveData()
        get() {
            Timber.d( "getHeaderIntro.")
            field.value ?:let {
                field.value = sampleApi2.getHeaderIntroObject()
            }
            return field
        }

    val loadingInfoItems : MutableLiveData<List<InfoObject>> = MutableLiveData()
        get() {
            Timber.d( "getLoadingInfoItems.")
            field.value ?:let {
                field.postValue(emptyList())
                loadInfoData()
            }
            return field
        }

    val clickEvents: EventHandler<InfoObject> = object : EventHandler<InfoObject>(
        HasEventDispatcher {
            EventDispatcher { _, eventState ->
                val info = (eventState as AdapterItemClickEvent).info.also {
                    Timber.d("Received Click Events: $it")
                }
                navigateAction.value = Event(info)
                null
            }

        }, 0) {
    }

    val navigateAction : MutableLiveData<Event<InfoObject>> = MutableLiveData()

    private fun loadInfoData() {
        Timber.d( "updateResponse.")
        loadingStatus.value = View.VISIBLE

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

}