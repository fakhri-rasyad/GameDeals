package com.d121211017.gamedeals

import android.app.Application
import com.d121211017.gamedeals.data.DefaultAppContainer
import com.d121211017.gamedeals.data.GameDealsContainer

class GameDealsApplication():Application() {
    lateinit var container : GameDealsContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}