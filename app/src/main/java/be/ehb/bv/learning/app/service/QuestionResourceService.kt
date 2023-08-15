package be.ehb.bv.learning.app.service


import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import be.ehb.bv.learning.app.storage.QuestionResource
import be.ehb.bv.learning.app.storage.QuestionResourceDatabase
import be.ehb.bv.learning.app.support.SampleData.SAMPLE_QUESTIONS
import be.ehb.bv.learning.core.model.ListQuestion
import be.ehb.bv.learning.core.model.Question
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import java.io.BufferedReader
import java.io.StringReader
import java.util.*
import kotlin.concurrent.thread

class QuestionResourceService : Service() {

    private val dictionaryOfQuestions = SAMPLE_QUESTIONS.toMutableMap()

    init {
        Log.i("test", "service created")
    }

    fun getQuestionResources(): List<String> = dictionaryOfQuestions.keys.toList()

    fun getQuestionsForResource(questionResources: String) : List<Question> {
        Log.i("test", questionResources)
        return dictionaryOfQuestions[questionResources]?:listOf()
    }

    inner class LocalBinder : Binder() {
        fun getService(): QuestionResourceService = this@QuestionResourceService
    }

    private val binder = LocalBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    val client: OkHttpClient = OkHttpClient();
    private val questionResourceDatabase
        by lazy { QuestionResourceDatabase.getDatabase(this).questionResourceDao() }

    fun splitCsv(s:String) = s.split(';', ignoreCase = false)
    fun load(url: String, updateValues: (List<String>) -> Unit) {
        thread {
            val request : Request = Request.Builder().url(url).build();

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                for ((name, value) in response.headers) {
                    println("$name: $value")
                }

                val csvContent = response.body!!.string()
                val reader = BufferedReader(StringReader(csvContent))

                val name = splitCsv(reader.readLine())[0]
                val list : List<Question> =  reader.lineSequence()
                    .filter { it.isNotBlank() }
                    .map {
                        val splitResult = it.split(';', ignoreCase = false)
                        ListQuestion(splitResult[0],splitResult.subList(0, splitResult.size - 1))
                    }.toList()

                dictionaryOfQuestions[name] = list
                updateValues(getQuestionResources())

                //storeContentLocally(csvContent)
                storeInDatabase(name, url)
            }
        }
    }

    private fun storeInDatabase(name: String, url: String) {
        val resources = questionResourceDatabase.getQuestionResources()
        for (n in resources) {
            println(n)
        }

        questionResourceDatabase.addQuestionResource(QuestionResource(name, Date(), url))
    }


}