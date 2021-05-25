package com.example.newmovieapp

import android.app.Application
import com.example.newmovieapp.di.RepositoryProvider
import com.example.newmovieapp.network.NetworkProvider

class Starter : Application() {

    override fun onCreate() {
        super.onCreate()
        NetworkProvider.initialize()
        RepositoryProvider.initialize()
    }
}