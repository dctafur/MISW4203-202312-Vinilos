package com.vinyls.collectors.details

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
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
import com.vinyls.albums.Album
import com.vinyls.collectors.Collector
import com.vinyls.atPosition
import com.vinyls.collectors.list.CollectorsListAdapter.CollectorViewHolder

var collectors: Array<Collector> = arrayOf(
    Collector(id = 100, name = "Manolo Bellon", telephone = "3502457896", email = "manollo@caracol.com.co"),
    Collector(id = 101, name = "Jaime Monsalve", telephone = "3012357936", email = "jmonsalve@rtvc.com.co"),
)

val albumsLists: Array<Array<Album>> = arrayOf(
    arrayOf(
        Album(id = 100, name = "Buscando América", cover = "", description = "", genre = "", releaseDate = "", recordLabel = "")
    ),
    arrayOf(
        Album(id = 101, name = "Poeta del pueblo", cover = "", description = "", genre = "", releaseDate = "", recordLabel = "")
    )
)

@LargeTest
@RunWith(AndroidJUnit4::class)
class CollectorsDetailsTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun ensureCollectorsDetailsWork() {
        val collectorsMenu = onView(withId(R.id.navigation_collectors_list))
        collectorsMenu.perform(click())

        Thread.sleep(2000)

        for (i in collectors.indices) {
            val collectorsList = onView(withId(R.id.collectors_list))
            collectorsList.check(matches(atPosition(i, hasDescendant(withText(collectors[i].name)))))
            collectorsList.check(matches(atPosition(i, hasDescendant(withText(collectors[i].email)))))
            collectorsList.perform(actionOnItemAtPosition<CollectorViewHolder>(i, click()))

            Thread.sleep(2000)

            val collectorDetails = onView(withId(R.id.collector_details))
            collectorDetails.check(matches(hasDescendant(withText(collectors[i].name))))
            collectorDetails.check(matches(hasDescendant(withText(collectors[i].email))))
            collectorDetails.check(matches(hasDescendant(withText(collectors[i].telephone))))

            val albums = albumsLists[i]
            val collectorAlbumsList = onView(withId(R.id.collector_albums_list))
            for (j in albums.indices) {
                collectorAlbumsList.check(matches(atPosition(j, hasDescendant(withText(albums[j].name)))))
            }

            Thread.sleep(1000)
            onView(withId(R.id.close_button)).perform(click())
        }

    }
}
