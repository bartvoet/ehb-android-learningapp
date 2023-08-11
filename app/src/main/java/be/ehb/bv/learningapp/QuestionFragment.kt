package be.ehb.bv.learningapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import be.ehb.bv.learningapp.databinding.QuestionFragmentBinding
import be.ehb.bv.learningapp.model.ListQuestion
import be.ehb.bv.learningapp.model.Question
import be.ehb.bv.learningapp.viewmodel.QuestionSessionViewModel
import be.ehb.bv.learningapp.viewmodel.QuestionViewModelFactory
import java.util.logging.Logger

class QuestionFragment : Fragment() {

    private var _binding: QuestionFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var questionSession : QuestionSessionViewModel

    companion object QuestionsContainer {
        private val questions: List<Question> =
            listOf(
                ListQuestion("hello", listOf("a", "b")),
                ListQuestion("world", listOf("a", "b", "c")),
                ListQuestion("a", listOf("a", "b", "c")),
                ListQuestion("b", listOf("a", "b", "c"))
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = QuestionFragmentBinding.inflate(inflater, container, false)
        val linearLayout = binding.root.rootView as LinearLayout //

        questionSession = ViewModelProvider(requireActivity(), QuestionViewModelFactory(QuestionsContainer.questions))
                    .get(QuestionSessionViewModel::class.java)

        questionSession.currentQuestion.ask( object : Question.QuestionInterface {
            override fun askListQuestion(question: String, size: Int) {
                binding.questionTextView.text = question
                repeat(size) {
                    addEditText("", linearLayout)
                }
            }
        })

        return binding.root

    }

    private fun addEditText(text: String, linearLayout: LinearLayout) {
        val et = EditText(activity)
        et.setText(text)
        et.setOnClickListener { Log.i("test", text) }
        et.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        linearLayout.addView(et)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            if(!questionSession.picker.questionsRemaining()) {
                Logger.getLogger("hello").info("ok")
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            } else {
                questionSession.selectQuestion()
                findNavController().navigate(R.id.action_FirstFragment_to_FirstFragment)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}