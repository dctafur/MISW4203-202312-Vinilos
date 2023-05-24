package com.vinyls.performers.details

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
import com.vinyls.performers.Performer
import com.vinyls.atPosition
import com.vinyls.performers.list.PerformersListAdapter.PerformerViewHolder

var performers: Array<Performer> = arrayOf(
    Performer(id = 101, name = "Queen", image = "", description = "description",  albums = listOf()),
    Performer(id = 2, name = "Maroon 5", image = "", description = "description",  albums = listOf()),
)

val albumsLists: Array<Array<Album>> = arrayOf(
    arrayOf(
        Album(id = 103, name = "A Day at the Races", cover = "", description = "", genre = "", releaseDate = ""),
        Album(id = 102, name = "A Night at the Opera", cover = "", description = "", genre = "", releaseDate = ""),
    ),
    arrayOf(),
)

@LargeTest
@RunWith(AndroidJUnit4::class)
class PerformersDetailsTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun ensurePerformersDetailsWork() {
        val performersMenu = onView(withId(R.id.navigation_performers_list))
        performersMenu.perform(click())

        Thread.sleep(2000)

        for (i in performers.indices) {
            val performersList = onView(withId(R.id.performers_list))
            performersList.check(matches(atPosition(i, hasDescendant(withText(performers[i].name)))))
            performersList.perform(actionOnItemAtPosition<PerformerViewHolder>(i, click()))

            Thread.sleep(2000)

            val performerDetails = onView(withId(R.id.performer_details))
            performerDetails.check(matches(hasDescendant(withText(performers[i].name))))

            val albums = albumsLists[i]
            val performerAlbumsList = onView(withId(R.id.performer_albums_list))
            for (j in albums.indices) {
                performerAlbumsList.check(matches(atPosition(j, hasDescendant(withText(albums[j].name)))))
            }

            Thread.sleep(1000)
            onView(withId(R.id.close_button)).perform(click())
        }

    }
}
