package cstjean.mobile.cartefidelite.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cstjean.mobile.cartefidelite.carte.Carte
import kotlinx.coroutines.flow.Flow
import java.util.UUID

// Data Access Object d'une entité Carte
@Dao
interface CarteDao {
    @Query("SELECT * FROM carte")
    fun getCartes(): Flow<List<Carte>>

    @Query("SELECT * FROM carte WHERE id=(:id)")
    suspend fun getCarte(id: UUID): Carte

    @Insert
    suspend fun addCarte(carte: Carte)

    @Update
    suspend fun updateCarte(carte: Carte)
}