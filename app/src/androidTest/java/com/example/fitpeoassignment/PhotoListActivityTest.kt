package com.example.fitpeoassignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Root
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fitpeoassignment.core.utils.Resource
import com.example.fitpeoassignment.feature.photodetails.PhotoDetailActivity
import com.example.fitpeoassignment.feature.photolist.adapter.PhotoListAdapter
import com.example.fitpeoassignment.feature.photolist.adapter.PhotoViewHolder
import com.example.fitpeoassignment.feature.photolist.model.Photo
import com.example.fitpeoassignment.feature.photolist.ui.PhotoListActivity
import com.example.fitpeoassignment.feature.photolist.viewmodels.PhotoListViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.AdditionalMatchers.not
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.util.EnumSet.allOf
import java.util.regex.Matcher

@RunWith(AndroidJUnit4::class)
class PhotoListActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityScenarioRule(PhotoListActivity::class.java)

    /*@Test
    fun testPhotosLoadedSuccessfully() {
        //onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))

        val photos = listOf(
            Photo(1, "title 1", "url1","url11"),
            Photo(2, "title 2", "url2","url22"),
            Photo(3, "title 2", "url3","url33")
        )
        val successResource = Resource.Success(photos)

        val liveData = MutableLiveData<Resource<List<Photo>>>()
        liveData.value = successResource

        val viewModel = mock(PhotoListViewModel::class.java)

        `when`(viewModel.photos).thenReturn(liveData)

        *//*activityRule.scenario.onActivity { activity ->
            activity.getPhotoViewModel() = viewModel
        }*//*

        onView(withId(R.id.photo_list)).check(matches(hasChildCount(photos.size)))

        photos.forEachIndexed { index, photo ->
            onView(withId(R.id.photo_list)).perform(
                RecyclerViewActions.scrollToPosition<PhotoViewHolder>(index)
            )
            onView(withText(photo.title)).check(matches(isDisplayed()))
            onView(withText(photo.url)).check(matches(isDisplayed()))
        }
    }
*/
    /*@Test
    fun testErrorLoadingPhotos() {
        val errorMessage = "Failed to load photos"

        val errorResource = Resource.Error<List<Photo>>(errorMessage)

        val viewModel = mock(PhotoListViewModel::class.java)

        `when`(viewModel.photos).thenReturn(errorResource)

        activityRule.scenario.onActivity { activity ->
            activity.viewModel = viewModel
        }

        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
        onView(withText(errorMessage)).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }*/

    /*class ToastMatcher : RootMatcher {
        override fun getConstraints(): Matcher<Root> {
            return isPlatformPopup()
        }

        override fun getDescription(): String {
            return "is toast"
        }

        override fun perform(uiController: UiController?, view: View?) {
            val toastView = view?.findViewById<TextView>(android.R.id.message)
            if (toastView != null && !toastView.isShown) {
                throw AssertionError("Toast is not shown.")
            }
        }
    }*/
}