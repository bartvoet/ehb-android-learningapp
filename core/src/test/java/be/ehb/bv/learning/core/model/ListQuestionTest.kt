package be.ehb.bv.learning.core.model

import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito


class ListQuestionTest {
    @Test
    fun askListQuestions_should_request_number_of_inputs_based_on_length() {
        val question = ListQuestion("Questions", listOf("a","b","c"))
        val mockInterface = Mockito.mock(Question.QuestionInterface::class.java)
        question.ask(mockInterface)
        Mockito.verify(mockInterface).askListQuestion("Questions",3)
    }

    @Test
    fun validate_returns_true_when_feedback_of_interface_is_an_identical_list() {
        val question = ListQuestion("Questions", listOf("a","b","c"))
        val mockInterface = Mockito.mock(Question.QuestionInterface::class.java)
        Mockito.`when`(mockInterface.getFeedback()).thenReturn(listOf("a","b","c"))
        Assert.assertEquals(true, question.validate(mockInterface).isOK())
    }

    @Test
    fun validate_returns_true_when_feedback_of_interface_is_same_list_with_different_order() {
        val question = ListQuestion("Questions", listOf("a","b","c"))
        val mockInterface = Mockito.mock(Question.QuestionInterface::class.java)
        Mockito.`when`(mockInterface.getFeedback()).thenReturn(listOf("b","a","c"))
        Assert.assertEquals(true, question.validate(mockInterface).isOK())
    }

    @Test
    fun validate_returns_false_when_feedback_of_interface_with_list_with_different_content() {
        val question = ListQuestion("Questions", listOf("a","b","c"))
        val mockInterface = Mockito.mock(Question.QuestionInterface::class.java)
        Mockito.`when`(mockInterface.getFeedback()).thenReturn(listOf("d","e","f"))
        Assert.assertEquals(false, question.validate(mockInterface).isOK())
    }

}