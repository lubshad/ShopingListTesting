package com.example.shopinglisttesting.ui.shopping_list

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.shopinglisttesting.R
import com.example.shopinglisttesting.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


@MediumTest
@HiltAndroidTest
class FragmentShoppingListTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Before
    fun setup() {
        hiltRule.inject()
    }


    @Test
    fun addItemFab_navigateToAddItemFragment() {
        val navController = mock(NavController::class.java)


        launchFragmentInHiltContainer<FragmentShoppingList> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.fab_add_item)).perform(click())

        val action = FragmentShoppingListDirections.actionFragmentShoppingListToFragmentAddItem()
        verify(navController).navigate(action)
    }
}