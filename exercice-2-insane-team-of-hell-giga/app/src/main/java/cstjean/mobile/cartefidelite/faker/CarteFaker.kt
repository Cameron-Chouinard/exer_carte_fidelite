package cstjean.mobile.cartefidelite.faker

import cstjean.mobile.cartefidelite.carte.Carte
import cstjean.mobile.cartefidelite.carte.TypeCommerce
import java.util.UUID
import com.github.javafaker.Faker

object CarteFaker {
    private val faker = Faker()

    fun generateFakeCarte(): Carte {
        val nomCommerce = faker.company().name()
        val noCarte = faker.number().digits(10)
        val typeCommerce = TypeCommerce.values().random()
        val couleurFond = "#${faker.color().hex()}"

        return Carte(
            id = UUID.randomUUID(),
            nomCommerce = nomCommerce,
            noCarte = noCarte,
            typeCommerce = typeCommerce,
            couleurFond = couleurFond
        )
    }

    fun generateFakeCartes(count: Int): List<Carte> {
        return List(count) { generateFakeCarte() }
    }
}