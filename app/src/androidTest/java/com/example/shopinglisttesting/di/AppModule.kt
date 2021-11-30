package com.example.shopinglisttesting.di

import android.content.Context
import androidx.room.Room
import com.example.shopinglisttesting.data.repository.shopping_item.ShoppingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object AppTestModule {

    @Provides
    @Named("test_db")
    fun provideMemmoryDatabase(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, ShoppingDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}