package cstjean.mobile.cartefidelite

import android.app.Application

class CarteFideliteApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CarteRepository.initialize(this)
    }
}
