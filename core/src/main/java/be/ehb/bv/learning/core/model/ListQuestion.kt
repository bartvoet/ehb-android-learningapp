package be.ehb.bv.learning.core.model

fun <T> List<T>.equalsIgnoreOrder(other: List<T>) = this.size == other.size && this.toSet() == other.toSet()

data class ListQuestion(private val question : String, private val answers: List<String>) :
    Question {

    override fun ask(qi: Question.QuestionInterface) {
        qi.askListQuestion(question, answers.size)
    }
    override fun validate(qi: Question.QuestionInterface): Question.QuestionFeedback {
        val receivedAnswers = qi.getFeedback()
        return object : Question.QuestionFeedback {
            override fun isOK() = receivedAnswers.equalsIgnoreOrder(answers)
        }
    }
}
