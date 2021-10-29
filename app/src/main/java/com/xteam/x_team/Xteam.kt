package com.xteam.x_team

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.error.KoinAppAlreadyStartedException
import org.koin.core.logger.Level
import org.koin.core.module.Module

class Xteam: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
    private fun initKoin() {
        try {
            startKoin {
                androidLogger(Level.ERROR)
                androidContext(applicationContext)//Application's context
                val modules = mutableListOf<Module>().apply {
                    addAll(appModules)

                }
                modules(modules)
            }
        } catch (error: KoinAppAlreadyStartedException) {
            //Timber.e(error.localizedMessage)
        }
    }
}