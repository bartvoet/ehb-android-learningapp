package be.ehb.bv.learningapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.ehb.bv.learningapp.ItemPicker
import be.ehb.bv.learningapp.model.Question
import be.ehb.bv.learningapp.model.RandomItemPicker
import java.util.logging.Logger

class QuestionSessionViewModel(private val questions : List<Question>) : ViewModel() {

    var picker : ItemPicker<Question> = RandomItemPicker(questions)

    override fun onCleared() {
        Logger.getLogger("hello").info("clear")
        picker = RandomItemPicker(questions)
        super.onCleared()
    }
}

class QuestionViewModelFactory(private val questions : List<Question>) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuestionSessionViewModel(questions) as T
    }
}