package be.ehb.bv.learningapp.model

data class ListQuestion(private val question : String, private val answers: List<String>) : Question {

    override fun ask(qi: Question.QuestionInterface) {
        qi.askListQuestion(question, answers.size)
    }

    override fun validate(qi: Question.QuestionInterface): Question.QuestionFeedback {
        //val answers = qi.getFeedback()
        // TODO: compare lists
        return object : Question.QuestionFeedback {
            override fun isOK() = true
        }
    }
}