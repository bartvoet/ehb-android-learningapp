package be.ehb.bv.learning.app.support

import be.ehb.bv.learning.core.model.ListQuestion
import be.ehb.bv.learning.core.model.Question

object SampleData {
    val SAMPLE_QUESTIONS  =
        mapOf (
            "networking"  to listOf(
                ListQuestion("Wat zijn de poorten voor HTTP en HTTPS?", listOf("80", "443")),
                ListQuestion("Waar staat DORA voor?", listOf("Discover", "Offer", "Request", "Accept"))
            ) as List<Question>,
            "programming" to listOf(
                ListQuestion("Wat zijn de favoriete programmeertalen?", listOf("Java", "Kotlin","Rust", "C")),
                ListQuestion("Wat zijn de 2 keywords in Kotlin om een variabele te definieren??",
                                    listOf("val", "var")),
            ) as List<Question>
        )
}