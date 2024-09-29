package cstjean.mobile.cartefidelite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cstjean.mobile.cartefidelite.databinding.FragmentCartesListBinding
import cstjean.mobile.cartefidelite.faker.CarteFaker
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.log

private const val TAG = "CartesListFragment"

class CartesListFragment : Fragment() {
    private var _binding: FragmentCartesListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Binding est null. La vue est visible ??"
        }
    private val cartesListViewModel: CartesListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartesListBinding.inflate(inflater, container, false)

        binding.cartesRecyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                cartesListViewModel.cartes.collect { cartes ->
                    binding.cartesRecyclerView.adapter = CartesListAdapter(cartes) { carteId ->
                        Log.d(TAG, carteId.toString())
                        findNavController().navigate(CartesListFragmentDirections.showCarteDetail(carteId))
                    }
                }
            }

            // Créé le bouton ajouter carte, qui appelle le faker pour générer une seule carte.
            binding.addCarteButton.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    val newCarte = CarteFaker.generateFakeCarte()
                    cartesListViewModel.addCarte(newCarte)
                }
            }
        }
    }
}