package be.ehb.bv.learning.app.session

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import be.ehb.bv.learning.app.R
import be.ehb.bv.learning.app.databinding.ActivityQuestionBinding
import be.ehb.bv.learning.app.service.QuestionResourceService
import be.ehb.bv.learning.app.session.viewmodel.QuestionSessionViewModel
import be.ehb.bv.learning.app.session.viewmodel.QuestionViewModelFactory
import be.ehb.bv.learning.core.model.ListQuestion
import be.ehb.bv.learning.core.model.Question
import java.util.*
import java.util.logging.Logger

class QuestionActivity : AppCompatActivity(), QuestionController {

    private lateinit var qi: Question.QuestionInterface

     override fun screenReady(qi: Question.QuestionInterface) {
        this.qi = qi
        questionSession = ViewModelProvider(this)[QuestionSessionViewModel::class.java]
        questionSession.selectQuestion()
        questionSession.currentQuestion.ask(qi)
    }

    override fun nextQuestion() {
        Logger.getLogger("hello").info("a")
        if(questionSession.currentQuestion
                .validate(this.qi)
                .isOK()) {
            questionSession.markQuestionAsFinished()
        }

        if(!questionSession.picker.questionsRemaining()) {
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_FirstFragment_to_SecondFragment)
            findNavController(R.id.nav_host_fragment_action).navigate(R.id.action_ActionFragment_to_EndActionFragment)
        } else {
            questionSession.selectQuestion()
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_FirstFragment_to_FirstFragment)
        }
    }

    private lateinit var questionSession: QuestionSessionViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        questionSession = ViewModelProvider(
            this,
            QuestionViewModelFactory()
        )[QuestionSessionViewModel::class.java]

        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
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

            val newQuestions = boundService?.getQuestionsForResource("networking")?:listOf()
            questionSession.initQuestions(newQuestions)
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
            boundService = null
        }
    }
}