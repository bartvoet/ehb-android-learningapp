package be.ehb.bv.learning.app.session

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import be.ehb.bv.learning.app.R
import be.ehb.bv.learning.app.databinding.StartActionFragmentBinding

class StartActionFragment : Fragment() {

    private lateinit var binding: StartActionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StartActionFragmentBinding.inflate(inflater, container, false)
        binding.startQuestion.setOnClickListener {
            requireActivity().findNavController(R.id.nav_host_fragment_content_main)
                .navigate(R.id.action_StartFragment_to_SecondFragment)
            findNavController().navigate(R.id.action_StartFragment_to_ActionFragment)
        }
        return binding.root
    }
}