package be.ehb.bv.learning.app.support

import be.ehb.bv.learning.core.model.ListQuestion
import be.ehb.bv.learning.core.model.Question

object SampleData {
    val SAMPLE_QUESTIONS  =
        mapOf (
            "networking"  to listOf(
                ListQuestion("http-ports", listOf("80", "443")),
                ListQuestion("DORA?", listOf("Discover", "Offer", "Request", "Accept"))
            ) as List<Question>,
            "programming" to listOf(
                ListQuestion("Favourite", listOf("Java", "Kotlin","Rust", "C")),
                ListQuestion("Favourite OS", listOf("Linux"))
            ) as List<Question>
        )

}