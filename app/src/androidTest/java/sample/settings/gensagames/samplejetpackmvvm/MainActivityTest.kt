package sample.settings.gensagames.samplejetpackmvvm

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sample.settings.gensagames.samplejetpackmvvm.inject.BaseInstrumentedTest
import sample.settings.gensagames.samplejetpackmvvm.utils.assertNotEmptyAction
import sample.settings.gensagames.samplejetpackmvvm.utils.isFeaturedViewHolder
import sample.settings.gensagames.samplejetpackmvvm.utils.waitFor
import sample.settings.gensagames.samplejetpackmvvm.view.MainActivity
import sample.settings.gensagames.samplejetpackmvvm.view.adapter.MainGridInfoAdapter
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class MainActivityTest : BaseInstrumentedTest() {

    companion object {
        private val ITEMS_TIME_TO = TimeUnit
            .SECONDS.toMillis(1)
        private const val ITEMS_MIN_SIZE = 15
    }

    /**
     * Use [ActivityScenarioRule] to create and launch the activity under test.
     */
    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testUseAppContext() {
        val appContext = InstrumentationRegistry
            .getInstrumentation().targetContext

        Assert.assertEquals(
            "sample.settings.gensagames.samplejetpackmvvm",
            appContext.packageName
        )
    }

    @Test
    fun testActiveLoading() {
        Assert.assertEquals(
            activityScenarioRule
                .scenario.state, Lifecycle.State.RESUMED
        )

        onView(withId(R.id.progress)).check(
            matches(
                withEffectiveVisibility(Visibility.VISIBLE)
            )
        )

        onView(isRoot()).perform(waitFor(ITEMS_TIME_TO))
        onView(withId(R.id.progress)).check(
            matches(
                withEffectiveVisibility(Visibility.GONE)
            )
        )
    }

    @Test
    fun testMinimumItems() {
        Assert.assertEquals(
            activityScenarioRule
                .scenario.state, Lifecycle.State.RESUMED
        )

        onView(isRoot()).perform(waitFor(ITEMS_TIME_TO))
        onView(withId(R.id.recyclerView)).check { v, _ ->
            v as RecyclerView
            Assert.assertTrue(
                v.adapter!!
                    .itemCount > ITEMS_MIN_SIZE
            )
        }
    }

    @Test
    fun testFeaturedViewHolder() {
        onView(isRoot()).perform(waitFor(ITEMS_TIME_TO))
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.scrollToHolder(isFeaturedViewHolder()))
    }

    @Test
    fun testRecyclerViewItems() {
        onView(isRoot()).perform(waitFor(ITEMS_TIME_TO))

        for (i in 1..ITEMS_MIN_SIZE) {
            onView(withId(R.id.recyclerView))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition
                    <RecyclerView.ViewHolder>(i, assertNotEmptyAction())
                )


        }
    }
}