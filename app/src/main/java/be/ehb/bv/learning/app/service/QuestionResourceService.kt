package be.ehb.bv.learning.app.service


import android.app.Service
import android.content.Intent
import android.util.Log
import android.os.Binder
import android.os.IBinder
import be.ehb.bv.learning.app.support.SampleData.SAMPLE_QUESTIONS
import be.ehb.bv.learning.core.model.Question

class QuestionResourceService : Service() {

    init {
        Log.i("test", "service created")
    }

    fun getQuestionResources(): List<String> = SAMPLE_QUESTIONS.keys.toList()

    fun getQuestionsForResource(questionResources: String) : List<Question> {
        Log.i("test", questionResources)
        return SAMPLE_QUESTIONS[questionResources]?:listOf()
    }

    inner class LocalBinder : Binder() {
        fun getService(): QuestionResourceService = this@QuestionResourceService
    }

    private val binder = LocalBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}