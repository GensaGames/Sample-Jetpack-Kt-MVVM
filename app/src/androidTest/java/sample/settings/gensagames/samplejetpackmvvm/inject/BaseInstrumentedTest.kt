package sample.settings.gensagames.samplejetpackmvvm.inject

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.runner.RunWith
import sample.settings.gensagames.samplejetpackmvvm.MainViewModelTest


@RunWith(AndroidJUnit4::class)
abstract class BaseInstrumentedTest {
    private val injector: TestComponent = DaggerTestComponent
        .builder()
        .module(MainApiModuleTest())
        .build()

    @Before
    fun setUp() {
        Log.d("TAG","LOL11");
        System.out.println("LOL11")
        injector.inject(this as MainViewModelTest)
    }
}