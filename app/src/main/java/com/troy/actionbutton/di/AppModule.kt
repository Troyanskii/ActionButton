package com.troy.actionbutton.di

import android.app.Application
import android.content.Context
import com.troy.actionbutton.network.RESTClient
import com.troy.actionbutton.network.RESTService
import com.troy.actionbutton.repository.AppDataBase
import com.troy.actionbutton.repository.dao.ActionDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideRESTService(application: Application): Context = application

    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRESTService(client: OkHttpClient): RESTService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://s3-us-west-2.amazonaws.com/androidexam/butto_to_action_config.json")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(RESTService::class.java)
    }

    @Singleton
    @Provides
    fun provideRESTClient(restService: RESTService): RESTClient {
        return RESTClient(restService)
    }

    @Singleton
    @Provides
    fun provideDataBase(context: Context): AppDataBase {
        return AppDataBase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideActionRepositoryDao(dataBase: AppDataBase): ActionDAO {
        return dataBase.actionsDao()
    }
}