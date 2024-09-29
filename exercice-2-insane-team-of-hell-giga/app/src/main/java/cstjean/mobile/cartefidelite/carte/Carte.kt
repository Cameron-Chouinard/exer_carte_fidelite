package cstjean.mobile.cartefidelite.carte

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


// Représente une Carte dans la database
@Entity
data class Carte(
    @PrimaryKey val id: UUID,
    val nomCommerce: String,
    val noCarte: String,
    val typeCommerce: TypeCommerce,
    val couleurFond: String
) {

    //fait crasher lapp donc je le désactive
    /*init {
        // Valide que le format de couleur est bien en hexadecimal
        require(couleurFond.matches(Regex("^#([0-9A-Fa-f]{6}|[0-9A-Fa-f]{3})$"))) {
            "La couleur doit être en format Hexadécimal"
        }
    }*/
}

enum class TypeCommerce {
    RESTAURANT,
    DIVERTISSEMENT,
    EPICERIE,
    AUTRE
}