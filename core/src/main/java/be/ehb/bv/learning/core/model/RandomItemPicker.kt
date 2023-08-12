package be.ehb.bv.learning.core.model

import java.util.*

class RandomItemPicker<T>(private val items: List<T>) : ItemPicker<T> {
    private val random = Random()
    private var pickIndex = 0

    private val l : MutableList<Int> = items.withIndex().map { it.index  }.toMutableList()

    override fun pickItem(): Pair<Int,T> {
        this.pickIndex = random.nextInt(l.size)
        val posInItems = l[pickIndex]
        return this.pickIndex to items[posInItems]
    }

    override fun questionsRemaining() = l.isNotEmpty()

    override fun closeLastPick() {
        l.removeAt(this.pickIndex)
    }
}