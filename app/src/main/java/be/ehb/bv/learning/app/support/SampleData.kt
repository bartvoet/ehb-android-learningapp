package be.ehb.bv.learning.app.support

import be.ehb.bv.learning.core.model.ListQuestion
import be.ehb.bv.learning.core.model.Question

object SampleData {
    val SAMPLE_QUESTIONS  =
        mapOf (
            "network, port en protocol" to listOf(
                ListQuestion("FTP", listOf("20","TCP")),
                ListQuestion("FTP Control", listOf("21","TCP")),
                ListQuestion("SSH:", listOf("22","TCP")),
                ListQuestion("Telnet", listOf("23","TCP")),
                ListQuestion("DNS", listOf("53","UDP","TCP")),
                ListQuestion("DHCP Server", listOf("67","UDP")),
                ListQuestion("DHCP Client", listOf("68","UDP")),
                ListQuestion("TFTP", listOf("69","UDP")),
                ListQuestion("HTTP", listOf("80","TCP")),
                ListQuestion("SNMP", listOf("161","UDP")),
                ListQuestion("SNMP-trap", listOf("162","UDP")),
                ListQuestion("LDAP", listOf("389","TCP")),
                ListQuestion("HTTPS",listOf("443","TCP")),
                ListQuestion("DHCP Client (ipv6)",listOf("546","UDP")),
                ListQuestion("DHCP Server (ipv6)",listOf("547","UDP"))
            ) as List<Question>,
            "programming" to listOf(
                ListQuestion("Wat zijn de favoriete programmeertalen?", listOf("Java", "Kotlin","Rust", "C")),
                ListQuestion("Wat zijn de 2 keywords in Kotlin om een variabele te definieren??",
                    listOf("val", "var")),
            ) as List<Question>

            )
}