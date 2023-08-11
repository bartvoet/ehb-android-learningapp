package be.ehb.bv.learningapp

interface ItemPicker<T> {
    fun questionsRemaining():Boolean
    fun pickItem(): Pair<Int,T>
    fun closeLastPick()
}