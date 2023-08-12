package be.ehb.bv.learning.model

interface ItemPicker<T> {
    fun questionsRemaining():Boolean
    fun pickItem(): Pair<Int,T>
    fun closeLastPick()
}