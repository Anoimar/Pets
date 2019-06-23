package com.thernat.pets.di

import android.app.Application
import androidx.room.Room
import com.thernat.pets.BuildConfig
import com.thernat.pets.api.ApiService
import com.thernat.pets.db.PetDao
import com.thernat.pets.db.MVVMDatabase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://boiling-lowlands-48524.herokuapp.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(createOkHttpClient())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): MVVMDatabase {
        return Room
            .databaseBuilder(app, MVVMDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBasicVoDao(db: MVVMDatabase): PetDao {
        return db.petDao()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if(BuildConfig.HTTP_LOGIN){
                addInterceptor(
                    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            }
        }
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

}
