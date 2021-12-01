package com.example.shopinglisttesting.ui.fragment_add_item

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.shopinglisttesting.R
import com.example.shopinglisttesting.launchFragmentInHiltContainer
import com.example.shopinglisttesting.ui.shopping_list.FragmentShoppingList
import com.example.shopinglisttesting.ui.shopping_list.FragmentShoppingListDirections
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@HiltAndroidTest
@MediumTest
class FragmentAddItemTest {

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }


    @Test
    fun onTapImageViewItem_navigateToGalleryFragment() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<FragmentAddItem> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.image_view_item)).perform(click())
        val action = FragmentAddItemDirections.actionFragmentAddItemToFragmentGallery()
        verify(navController).navigate(action)
    }


    @Test
    fun pressBackButton_popBackStack() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<FragmentShoppingList> {
            Navigation.setViewNavController(requireView(), navController)
        }
        val action = FragmentShoppingListDirections.actionFragmentShoppingListToFragmentAddItem()
        navController.navigate(action)
        pressBack()
        verify(navController).popBackStack()
    }


}