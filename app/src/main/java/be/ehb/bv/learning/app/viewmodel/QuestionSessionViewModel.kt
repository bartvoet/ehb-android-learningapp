package be.ehb.bv.learning.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.ehb.bv.learning.model.ItemPicker
import be.ehb.bv.learning.model.Question
import be.ehb.bv.learning.model.RandomItemPicker
import java.util.logging.Logger

class QuestionSessionViewModel(private val questions : List<Question>) : ViewModel() {

    var picker : ItemPicker<Question> = RandomItemPicker(questions)
    lateinit var currentQuestion : Question

    fun selectQuestion() : Question {
        val (_, currentQuestion) = picker.pickItem()
        this.currentQuestion = currentQuestion
        return this.currentQuestion
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

class QuestionViewModelFactory(private val questions : List<Question>) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuestionSessionViewModel(questions) as T
    }
}