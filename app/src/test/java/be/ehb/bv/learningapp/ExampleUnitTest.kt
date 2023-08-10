package be.ehb.bv.learningapp

import be.ehb.bv.learningapp.model.RandomItemPicker
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val picker = RandomItemPicker(listOf(4,5,6))
        while(picker.questionsRemaining()) {
            val (a,b) = picker.pickItem()
            println("$a $b")
            picker.closeItem(a)
        }
        //picker.
        //assertEquals(4, 2 + 2)
    }
}