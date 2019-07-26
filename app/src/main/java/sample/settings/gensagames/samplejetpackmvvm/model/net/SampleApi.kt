package sample.settings.gensagames.samplejetpackmvvm.model.net

import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

/**
 * Stub implementation of API Service, or Repository files.
 */
class SampleApi @Inject constructor() {

    fun getSampleStatus(value : Any) : Boolean {

        fun innerValid() : Boolean {
            val result = Random(System.currentTimeMillis()).nextBoolean()
            Timber.d( String.format("Returning Sample API Response: %s", result))
            return result
        }

        return when (value) {
            is String -> innerValid()
            else -> false
        }
    }
}