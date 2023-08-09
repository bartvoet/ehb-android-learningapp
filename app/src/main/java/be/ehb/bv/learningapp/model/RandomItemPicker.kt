package be.ehb.bv.learningapp.model

import java.util.*

class RandomItemPicker<T>( val items: List<T>) : ItemPicker<T> {
    private val random = Random()
    private val l : MutableList<Int> = items.withIndex().map { it.index  }.toMutableList()

    override fun pickItem(): T {
        val pickIndex = random.nextInt(l.size)
        val posInItems = l[pickIndex]
        return items[posInItems]
    }

    override fun questionsRemaining(): Boolean {
        TODO("Not yet implemented")
    }

    override fun closeItem(questionId: Int) {
        TODO("Not yet implemented")
    }


}