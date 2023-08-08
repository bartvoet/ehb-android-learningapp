package be.ehb.bv.learningapp.model

interface ItemPicker<T> {
    fun questionsRemaining():Boolean
    fun closeItem(questionId: Int)
    fun pickItem(numberOfQuestions: Int): Int
}