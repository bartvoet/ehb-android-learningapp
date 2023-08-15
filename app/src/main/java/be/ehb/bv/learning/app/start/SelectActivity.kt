package be.ehb.bv.learning.app.start

import android.R
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import be.ehb.bv.learning.app.databinding.ActivitySelectBinding
import be.ehb.bv.learning.app.service.QuestionResourceService
import be.ehb.bv.learning.app.session.QuestionActivity
import be.ehb.bv.learning.app.support.IntentConstants
import be.ehb.bv.learning.app.support.SampleData.TEST_URL
import java.util.Arrays.asList

class SelectActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectBinding

    private fun getSelectedResource() = binding.selectSpinner.selectedItem.toString()

    private fun reloadSpinnerOnUI(list: List<String>) {
        Log.i("test", list.toString())
        runOnUiThread {
            val adapter: ArrayAdapter<*> = getArrayAdapterFromResources()
            binding.selectSpinner.adapter = adapter
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding = ActivitySelectBinding.inflate(layoutInflater)

        binding.loadUrl.setText(TEST_URL)
        binding.selectButton.setOnClickListener {
            val launchSession = Intent(this, QuestionActivity::class.java)
            launchSession.putExtra(
                IntentConstants.INTENT_START_SESSION_RESOURCE_ARG,
                getSelectedResource())
            startActivity(launchSession)
        }
        val message = getString(be.ehb.bv.learning.app.R.string.resourcesloaded)
        val loadUrl = binding.loadUrl.text.toString()

        binding.loadButton.setOnClickListener {
            boundService?.load(loadUrl, this::reloadSpinnerOnUI )
            Toast.makeText(this, message, Toast.LENGTH_LONG ).show()
        }
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, QuestionResourceService::class.java)
        startService(intent)
        bindService(intent, boundServiceConnection, BIND_AUTO_CREATE)
    }

    private var boundService : QuestionResourceService? = null
    private var isBound = false

    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binderBridge: QuestionResourceService.LocalBinder =
                service as QuestionResourceService.LocalBinder
            boundService = binderBridge.getService()
            isBound = true
            Log.i("test", "connected!!!!")

            val adapter: ArrayAdapter<*> = getArrayAdapterFromResources()
            binding.selectSpinner.adapter = adapter
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
            boundService = null
        }
    }

    private fun getArrayAdapterFromResources(): ArrayAdapter<*> {
        val list: List<String> = boundService?.getQuestionResources() ?: asList("")
        val adapter: ArrayAdapter<*> =
            ArrayAdapter<String>(
                this@SelectActivity,
                R.layout.simple_spinner_item,
                list.toTypedArray()
            )
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        return adapter
    }
}