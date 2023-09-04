package com.jhorgi.cinematica

import android.app.Application
import com.jhorgi.cinematica.core.di.networkModule
import com.jhorgi.cinematica.core.di.repositoryModule
import com.jhorgi.cinematica.di.useCaseModule
import com.jhorgi.cinematica.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)

            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )

        }
    }
}