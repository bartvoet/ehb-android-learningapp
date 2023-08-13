package be.ehb.bv.learning.app.session.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.ehb.bv.learning.core.model.ItemPicker
import be.ehb.bv.learning.core.model.Question
import be.ehb.bv.learning.core.model.RandomItemPicker
import java.util.logging.Logger

class QuestionSessionViewModel(private var questions : List<Question> = listOf()) : ViewModel() {

    var picker : ItemPicker<Question> = RandomItemPicker(questions)
    lateinit var currentQuestion : Question

    val currentResult: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val currentAnswers: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val currentStatus: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun selectQuestion() : Question {
        val (_, currentQuestion) = picker.pickItem()
        this.currentQuestion = currentQuestion
        return this.currentQuestion
    }

    fun initQuestions(newQuestions : List<Question>) {
        this.questions = newQuestions
        picker = RandomItemPicker(this.questions)
    }

    fun markQuestionAsFinished() {
        picker.closeLastPick()
    }

    override fun onCleared() {
        Logger.getLogger("hello").info("clear")
        picker = RandomItemPicker(questions)
        super.onCleared()
    }

    fun renew() {
        picker = RandomItemPicker(questions)
    }
}

class QuestionViewModelFactory(private val questions : List<Question> = listOf()) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return QuestionSessionViewModel(questions) as T
    }
}