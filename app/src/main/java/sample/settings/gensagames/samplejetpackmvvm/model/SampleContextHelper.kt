package sample.settings.gensagames.samplejetpackmvvm.model

import android.content.Context
import android.util.Log
import sample.settings.gensagames.samplejetpackmvvm.utils.TAG
import sample.settings.gensagames.samplejetpackmvvm.view.MainActivity
import javax.inject.Inject

/**
 * Just simple helper class, injected in Context files,
 * for tests purpose to mock changes in dagger in some tests.
 */
open class SampleContextHelper @Inject constructor() {

    fun logSampleInfo(context : Context) {
        Log.d(TAG, String.format("logSampleInfo. " +
                "Object: %s", context.hashCode()))
    }
}