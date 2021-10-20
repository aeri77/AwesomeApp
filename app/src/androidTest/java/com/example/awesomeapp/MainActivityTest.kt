package com.example.awesomeapp

import androidx.test.core.app.ActivityScenario
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.awesomeapp.ui.list.ListPhotoActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.example.awesomeapp.R
import com.example.awesomeapp.ui.list.ListPhotoAdapter

/**
 * @author Aeri on 10/20/2021.
 */

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)

    }

    @Test
    fun runUITest() {
        Thread.sleep(5000)
        onView(withId(R.id.menu_grid_view)).perform(click())
        onView(withId(R.id.menu_linear_view)).perform(click())
        onView(withId(R.id.menu_grid_view)).perform(click())
        onView(withId(R.id.menu_linear_view)).perform(click())
        onView(withId(R.id.menu_grid_view)).perform(click())
        onView(withId(R.id.menu_linear_view)).perform(click())
        onView(withId(R.id.menu_grid_view)).perform(click())
        onView(withId(R.id.menu_linear_view)).perform(click())

        onView(withId(R.id.cl)).perform(swipeUp())
        onView(withId(R.id.cl)).perform(swipeUp())
        onView(withId(R.id.cl)).perform(swipeDown())
        onView(withId(R.id.cl)).perform(swipeDown())
        onView(withId(R.id.cl)).perform(swipeUp())
        onView(withId(R.id.cl)).perform(swipeUp())
        onView(withId(R.id.cl)).perform(swipeDown())
        onView(withId(R.id.cl)).perform(swipeDown())
        onView(withId(R.id.cl)).perform(click())
        onView(withId(R.id.tv_name)).perform(pressBack())
    }


}
