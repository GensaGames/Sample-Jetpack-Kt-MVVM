package sample.settings.gensagames.samplejetpackmvvm.model.net

import android.util.Log
import sample.settings.gensagames.samplejetpackmvvm.utils.TAG
import javax.inject.Inject
import kotlin.random.Random

/**
 * Stub implementation of API Service, or Repository files.
 */
class SampleApi @Inject constructor() {

    fun getSampleStatus(value : Any) : Boolean {

        fun innerValid() : Boolean {
            val result = Random(System.currentTimeMillis()).nextBoolean()
            Log.d(TAG, String.format("Returning Sample API Response: %s", result))
            return result
        }

        return when (value) {
            is String -> innerValid()
            else -> false
        }
    }
}