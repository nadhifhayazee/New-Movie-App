package com.example.newmovieapp.list_movie

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.newmovieapp.R
import com.example.newmovieapp.activity.MainActivity
import com.example.newmovieapp.util.EspressoIdlingResource
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MovieFragmentTest {

    @get:Rule
    var mActivityScenario = ActivityScenarioRule(MainActivity::class.java)

    lateinit var scenario: ActivityScenario<MainActivity>

    private var activity: MainActivity? = null

    @Before
    fun setUp() {
        scenario = mActivityScenario.scenario
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovieList() {
        Espresso.onView(withId(R.id.movieCarousel))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.rvListMovie))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.progressBar))
            .check(ViewAssertions.matches(not(isDisplayed())))

        Espresso.onView(withId(R.id.toolbarTitle))
            .check(ViewAssertions.matches(not(isDisplayed())))

        Espresso.onView(withId(R.id.scrollView)).perform(ViewActions.swipeUp())

        Espresso.onView(withId(R.id.toolbarTitle))
            .check(ViewAssertions.matches(isDisplayed()))
    }


    @Test
    fun loadDetailMovieAndInsertFavorite() {
        scenario.onActivity {
            activity = it
        }

        Espresso.onView(withId(R.id.rvListMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.tvTitleDetail))
            .check(ViewAssertions.matches(not(withText(""))))

        Espresso.onView(withId(R.id.tvGenres))
            .check(ViewAssertions.matches(not(withText(""))))

        Espresso.onView(withId(R.id.tvRuntime))
            .check(ViewAssertions.matches(not(withText(""))))

        Espresso.onView(withId(R.id.tvRating))
            .check(ViewAssertions.matches(not(withText(""))))

        Espresso.onView(withId(R.id.tvOverview))
            .check(ViewAssertions.matches(not(withText(""))))

        Espresso.onView(withId(R.id.ivBackdropDetail))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.ivPosterDetail))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.ivFavorite)).perform(ViewActions.click())

        Espresso.onView(isRoot()).perform(ViewActions.pressBack())


        Espresso.onView(withId(R.id.favoriteFragment)).perform(ViewActions.click())

        val recycler = activity?.findViewById<RecyclerView>(R.id.rvListFavoriteMovie)
        if (recycler?.isVisible == true) {
            Espresso.onView(withId(R.id.rvListFavoriteMovie)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )

            testDeleteFavorite()

        } else {
            Espresso.onView(withId(R.id.noDataView))
                .check(ViewAssertions.matches(isDisplayed()))
        }


    }

    private fun testDeleteFavorite() {
        Espresso.onView(withId(R.id.ivFavorite)).perform(ViewActions.click())

        Espresso.onView(isRoot()).perform(ViewActions.pressBack())

        val recycler = activity?.findViewById<RecyclerView>(R.id.rvListFavoriteMovie)
        if (recycler?.isVisible == true) {

            Espresso.onView(withId(R.id.rvListFavoriteMovie))
                .check(ViewAssertions.matches(isDisplayed()))

        } else {
            Espresso.onView(withId(R.id.noDataView))
                .check(ViewAssertions.matches(isDisplayed()))
        }
    }

}