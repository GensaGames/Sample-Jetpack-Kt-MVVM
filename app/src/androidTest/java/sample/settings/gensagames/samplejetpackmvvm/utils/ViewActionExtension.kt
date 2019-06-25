package sample.settings.gensagames.samplejetpackmvvm.utils

import android.util.Log
import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.ViewAction
import org.hamcrest.Matcher
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import sample.settings.gensagames.samplejetpackmvvm.view.adapter.MainGridInfoAdapter


/**
 * Perform action of waiting for a specific time.
 */
fun waitFor(millis: Long): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return isRoot()
        }

        override fun getDescription(): String {
            return "Wait for $millis milliseconds."
        }

        override fun perform(uiController: UiController, view: View) {
            uiController.loopMainThreadForAtLeast(millis)
        }
    }
}

fun assertNotEmptyAction(): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return isDisplayed()
        }

        override fun getDescription(): String {
            return "Assert not empty action."
        }

        override fun perform(uiController: UiController, view: View) {
            Log.d("fsdfs", "Work: " + view.tag)
        }
    }
}

/**
 * Matches the [CustomAdapter.ViewHolder]s is featured
 */
fun isFeaturedViewHolder(): Matcher<MainGridInfoAdapter.InfoViewHolder> {
    return object : TypeSafeMatcher<MainGridInfoAdapter.InfoViewHolder>() {
        var isFirst : Boolean = false

        override fun matchesSafely(
            customHolder: MainGridInfoAdapter.InfoViewHolder): Boolean {
            if (customHolder.isFeatured() && !isFirst) {
                isFirst = true
                return true
            }
            return false;
        }

        override fun describeTo(description: Description?) {
            description?.appendText("item is featured")
        }
    }
}