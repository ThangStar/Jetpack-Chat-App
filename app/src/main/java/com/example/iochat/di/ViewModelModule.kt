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
    * util: find name class same params in view model (anonitasion) @hiltViewModel @Inject
    *
    * require: return other name class
    *
    * */

    @Provides
    fun getContext(@ApplicationContext context: Context): Context{
        return context
    }
}