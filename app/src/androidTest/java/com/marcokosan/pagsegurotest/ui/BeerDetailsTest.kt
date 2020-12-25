package com.marcokosan.pagsegurotest.ui

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marcokosan.pagsegurotest.R
import com.marcokosan.pagsegurotest.data.TestData
import com.marcokosan.pagsegurotest.ui.beerdetails.BeerDetailsActivity
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BeerDetailsTest {

    @get:Rule
    val rule = ActivityScenarioRule<BeerDetailsActivity>(
        BeerDetailsActivity.starterIntent(
            ApplicationProvider.getApplicationContext(),
            TestData.BEER
        )
    )

    @Test
    fun basicViewsDisplayed() {
        onView(
            CoreMatchers.allOf(
                withId(R.id.beer_name),
                withText(TestData.FAKE_BEER_NAME)
            )
        ).check(matches(isDisplayed()))

        onView(
            CoreMatchers.allOf(
                withId(R.id.tagline),
                withText(TestData.FAKE_BEER_TAGLINE)
            )
        ).check(matches(isDisplayed()))
    }
}