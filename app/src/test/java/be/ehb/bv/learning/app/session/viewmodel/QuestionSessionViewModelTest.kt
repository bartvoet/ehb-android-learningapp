package be.ehb.bv.learning.app.session.viewmodel

import be.ehb.bv.learning.core.model.ListQuestion
import be.ehb.bv.learning.core.model.Question
import org.junit.Assert.assertEquals
import org.junit.Test

class QuestionSessionViewModelTest {
    @Test
    fun select_will_pick_a_question_from_the_injected_list() {
        val question = ListQuestion("a", listOf("b","c"))
        val listOfQuestions = listOf<Question>(question)
        val model = QuestionSessionViewModel(listOfQuestions)
        assertEquals(question, model.selectQuestion())
    }

    @Test
    fun init_will_replace_the_injected_list() {
        val question = ListQuestion("a", listOf("b","c"))
        val listOfQuestions = listOf<Question>(question)
        val model = QuestionSessionViewModel(listOfQuestions)
        assertEquals(question, model.selectQuestion())

        val newQuestion = ListQuestion("b", listOf("c","d"))
        val newListOfQuestions = listOf<Question>(newQuestion)
        model.initQuestions(newListOfQuestions)
        assertEquals(newQuestion, model.selectQuestion())
    }
}