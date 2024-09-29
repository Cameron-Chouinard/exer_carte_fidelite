package cstjean.mobile.cartefidelite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import cstjean.mobile.cartefidelite.carte.Carte
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import cstjean.mobile.cartefidelite.faker.CarteFaker

class CarteViewModel(carteId: UUID) : ViewModel() {
    private val carteRepository = CarteRepository.get()
    private val _carte: MutableStateFlow<Carte?> = MutableStateFlow(null)
    val carte: StateFlow<Carte?> = _carte

    init {
        viewModelScope.launch {
            _carte.value = carteRepository.getCarte(carteId)
        }
    }

    fun generateAndSaveFakeCartes(count: Int) {
        viewModelScope.launch {
            val fakeCartes = CarteFaker.generateFakeCartes(count)
            carteRepository.addCartes(fakeCartes)
        }
    }

    fun updateCarte(onUpdate: (Carte) -> Carte) {
        _carte.update { oldCarte ->
            oldCarte?.let { onUpdate(it) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        carte.value?.let { carteRepository.updateCarte(it) }
    }
}

class CarteViewModelFactory(private val carteId: UUID) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CarteViewModel(carteId) as T
    }
}