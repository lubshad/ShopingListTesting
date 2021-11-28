package com.example.shopinglisttesting.di


import android.content.Context
import androidx.room.Room
import com.example.shopinglisttesting.data.repository.shopping_item.ShoppingDatabase
import com.example.shopinglisttesting.data.repository.shopping_item.ShoppingItemDao
import com.example.shopinglisttesting.domain.pixabay_api.PixabayApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Suppress("SpellCheckingInspection")
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesShoppingItemDatabase(@ApplicationContext context: Context): ShoppingDatabase {
        return Room.databaseBuilder(context, ShoppingDatabase::class.java, "shopping_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesShoppingItemDao(db: ShoppingDatabase): ShoppingItemDao = db.shoppingDao()


    @Provides
    @Singleton
    fun providesPixabayRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(PixabayApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    @Singleton
    fun providesPixabayApiInterface(pixabayRetrofit: Retrofit): PixabayApi =
        pixabayRetrofit.create(PixabayApi::class.java)
}