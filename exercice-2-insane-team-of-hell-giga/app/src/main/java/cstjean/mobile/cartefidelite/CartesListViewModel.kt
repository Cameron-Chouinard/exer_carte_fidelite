package cstjean.mobile.cartefidelite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cstjean.mobile.cartefidelite.carte.Carte
import cstjean.mobile.cartefidelite.carte.TypeCommerce
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random

private const val TAG = "CartesListViewModel"

class CartesListViewModel : ViewModel() {
    private val carteRepository = CarteRepository.get()
    private val _cartes: MutableStateFlow<List<Carte>> = MutableStateFlow(emptyList())
    val cartes: StateFlow<List<Carte>> = _cartes

  init {
        viewModelScope.launch {
            loadCartes()

            carteRepository.getCartes().collect {
                _cartes.value = it
            }
        }
    }

    suspend fun loadCartes() {
        // Données de tests
        for (i in 0 until 10) {
            val carte = Carte(
                UUID.randomUUID(),
                "Commerce #$i",
                "12345",
                TypeCommerce.AUTRE,
                "#CD0000"
            )

            addCarte(carte)
        }
    }

    suspend fun addCarte(carte: Carte) {
        carteRepository.addCarte(carte)
    }
}