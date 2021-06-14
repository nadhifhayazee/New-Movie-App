package com.example.newmovieapp.list_tv

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.newmovieapp.R
import com.example.newmovieapp.activity.MainActivity
import com.example.newmovieapp.util.EspressoIdlingResource
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4ClassRunner::class)
class TvFragmentTest {

    @get:Rule
    var mActivityScenario = ActivityScenarioRule(MainActivity::class.java)

    lateinit var scenario: ActivityScenario<MainActivity>

    private var activity: MainActivity? = null

    @Before
    fun setUp() {
        scenario = mActivityScenario.scenario
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)

        Espresso.onView(ViewMatchers.withId(R.id.tvFragment)).perform(ViewActions.click())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadTvList() {

        Espresso.onView(ViewMatchers.withId(R.id.movieCarousel))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.rvListMovie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        Espresso.onView(ViewMatchers.withId(R.id.progressBar))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))

        Espresso.onView(ViewMatchers.withId(R.id.toolbarTitle))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))

        Espresso.onView(ViewMatchers.withId(R.id.scrollView)).perform(ViewActions.swipeUp())

        Espresso.onView(ViewMatchers.withId(R.id.toolbarTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadDetailTvAndInsertFavorite() {
        scenario.onActivity {
            activity = it
        }

        Espresso.onView(ViewMatchers.withId(R.id.rvListMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.tvTitleDetail))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.withText(""))))

        Espresso.onView(ViewMatchers.withId(R.id.tvGenres))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.withText(""))))

        Espresso.onView(ViewMatchers.withId(R.id.tvRuntime))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.withText(""))))

        Espresso.onView(ViewMatchers.withId(R.id.tvRating))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.withText(""))))

        Espresso.onView(ViewMatchers.withId(R.id.tvOverview))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.withText(""))))

        Espresso.onView(ViewMatchers.withId(R.id.ivBackdropDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.ivPosterDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.ivFavorite)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())

        Espresso.onView(ViewMatchers.withId(R.id.favoriteFragment)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.viewPager)).perform(swipeLeft())

        sleep(1000)
        val recycler = activity?.findViewById<RecyclerView>(R.id.rvListFavoriteMovie)
        if (recycler?.isVisible == true) {

            testDeleteFavorite()

        } else {
            Espresso.onView(allOf(ViewMatchers.withId(R.id.noDataView)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }


    }

    private fun testDeleteFavorite() {

        Espresso.onView(allOf(ViewMatchers.withId(R.id.rvListFavoriteMovie))).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        Espresso.onView(allOf(ViewMatchers.withId(R.id.ivFavorite))).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())

        val recycler = activity?.findViewById<RecyclerView>(R.id.rvListFavoriteMovie)
        if (recycler?.isVisible == true) {

            Espresso.onView(ViewMatchers.withId(R.id.rvListFavoriteMovie))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        } else {
            Espresso.onView(allOf(ViewMatchers.withId(R.id.noDataView)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

}