package be.ehb.bv.learning.app.session

import android.content.ComponentName
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.icu.text.DateTimePatternGenerator.PatternInfo.OK
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import be.ehb.bv.learning.app.R
import be.ehb.bv.learning.app.databinding.ActivityQuestionBinding
import be.ehb.bv.learning.app.service.QuestionResourceService
import be.ehb.bv.learning.app.session.viewmodel.QuestionSessionViewModel
import be.ehb.bv.learning.app.session.viewmodel.QuestionViewModelFactory
import be.ehb.bv.learning.app.start.SelectActivity
import be.ehb.bv.learning.app.support.IntentConstants
import be.ehb.bv.learning.core.model.Question
import java.util.logging.Logger

class QuestionActivity : AppCompatActivity(), QuestionController {

    private lateinit var qi: Question.QuestionInterface
    private lateinit var questionSession: QuestionSessionViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityQuestionBinding

    override fun screenReady(qi: Question.QuestionInterface) {
        this.qi = qi
        questionSession = ViewModelProvider(this)[QuestionSessionViewModel::class.java]
        questionSession.selectQuestion()
        questionSession.currentQuestion.ask(qi)
    }

    override fun nextQuestion() {
        validateAnswer()
        goToNextStep()
    }

    private fun validateAnswer() {
        if (questionSession.currentQuestion.validate(this.qi).isOK()) {
            questionSession.markQuestionAsFinished()
            questionSession.currentResult.value = getString(R.string.OK)
        } else {
            questionSession.currentResult.value = getString(R.string.NOK)
        }

        val picker = questionSession.picker
        questionSession.currentStatus.value =
            getString(R.string.remaining, picker.remainingItems,picker.totalItems)
    }

    private fun goToNextStep() {
        if (!questionSession.picker.questionsRemaining()) {
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_FirstFragment_to_SecondFragment)
            findNavController(R.id.nav_host_fragment_action).navigate(R.id.action_ActionFragment_to_EndActionFragment)
        } else {
            questionSession.selectQuestion()
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_FirstFragment_to_FirstFragment)
        }
    }

    override fun stopSession() {
        startActivity(Intent(this, SelectActivity::class.java))
    }

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

    private fun getQuestionResourceName() = intent
                .getStringExtra(
                    IntentConstants.INTENT_START_SESSION_RESOURCE_ARG,
                )?:""

    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binderBridge: QuestionResourceService.LocalBinder =
                service as QuestionResourceService.LocalBinder
            boundService = binderBridge.getService()
            isBound = true
            Log.i("test", "connected!!!!")

            val newQuestions = boundService?.
                getQuestionsForResource(getQuestionResourceName())?:listOf()
            questionSession.initQuestions(newQuestions)
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
            boundService = null
        }
    }
}