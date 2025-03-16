package com.rentx.dragonballwiki

import android.app.Application
import com.rentx.dragonballwiki.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class DragonBallApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DragonBallApplication)
            modules(appModule)
        }

    }
}