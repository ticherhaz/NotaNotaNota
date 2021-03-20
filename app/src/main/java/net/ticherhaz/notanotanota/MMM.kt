package net.ticherhaz.notanotanota

import android.app.Application

class MMM : Application() {

    override fun onCreate() {
        super.onCreate()
        ZZZ.init(this)
    }
}