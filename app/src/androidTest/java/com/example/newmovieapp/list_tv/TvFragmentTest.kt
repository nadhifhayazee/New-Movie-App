package com.example.newmovieapp.list_tv

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.newmovieapp.R
import com.example.newmovieapp.activity.MainActivity
import com.example.newmovieapp.util.DataDummyHelper
import com.example.newmovieapp.util.EspressoIdlingResource
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class TvFragmentTest {
    private val expectedDetailTv = DataDummyHelper.getDummyTv()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
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
    fun loadDetailTv() {
        Espresso.onView(ViewMatchers.withId(R.id.rvListMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                ViewActions.click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.tvTitleDetail))
            .check(ViewAssertions.matches(ViewMatchers.withText(expectedDetailTv.name)))

        Espresso.onView(ViewMatchers.withId(R.id.tvGenres))
            .check(ViewAssertions.matches(ViewMatchers.withText(expectedDetailTv.getGenres())))

        Espresso.onView(ViewMatchers.withId(R.id.tvRuntime))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.withText(
                        expectedDetailTv.episode_run_time?.getOrNull(
                            0
                        ).toString() + " Menit"
                    )
                )
            )

        Espresso.onView(ViewMatchers.withId(R.id.tvRating))
            .check(ViewAssertions.matches(ViewMatchers.withText(expectedDetailTv.vote_average.toString())))

        Espresso.onView(ViewMatchers.withId(R.id.tvOverview))
            .check(ViewAssertions.matches(ViewMatchers.withText(expectedDetailTv.overview)))

        Espresso.onView(ViewMatchers.withId(R.id.ivBackdropDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.ivPosterDetail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

}