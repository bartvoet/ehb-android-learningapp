package be.ehb.bv.learningapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import java.util.logging.Logger


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    // This property is only valid between onCreateView and
    // onDestroyView.



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = inflater.inflate(R.layout.fragment_first, container, false)
        val linearLayout = binding.findViewById<LinearLayout>(R.id.linearLayout)
        //val rowView = inflater.inflate(R.layout.field, null)
        //linearLayout.addView(rowView)
        // Add the new row before the add field button.
        for(i in 1..5) {
            addEditText(i.toString(), linearLayout)
        }
        return binding

    }

    private fun addEditText(text: String, linearLayout: LinearLayout) {
        val ET = EditText(activity)
        ET.setText("LOL")
        ET.setOnClickListener { Log.i("test", text) }
        ET.id = 5
        ET.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        linearLayout.addView(ET)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //findViewById(R.id.linearLayout);
        //linearLayout.addView()

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}