package com.example.shopinglisttesting.di


import com.example.shopinglisttesting.domain.pixabay_api.PixabayApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("SpellCheckingInspection")
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesPixabayRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(PixabayApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    fun providesPixabayApiInterface(pixabayRetrofit: Retrofit): PixabayApi =
        pixabayRetrofit.create(PixabayApi::class.java)
}