package be.ehb.bv.learningapp.model

class ListQuestion : Question {
    fun ask( qInterface: QuestionInterface): Answer = Answer()
}