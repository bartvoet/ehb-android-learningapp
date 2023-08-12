package be.ehb.bv.learning.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import be.ehb.bv.learning.app.viewmodel.QuestionSessionViewModel
import be.ehb.bv.learning.app.R


import be.ehb.bv.learning.app.databinding.EndFragmentBinding

class EndFragment : Fragment() {

    private var _binding: EndFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var questionSession : QuestionSessionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        questionSession = ViewModelProvider(requireActivity())
            .get(QuestionSessionViewModel::class.java)
        _binding = EndFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            questionSession.renew()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}