package cstjean.mobile.cartefidelite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.util.UUID

class MainActivity : AppCompatActivity() {
    private lateinit var carteViewModel: CarteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val carteId = UUID.randomUUID()

        carteViewModel = ViewModelProvider(this, CarteViewModelFactory(carteId))[CarteViewModel::class.java]
        carteViewModel.generateAndSaveFakeCartes(20)

    }
}