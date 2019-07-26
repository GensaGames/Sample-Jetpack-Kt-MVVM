package sample.settings.gensagames.samplejetpackmvvm.inject

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.runner.RunWith
import sample.settings.gensagames.samplejetpackmvvm.MainActivityTest


@RunWith(AndroidJUnit4::class)
abstract class BaseInstrumentedTest {
    private val injector: TestApplicationComponent = DaggerTestActivityComponent
        .builder()
        .build()

    @Before
    fun setUp() {
        when (this) {
            is MainActivityTest -> injector.inject(this)
        }
    }
}