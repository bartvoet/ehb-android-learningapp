package be.ehb.bv.learningapp.model

interface Question {
    fun ask(qi:QuestionInterface);

    fun validate(qi:QuestionInterface): QuestionFeedback

    fun askAndValidate(qi : QuestionInterface) : QuestionFeedback {
        ask(qi)
        return validate(qi)
    }

    interface QuestionInterface {
        fun askListQuestion(question: String, size: Int)
        fun getFeedback(): List<String>
    }

    interface QuestionFeedback {
        fun isOK() : Boolean
    }
}




