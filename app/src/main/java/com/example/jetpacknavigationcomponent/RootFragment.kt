package com.example.jetpacknavigationcomponent

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.jetpacknavigationcomponent.databinding.FragmentRootBinding

class RootFragment : Fragment(R.layout.fragment_root) {
    private lateinit var binding: FragmentRootBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRootBinding.bind(view)
        binding.openYellowBoxButton.setOnClickListener {
            openBox(Color.rgb(255, 255, 200), getString(R.string.yellow))
        }
        binding.openGreenBoxButton.setOnClickListener {
            openBox(Color.rgb(200, 255, 200), getString(R.string.green))
        }
//        parentFragmentManager.setFragmentResultListener(
//            BoxFragment.REQUEST_CODE,
//            viewLifecycleOwner
//        ) { _, data ->
//            val number = data.getInt(BoxFragment.EXTRA_RANDOM_NUMBER)
//            Toast.makeText(requireContext(), "$number", Toast.LENGTH_SHORT).show()
//        }
        val liveData = findNavController().currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<Int>(BoxFragment.EXTRA_RANDOM_NUMBER)
        liveData?.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                liveData.value = null
            }
        }
    }

    private fun openBox(color: Int, colorName: String) {
//        findNavController().navigate(
//            R.id.action_rootFragment_to_boxFragment,
//            bundleOf(
//                BoxFragment.ARG_COLOR to color,
//                BoxFragment.ARG_COLOR_NAME to colorName,
//            ),
//            navOptions {
//                anim {
//                    enter = R.anim.enter
//                    exit = R.anim.exit
//                    popEnter = R.anim.pop_enter
//                    popExit = R.anim.pop_exit
//                }
//            }
//        )

        val direction = RootFragmentDirections.actionRootFragmentToBoxFragment(color, colorName)

        findNavController().navigate(
            direction,
            navOptions {
                anim {
                    enter = R.anim.enter
                    exit = R.anim.exit
                    popEnter = R.anim.pop_enter
                    popExit = R.anim.pop_exit
                }
            }
        )
    }
}