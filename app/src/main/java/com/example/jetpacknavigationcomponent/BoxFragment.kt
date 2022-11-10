package com.example.jetpacknavigationcomponent

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jetpacknavigationcomponent.databinding.FragmentBoxBinding
import kotlin.random.Random

class BoxFragment : Fragment(R.layout.fragment_box) {
    private lateinit var binding: FragmentBoxBinding

    private val args: BoxFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBoxBinding.bind(view)
//        val color = requireArguments().getInt(ARG_COLOR)
//        binding.root.setBackgroundColor(color)

//val color = BoxFragmentArgs.fromBundle(requireArguments()).color

        val color = args.color
        binding.root.setBackgroundColor(color)

        binding.goBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.openSecretButton.setOnClickListener {
//            findNavController().navigate(R.id.action_boxFragment_to_secretFragment)
            findNavController().navigate(BoxFragmentDirections.actionBoxFragmentToSecretFragment())
        }
        binding.generateNumberButton.setOnClickListener {
            val number = Random.nextInt(100)
            findNavController().previousBackStackEntry?.savedStateHandle
                ?.set(EXTRA_RANDOM_NUMBER, number)
//            parentFragmentManager.setFragmentResult(
//                REQUEST_CODE,
//                bundleOf(EXTRA_RANDOM_NUMBER to number)
//            )
            findNavController().popBackStack()

        }
    }

    companion object {
        const val REQUEST_CODE = "RANDOM_NUMBER"
        const val EXTRA_RANDOM_NUMBER = "EXTRA_RANDOM_NUMBER"
//        const val ARG_COLOR = "color"
//        const val ARG_COLOR_NAME = "colorName"
    }
}