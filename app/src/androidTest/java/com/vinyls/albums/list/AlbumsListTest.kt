package com.vinyls.albums.list

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
import com.vinyls.albums.Album
import com.vinyls.atPosition

var albums: Array<Album> = arrayOf(
    Album(id = 100, name = "Buscando América", cover = "", description = "", genre = "", releaseDate = "", recordLabel = ""),
    Album(id = 101, name = "Poeta del pueblo", cover = "", description = "", genre = "", releaseDate = "", recordLabel = ""),
    Album(id = 102, name = "A Night at the Opera", cover = "", description = "", genre = "", releaseDate = "", recordLabel = ""),
    Album(id = 103, name = "A Day at the Races", cover = "", description = "", genre = "", releaseDate = "", recordLabel = ""),
)

@LargeTest
@RunWith(AndroidJUnit4::class)
class AlbumsListTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun ensureAlbumListWork() {
        onView(withId(R.id.navigation_albums_list)).perform(click())
        Thread.sleep(1000)
        for (i in albums.indices) {
            onView(withId(R.id.albums_list))
                .check(matches(atPosition(i, hasDescendant(withText(albums[i].name)))))
        }
    }
}
