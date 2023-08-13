package be.ehb.bv.learning.app.session

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import be.ehb.bv.learning.app.databinding.QuestionActionFragmentBinding

class ActionFragment : Fragment() {
    private lateinit var controller: QuestionController
    private lateinit var binding: QuestionActionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = QuestionActionFragmentBinding.inflate(inflater, container, false)
        binding.nextQuestion.setOnClickListener {
            controller.nextQuestion()
        }
        binding.stopQuestion.setOnClickListener {
            controller.stopSession()
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controller = context as QuestionController
    }
}