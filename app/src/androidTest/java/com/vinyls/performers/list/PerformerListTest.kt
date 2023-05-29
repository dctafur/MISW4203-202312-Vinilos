package com.vinyls.performers.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import com.vinyls.MainActivity
import com.vinyls.R
import com.vinyls.performers.Performer
import com.vinyls.atPosition

var performers: Array<Performer> = arrayOf(
    Performer(name = "Queen", image = "https://pm1.narvii.com/6724/a8b29909071e9d08517b40c748b6689649372852v2_hq.jpg", id = 1, description = "Description", albums = listOf()),
    Performer(name = "Maroon 5", image = "https://source.boomplaymusic.com/group10/M00/01/30/1579a2e6e3fa4da380b51afb2e42c553_320_320.png", id = 3, description = "Description", albums = listOf()),
    Performer(name = "Soda Stereo", image = "https://upload.wikimedia.org/wikipedia/commons/6/6f/CeratiAlbertiBosio.jpg", id = 2, description = "Description", albums = listOf()),
)

@LargeTest
@RunWith(AndroidJUnit4::class)
class PerformerListTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun ensurePerformerListWork() {
        onView(withId(R.id.navigation_performers_list)).perform(click())
        Thread.sleep(4000)
        for (i in performers.indices) {
            onView(withId(R.id.performers_list))
                .check(matches(atPosition(i, hasDescendant(withText(performers[i].name)))))
        }
    }
}
