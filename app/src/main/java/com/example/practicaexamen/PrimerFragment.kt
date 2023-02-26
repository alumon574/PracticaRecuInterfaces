package com.example.practicaexamen

import android.app.slice.Slice
import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.util.Log
import android.view.*
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.picturesviewer.adapters.AdaptadorCards
import com.example.picturesviewer.adapters.Card
import com.example.practicaexamen.databinding.FragmentPrimerBinding



class PrimerFragment : Fragment() {

    private var _binding: FragmentPrimerBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentPrimerBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = binding.recyclerFotos

        val cardAdapter = AdaptadorCards(arrayListOf(
            Card(R.drawable.image1),
            Card(R.drawable.image2),
            Card(R.drawable.image3),
            Card(R.drawable.image4),
            Card(R.drawable.image5),
            Card(R.drawable.image6),
            Card(R.drawable.image7),
            Card(R.drawable.image8)
        )
        )

        var modeCallback: ActionMode.Callback = object : ActionMode.Callback{
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                val id = item?.itemId
                when(id){
                    R.id.editar -> {
                        Log.i("MainActivity","Editar")
                        mode?.finish()
                    }
                    R.id.borrar -> {
                        Log.i("MainActivity","Borrar")
                        mode?.finish()
                    }
                    R.id.compartir -> {
                        Log.i("MainActivity","Compartir")
                        mode?.finish()
                    }
                    else -> return false
                }
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                var mode = mode
                mode = null
            }

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                mode?.setTitle("Options")
                mode?.menuInflater?.inflate(R.menu.menu_context, menu)
                return true
            }
        }
        recycler.adapter = cardAdapter
        recycler.layoutManager = GridLayoutManager(context,2)
        cardAdapter.onLongClick = {
            it -> it.startActionMode(modeCallback)
        }

        cardAdapter.onClick = { view, card ->
            findNavController().navigate(
                R.id.goto_secondFragment,
            bundleOf("card" to card.foto)
            )
        }

        var slide = Slide()

        slide.slideEdge = Gravity.RIGHT

        slide.duration = 250

        exitTransition = slide

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}