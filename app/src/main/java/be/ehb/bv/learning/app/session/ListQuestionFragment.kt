package be.ehb.bv.learning.app.session

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
import be.ehb.bv.learning.app.databinding.ListQuestionFragmentBinding
import be.ehb.bv.learning.core.model.Question

class ListQuestionFragment : Fragment() {

    private lateinit var binding: ListQuestionFragmentBinding

    private lateinit var qc : QuestionController

    inner class FragmentQuestionInterface() : Question.QuestionInterface {

        override fun askListQuestion(question: String, size: Int) {
            binding.questionTextView.text = question
            repeat(size) {
                addEditText("", binding.root)
            }
        }

        private fun addEditText(text: String, linearLayout: LinearLayout) {
            val et = EditText(activity)
            et.setText(text)
            et.setOnClickListener { Log.i("test", text) }
            et.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        qc = context as QuestionController
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListQuestionFragmentBinding.inflate(inflater, container, false)
        qc.screenReady(FragmentQuestionInterface())
        return binding.root
    }

}