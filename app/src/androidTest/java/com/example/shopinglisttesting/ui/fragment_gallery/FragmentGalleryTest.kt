package com.example.shopinglisttesting.ui.fragment_gallery

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.shopinglisttesting.R
import com.example.shopinglisttesting.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import java.util.concurrent.TimeUnit

class FragmentGalleryTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }



    @Test
    fun imagePickerTest() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<FragmentGallery> {
            Navigation.setViewNavController(requireView(), navController)
        }

        TimeUnit.MINUTES.sleep(5)

        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_gallery)).perform(
            RecyclerViewActions.actionOnItemAtPosition<PixabayImageAdapter.PixabayImageViewHolder>(
                2,
                ViewActions.click()
            )
        )

        Mockito.verify(navController).popBackStack()
    }
}