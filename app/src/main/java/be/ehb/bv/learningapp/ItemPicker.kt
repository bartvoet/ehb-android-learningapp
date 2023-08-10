package be.ehb.bv.learningapp

interface ItemPicker<T> {
    fun questionsRemaining():Boolean
    fun closeItem(questionId: Int)
    fun pickItem(): Pair<Int,T>
}