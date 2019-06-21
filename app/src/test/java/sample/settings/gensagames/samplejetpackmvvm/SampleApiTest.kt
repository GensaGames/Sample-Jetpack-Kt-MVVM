package sample.settings.gensagames.samplejetpackmvvm

import org.junit.Assert
import org.junit.Test
import sample.settings.gensagames.samplejetpackmvvm.inject.BaseUnitTest
import sample.settings.gensagames.samplejetpackmvvm.model.net.SampleApi
import javax.inject.Inject

class SampleApiTest : BaseUnitTest() {
    @Inject
    lateinit var sampleApi: SampleApi

    @Test
    fun testSampleStatus() {
        Assert.assertEquals(4, 2 + 2)
    }
}