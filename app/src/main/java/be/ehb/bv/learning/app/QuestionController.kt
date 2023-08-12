package be.ehb.bv.learning.app

import be.ehb.bv.learning.core.model.Question

interface QuestionController {
    fun nextQuestion()
    fun registerQuestionInterface(qi: Question.QuestionInterface)
}