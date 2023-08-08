package be.ehb.bv.learningapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import be.ehb.bv.learningapp.databinding.FragmentFirstBinding
import be.ehb.bv.learningapp.databinding.FragmentSecondBinding
import java.util.logging.Logger

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //_binding = inflater.inflate(R.layout.fragment_first, container, false)
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val linearLayout = binding.root.rootView as LinearLayout //

        for(i in 1..5) {
            addEditText(i.toString(), linearLayout)
        }
        return binding.root

    }

    private fun addEditText(text: String, linearLayout: LinearLayout) {
        val ET = EditText(activity)
        ET.setText("LOL!!")
        ET.setOnClickListener { Log.i("test", text) }
        ET.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        linearLayout.addView(ET)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}