package be.ehb.bv.learningapp.model

class ListQuestion(val question : String, val answers: List<String>) : Question {

    override fun ask(qi: Question.QuestionInterface) {
        qi.askListQuestion(question, answers.size)
    }

    override fun validate(qi: Question.QuestionInterface): Question.QuestionFeedback {
        TODO("Not yet implemented")
    }

    override fun askAndValidate(qi: Question.QuestionInterface): Question.QuestionFeedback {
        TODO("Not yet implemented")
    }
}