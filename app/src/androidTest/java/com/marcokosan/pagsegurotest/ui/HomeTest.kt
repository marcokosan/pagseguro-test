package com.marcokosan.pagsegurotest.ui

import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marcokosan.pagsegurotest.R
import com.marcokosan.pagsegurotest.data.TestData
import com.marcokosan.pagsegurotest.ui.home.HomeActivity
import com.marcokosan.pagsegurotest.ui.home.HomeViewHolder
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeTest {

    @get:Rule
    val rule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun basicViewsDisplayed() {
        onView(CoreMatchers.allOf(CoreMatchers.instanceOf(TextView::class.java),
            withParent(withId(R.id.toolbar))))
            .check(matches(withText(R.string.title_home)))

        onView(withId(R.id.beers)).perform(scrollToPosition<HomeViewHolder>(0))

        onView(
            CoreMatchers.allOf(
                withId(R.id.beer_name),
                withText(TestData.FAKE_BEER_NAME)
            )
        ).check(matches(isDisplayed()))
    }

    @Test
    fun clickOnFirstItem_detailsShown() {
        onView(withId(R.id.beers)).perform(actionOnItemAtPosition<HomeViewHolder>(0, click()))

        onView(withId(R.id.beer_image)).check(matches(isDisplayed()))
        onView(withId(R.id.beer_name)).check(matches(isDisplayed()))
        onView(withId(R.id.abv)).check(matches(isDisplayed()))
    }
}