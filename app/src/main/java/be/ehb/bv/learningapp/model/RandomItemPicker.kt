package be.ehb.bv.learningapp.model

import be.ehb.bv.learningapp.ItemPicker
import java.util.*

class RandomItemPicker<T>(private val items: List<T>) : ItemPicker<T> {
    private val random = Random()

    private val l : MutableList<Int> = items.withIndex().map { it.index  }.toMutableList()

    override fun pickItem(): Pair<Int,T> {
        val pickIndex = random.nextInt(l.size)
        val posInItems = l[pickIndex]
        return pickIndex to items[posInItems]
    }

    override fun questionsRemaining() = l.isNotEmpty()

    override fun closeItem(questionId: Int) {
        l.removeAt(questionId)
    }
}