package cstjean.mobile.cartefidelite

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cstjean.mobile.cartefidelite.carte.Carte
import cstjean.mobile.cartefidelite.carte.TypeCommerce
import cstjean.mobile.cartefidelite.databinding.FragmentCarteBinding
import java.util.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch

import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.journeyapps.barcodescanner.BarcodeEncoder

// Fragment pour s'occuper d'une seule Carte
class CarteFragment : Fragment() {
    private var _binding: FragmentCarteBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Binding est null, la vue est-elle visible?"
        }

    private val args: CarteFragmentArgs by navArgs()

    private val carteViewModel: CarteViewModel by viewModels {
        CarteViewModelFactory(args.carteId)
    }



    /* A VOIR POUR SHOW LE CODE BAR CRÉÉ AVEC LA FONCTION PLUS BAS
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView: ImageView = findViewById(R.id.barcodeImageView)
        val barcodeText = "123456789" // Remplacez par le numéro de la carte

        val barcodeBitmap = generateCode39Barcode(barcodeText)
        barcodeBitmap?.let {
            imageView.setImageBitmap(it)
        }
    }
    */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                carteViewModel.carte.collect { carte ->
                    carte?.let { updateUi(it) }
                }
            }
        }

        binding.apply {
            carteNom.doOnTextChanged { text, _, _, _ ->
                carteViewModel.updateCarte { oldCarte ->
                    oldCarte.copy(nomCommerce = text.toString())
                }
            }
        }
    }

    private fun updateUi(carte: Carte) {
        binding.apply {
            // Pour éviter une loop infinie avec le update
            if (carteNom.text.toString() != carte.nomCommerce) {
                carteNom.setText(carte.nomCommerce)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Génère un code barre bitmap avec le texte passé en paramètre
    fun generateCode39Barcode(text: String): Bitmap? {
        return try {
            val writer = QRCodeWriter()
            val bitMatrix: BitMatrix = writer.encode(text, BarcodeFormat.CODE_39, 600, 200)
            val barcodeEncoder = BarcodeEncoder()
            barcodeEncoder.createBitmap(bitMatrix)
        } catch (e: WriterException) {
            e.printStackTrace()
            null
        }
    }

}