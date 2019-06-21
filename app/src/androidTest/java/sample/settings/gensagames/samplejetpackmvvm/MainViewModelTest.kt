package sample.settings.gensagames.samplejetpackmvvm

import android.util.Log
import android.widget.Toast
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import sample.settings.gensagames.samplejetpackmvvm.inject.API_TEST_TAG
import sample.settings.gensagames.samplejetpackmvvm.inject.BaseInstrumentedTest
import sample.settings.gensagames.samplejetpackmvvm.model.net.SampleApi
import sample.settings.gensagames.samplejetpackmvvm.model.net.SampleApi2
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class MainViewModelTest : BaseInstrumentedTest() {

    @Inject
    lateinit var sampleApi2: SampleApi2

    @Inject
    lateinit var sampleApi: SampleApi

    @Test
    fun testInfoItemsBeforeLoading() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        Assert.assertEquals(
            "sample.settings" +
                    ".gensagames.samplejetpackmvvm", appContext.packageName
        )
    }

    @Test
    fun checkSampleApi2() {
        Assert.assertEquals(sampleApi2.getInfo(), API_TEST_TAG)
    }
}