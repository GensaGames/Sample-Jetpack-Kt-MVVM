package sample.settings.gensagames.samplejetpackmvvm.model

import android.content.Context
import timber.log.Timber
import javax.inject.Inject

/**
 * Just simple helper class, injected in Context files,
 * for tests purpose to mock changes in dagger in some tests.
 */
open class SampleContextHelper @Inject constructor() {

    fun logSampleInfo(context : Context) {
        Timber.d( String.format("logSampleInfo. " +
                "Object: %s", context.hashCode()))
    }
}