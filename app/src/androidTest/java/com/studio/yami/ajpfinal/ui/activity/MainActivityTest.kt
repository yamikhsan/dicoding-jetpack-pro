package com.studio.yami.ajpfinal.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.studio.yami.ajpfinal.R
import com.studio.yami.ajpfinal.idleresource.EspressoIdlingResource
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activity = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource)
    }

    @Test
    fun navBottomTest(){
        onView(allOf(withId(R.id.nav_favorite), isDescendantOfA(withId(R.id.nav_view)))).perform(click())
        onView(withId(R.id.fr_favorite)).check(matches(isDisplayed()))
        onView(allOf(withText(R.string.tv), isDescendantOfA(withId(R.id.tabs)))).perform(click())
        onView(withId(R.id.rv_fav_tv)).check(matches(isDisplayed()))
        onView(allOf(withText(R.string.movie), isDescendantOfA(withId(R.id.tabs)))).perform(click())
        onView(withId(R.id.rv_fav_movie)).check(matches(isDisplayed()))

        onView(allOf(withId(R.id.nav_tv), isDescendantOfA(withId(R.id.nav_view)))).perform(click())
        onView(withId(R.id.fr_tv)).check(matches(isDisplayed()))
        checkDetail(R.id.rv_tv)
        onView(isRoot()).perform(pressBack())

        onView(allOf(withId(R.id.nav_movies), isDescendantOfA(withId(R.id.nav_view)))).perform(click())
        onView(withId(R.id.fr_movie)).check(matches(isDisplayed()))
        checkDetail(R.id.rv_movie)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource)
    }

    private fun checkDetail(id: Int){
        onView(withId(id)).check(matches(isDisplayed()))
        onView(withId(id)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.layout_detail)).check(matches(isDisplayed()))
    }
}