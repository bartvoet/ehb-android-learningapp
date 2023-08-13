package be.ehb.bv.learning.app.service


import android.app.Service
import android.content.Intent
import android.util.Log
import android.os.Binder
import android.os.IBinder
import be.ehb.bv.learning.core.model.ListQuestion
import be.ehb.bv.learning.core.model.Question

class QuestionResourceService : Service() {

    private companion object QuestionsContainer {
        private val questions: List<Question> =
            listOf(
                ListQuestion("hello", listOf("a", "b")),
                ListQuestion("world", listOf("a", "b", "c")),
                ListQuestion("a", listOf("a", "b", "c")),
                ListQuestion("b", listOf("a", "b", "c"))
            )
    }

    public fun getQuestionResources(): List<String> = listOf("network", "programming")

    public fun getQuestionsForResource(questionResources: String) : List<Question> {
        Log.i("test", questionResources)
        return questions;
    }

    inner class LocalBinder : Binder() {
        fun getService(): QuestionResourceService = this@QuestionResourceService
    }

    private val binder = LocalBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}