package com.vinyls.albums.details

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
import com.vinyls.atPosition
import com.vinyls.albums.list.AlbumsListAdapter.AlbumViewHolder

var albums: Array<Album> = arrayOf(
    Album(id = 100, name = "Buscando América", cover = "", description = "", genre = "", releaseDate = "", recordLabel = ""),
    Album(id = 101, name = "Poeta del pueblo", cover = "", description = "", genre = "", releaseDate = "", recordLabel = ""),
    Album(id = 102, name = "A Night at the Opera", cover = "", description = "", genre = "", releaseDate = "", recordLabel = ""),
    Album(id = 103, name = "A Day at the Races", cover = "", description = "", genre = "", releaseDate = "", recordLabel = ""),
)

@LargeTest
@RunWith(AndroidJUnit4::class)
class AlbumsDetailsTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun ensureAlbumDetailsWork() {
        onView(withId(R.id.navigation_albums_list)).perform(click())
        Thread.sleep(2000)
        for (i in albums.indices) {
            val albumsList = onView(withId(R.id.albums_list))
            albumsList.check(matches(atPosition(i, hasDescendant(withText(albums[i].name)))))
            albumsList.perform(actionOnItemAtPosition<AlbumViewHolder>(i, click()))

            Thread.sleep(2000)

            val albumDetails = onView(withId(R.id.album_details))
            albumDetails.check(matches(hasDescendant(withText(albums[i].name))))

            Thread.sleep(1000)
            onView(withId(R.id.close_button)).perform(click())
        }
    }
}
