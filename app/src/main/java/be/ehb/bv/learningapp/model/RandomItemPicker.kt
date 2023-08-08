package be.ehb.bv.learningapp.model

import java.util.*

class RandomItemPicker<T> : ItemPicker<T> {
    private val random = Random()

    override fun pickItem(fromTotal: Int): Int {
        return random.nextInt(fromTotal)
    }

    override fun questionsRemaining(): Boolean {
        TODO("Not yet implemented")
    }

    override fun closeItem(questionId: Int) {
        TODO("Not yet implemented")
    }


}