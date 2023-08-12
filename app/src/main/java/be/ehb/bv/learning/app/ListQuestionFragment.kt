package be.ehb.bv.learning.app

import android.content.Context
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
import be.ehb.bv.learning.core.model.ListQuestion
import be.ehb.bv.learning.core.model.Question
import be.ehb.bv.learning.app.viewmodel.QuestionSessionViewModel
import be.ehb.bv.learning.app.viewmodel.QuestionViewModelFactory
import be.ehb.bv.learning.app.databinding.ListQuestionFragmentBinding

class ListQuestionFragment : Fragment() {

    private var _binding: ListQuestionFragmentBinding? = null

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
        private val linearLayout = binding.root

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
        _binding = ListQuestionFragmentBinding.inflate(inflater, container, false)

        questionSession = ViewModelProvider(
            requireActivity(),
            QuestionViewModelFactory(questions)
        )[QuestionSessionViewModel::class.java]

        questionSession.selectQuestion()
        questionSession.currentQuestion.ask( FragmentQuestionInterface())

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFirst.setOnClickListener {
            if(questionSession.currentQuestion
                    .validate(FragmentQuestionInterface())
                    .isOK()) {
                questionSession.markQuestionAsFinished()
            }

            if(!questionSession.picker.questionsRemaining()) {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            } else {
                questionSession.selectQuestion()
                findNavController().navigate(R.id.action_FirstFragment_to_FirstFragment)
            }
        }


    }
}