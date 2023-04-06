package com.example.iochat.di

import android.content.Context
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    /*
    *
    * find and inject to params in your view model
    *
    * return require other
    *
    * */

    @Provides
    fun getContext(@ApplicationContext context: Context): Context{
        Log.d("SSS", "U BINDED CONTEXT")
        return context
    }
}