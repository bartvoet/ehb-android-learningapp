package be.ehb.bv.learning.app.start

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import be.ehb.bv.learning.app.databinding.ActivitySelectBinding
import be.ehb.bv.learning.app.service.QuestionResourceService
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

    private var boundService : QuestionResourceService? = null
    private var isBound = false

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, QuestionResourceService::class.java)
        startService(intent)
        bindService(intent, boundServiceConnection, BIND_AUTO_CREATE)
    }

    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binderBridge: QuestionResourceService.LocalBinder =
                service as QuestionResourceService.LocalBinder
            boundService = binderBridge.getService()
            isBound = true
            Log.i("test", "connected!!!!")
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
            boundService = null
        }
    }
}