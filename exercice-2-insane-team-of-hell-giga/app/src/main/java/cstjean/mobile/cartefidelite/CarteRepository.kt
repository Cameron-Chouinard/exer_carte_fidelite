package cstjean.mobile.cartefidelite
import android.content.Context
import androidx.room.Room
import cstjean.mobile.cartefidelite.carte.Carte
import cstjean.mobile.cartefidelite.database.CarteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.UUID

private const val DATABASE_NAME = "carte-database"

class CarteRepository private constructor(
    context: Context,
    private val coroutineScope: CoroutineScope = GlobalScope
) {
    private val database: CarteDatabase =
        Room
            .databaseBuilder(
                context.applicationContext,
                CarteDatabase::class.java,
                DATABASE_NAME,
            )
            .createFromAsset(DATABASE_NAME)
            .build()

    fun updateCarte(carte: Carte) {
        coroutineScope.launch {
            database.carteDao().updateCarte(carte)
        }
    }

    fun getCartes(): Flow<List<Carte>> = database.carteDao().getCartes()

    suspend fun getCarte(id: UUID): Carte = database.carteDao().getCarte(id)

    suspend fun addCarte(carte: Carte) = database.carteDao().addCarte(carte)

    suspend fun addCartes(cartes: List<Carte>) {
        for (carte in cartes) {
            addCarte(carte)
        }
    }

    companion object {
        private var INSTANCE: CarteRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CarteRepository(context)
            }
        }
        fun get(): CarteRepository =
            INSTANCE
                ?: throw IllegalStateException("CarteRepository doit être initialisé...")
    }

}