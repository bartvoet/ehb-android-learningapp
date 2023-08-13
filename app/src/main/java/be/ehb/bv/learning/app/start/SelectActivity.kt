package be.ehb.bv.learning.app.start

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import be.ehb.bv.learning.app.databinding.ActivitySelectBinding
import be.ehb.bv.learning.app.session.QuestionActivity

class SelectActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        binding.selectButton.setOnClickListener {
            startActivity(Intent(this, QuestionActivity::class.java))
        }
        setContentView(binding.root)
    }
}