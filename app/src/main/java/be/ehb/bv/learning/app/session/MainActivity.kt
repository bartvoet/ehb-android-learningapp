package be.ehb.bv.learning.app.session

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import be.ehb.bv.learning.app.R
import be.ehb.bv.learning.app.databinding.ActivityMainBinding
import be.ehb.bv.learning.app.session.viewmodel.QuestionSessionViewModel
import be.ehb.bv.learning.app.session.viewmodel.QuestionViewModelFactory
import be.ehb.bv.learning.core.model.ListQuestion
import be.ehb.bv.learning.core.model.Question
import java.util.logging.Logger

class MainActivity : AppCompatActivity(), QuestionController {

    companion object QuestionsContainer {
        private val questions: List<Question> =
            listOf(
                ListQuestion("hello", listOf("a", "b")),
                ListQuestion("world", listOf("a", "b", "c")),
                ListQuestion("a", listOf("a", "b", "c")),
                ListQuestion("b", listOf("a", "b", "c"))
            )
    }

    private lateinit var  qi: Question.QuestionInterface

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
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        questionSession = ViewModelProvider(
            this,
            QuestionViewModelFactory(questions)
        )[QuestionSessionViewModel::class.java]

        binding = ActivityMainBinding.inflate(layoutInflater)
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
}