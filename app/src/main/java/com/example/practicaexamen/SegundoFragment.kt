package com.example.practicaexamen

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.practicaexamen.databinding.FragmentSegundoBinding

class SegundoFragment : Fragment() {

    private var _binding: FragmentSegundoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.goto_firstFragment)
        }
        // Inflate the layout for this fragment
        _binding = FragmentSegundoBinding.inflate(inflater, container, false)
        return binding.root
    }

    var isPlaying = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageResource = arguments?.getInt("card") ?: R.drawable.ic_launcher_background
        binding.imageView.setImageResource(imageResource)

        val animator = TranslateAnimation(-1000f,0f,0f,0f)

        animator.duration = 1000

        binding.imageView.startAnimation(animator)

        binding.FAB.setOnClickListener {
            val anim = if (isPlaying) {
                AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.ic_play_to_pause
                ) as AnimatedVectorDrawable
            } else {
                AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.ic_pause_to_play
                ) as AnimatedVectorDrawable
            }
            (it as ImageView).setImageDrawable(anim)
            anim.start()
            isPlaying = !isPlaying
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}