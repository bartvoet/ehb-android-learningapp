package be.ehb.bv.learning.app.session

import be.ehb.bv.learning.core.model.Question

interface QuestionController {
    fun screenReady(qi: Question.QuestionInterface)
    fun nextQuestion()
}