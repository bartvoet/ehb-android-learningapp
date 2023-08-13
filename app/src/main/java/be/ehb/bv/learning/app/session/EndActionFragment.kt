package be.ehb.bv.learning.app.session

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import be.ehb.bv.learning.app.R
import be.ehb.bv.learning.app.databinding.EndActionFragmentBinding
import be.ehb.bv.learning.app.session.viewmodel.QuestionSessionViewModel

class EndActionFragment : Fragment() {
    private lateinit var questionSession: QuestionSessionViewModel

    private lateinit var binding: EndActionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EndActionFragmentBinding.inflate(inflater, container, false)
        questionSession = ViewModelProvider(requireActivity())[QuestionSessionViewModel::class.java]
        binding.buttonSecond.setOnClickListener {
            requireActivity().findNavController(R.id.nav_host_fragment_content_main)
                .navigate(R.id.action_SecondFragment_to_FirstFragment)
            findNavController().navigate(R.id.action_EndActionFragment_to_StartActionFragment)
            questionSession.renew()
        }
        return binding.root
    }
}