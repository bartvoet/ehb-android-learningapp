package be.ehb.bv.learning.core.model

interface ItemPicker<T> {
    fun questionsRemaining():Boolean
    fun pickItem(): Pair<Int,T>
    fun closeLastPick()
}