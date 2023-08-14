package be.ehb.bv.learning.app.session

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import be.ehb.bv.learning.app.databinding.SessionStatusFragmentBinding
import be.ehb.bv.learning.app.session.viewmodel.QuestionSessionViewModel

class SessionStatusFragment : Fragment() {

    private lateinit var questionSession: QuestionSessionViewModel
    private lateinit var binding: SessionStatusFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SessionStatusFragmentBinding.inflate(inflater, container, false)
        questionSession = ViewModelProvider(requireActivity())[QuestionSessionViewModel::class.java]

        questionSession.currentResult.observe(viewLifecycleOwner)
            { newResult -> binding.result.text = newResult }

        questionSession.currentStatus.observe(viewLifecycleOwner)
            { newStatus -> binding.progressIndicator.text = newStatus }

        questionSession.currentAnswers.observe(viewLifecycleOwner)
            { correction -> binding.correction.text = correction }

        return binding.root
    }
}