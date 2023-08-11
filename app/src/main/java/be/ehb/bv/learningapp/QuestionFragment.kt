package be.ehb.bv.learningapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.view.children
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

    inner class FragmentQuestionInterface() : Question.QuestionInterface {
        private val linearLayout = binding.root.rootView as LinearLayout

        override fun askListQuestion(question: String, size: Int) {
            binding.questionTextView.text = question
            repeat(size) {
                addEditText("", linearLayout)
            }
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

        override fun getFeedback(): List<String> =
            binding.root.children
                .filterIsInstance( EditText::class.java )
                .map { it.text.toString()}
                .toList()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = QuestionFragmentBinding.inflate(inflater, container, false)

        questionSession = ViewModelProvider(
            requireActivity(),
            QuestionViewModelFactory(QuestionsContainer.questions)
        )[QuestionSessionViewModel::class.java]

        questionSession.currentQuestion.ask( FragmentQuestionInterface())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            if(!questionSession.picker.questionsRemaining()) {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            } else {
                questionSession.selectQuestion()
                questionSession.markQuestionAsFinished()
                findNavController().navigate(R.id.action_FirstFragment_to_FirstFragment)
            }
        }
    }
}