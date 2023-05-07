package com.vinyls.collectors.list

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
import com.vinyls.collectors.Collector
import com.vinyls.atPosition

var collectors: Array<Collector> = arrayOf(
    Collector(name = "Manolo Bellon", email = "manollo@caracol.com.co", id = 1, telephone = ""),
    Collector(name = "Jaime Monsalve", email = "jmonsalve@rtvc.com.co", id = 1, telephone = ""),
)

@LargeTest
@RunWith(AndroidJUnit4::class)
class CollectorListTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun ensureAlbumListWork() {
        onView(withId(R.id.navigation_collectors_list)).perform(click())
        Thread.sleep(4000)
        for (i in collectors.indices) {
            onView(withId(R.id.collector_list))
                .check(matches(atPosition(i, hasDescendant(withText(collectors[i].name)))))
        }
    }
}
