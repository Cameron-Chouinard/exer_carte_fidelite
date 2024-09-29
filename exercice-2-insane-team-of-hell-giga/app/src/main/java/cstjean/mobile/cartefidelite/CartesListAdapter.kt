package cstjean.mobile.cartefidelite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cstjean.mobile.cartefidelite.carte.Carte
import cstjean.mobile.cartefidelite.databinding.ListItemCartesBinding
import java.util.UUID

class CarteHolder(val binding: ListItemCartesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(carte: Carte, onCarteClicked: (carteId: UUID) -> Unit) {
        binding.carteNom.text = carte.nomCommerce
        binding.carteCommerce.text = carte.typeCommerce.toString()
        binding.numeroCarte.text = carte.noCarte
        binding.root.setOnClickListener {
            onCarteClicked(carte.id)
        }
    }


}

class CartesListAdapter(
    private val cartes: List<Carte>,
    private val onCarteClicked: (carteId: UUID) -> Unit) :
            RecyclerView.Adapter<CarteHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarteHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCartesBinding.inflate(inflater, parent, false)
        return CarteHolder(binding)
    }
    override fun onBindViewHolder(holder: CarteHolder, position: Int) {
        val carte = cartes[position]
        holder.bind(carte, onCarteClicked)
    }

    override fun getItemCount() = cartes.size
}
