package com.vinyls.collectors.aggregate

import androidx.appcompat.widget.MenuPopupWindow.MenuDropDownListView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.CoreMatchers.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import com.vinyls.MainActivity
import com.vinyls.R
import com.vinyls.albums.Album
import com.vinyls.albums.list.AlbumsListAdapter.AlbumViewHolder
import com.vinyls.atPosition
import com.vinyls.clickChildViewWithId
import com.vinyls.collectors.Collector
import com.vinyls.collectors.list.CollectorsListAdapter.CollectorViewHolder

var collectors: Array<Collector> = arrayOf(
    Collector(id = 100, name = "Manolo Bellon", telephone = "3502457896", email = "manollo@caracol.com.co"),
    Collector(id = 101, name = "Jaime Monsalve", telephone = "3012357936", email = "jmonsalve@rtvc.com.co"),
)

val newAlbum = Album(id = 103, name = "A Day at the Races", cover = "", description = "", genre = "", releaseDate = "")

@LargeTest
@RunWith(AndroidJUnit4::class)
class CollectorsAggregateTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun ensureCollectorsAggregateWork() {
        Thread.sleep(1000)
        val collectorsMenu = onView(withId(R.id.navigation_collectors_list))
        collectorsMenu.perform(click())
        Thread.sleep(2000)

        for (i in collectors.indices) {
            val collectorsList = onView(withId(R.id.collectors_list))
            collectorsList.perform(actionOnItemAtPosition<CollectorViewHolder>(i, clickChildViewWithId(R.id.options_button)))
            Thread.sleep(1000)
            onData(anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(0).perform(click())

            Thread.sleep(2000)
            val albumsList = onView(withId(R.id.albums_list))
            albumsList.perform(actionOnItemAtPosition<AlbumViewHolder>(3, click()))

            Thread.sleep(1000)
            collectorsList.perform(actionOnItemAtPosition<CollectorViewHolder>(i, click()))
            Thread.sleep(2000)

            val collectorAlbumsList = onView(withId(R.id.collector_albums_list))
            collectorAlbumsList.check(matches(atPosition(0, hasDescendant(withText(newAlbum.name)))))
            Thread.sleep(1000)
            onView(withId(R.id.close_button)).perform(click())
            Thread.sleep(1000)
        }
    }
}
