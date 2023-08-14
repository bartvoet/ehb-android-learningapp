package be.ehb.bv.learning.app.support

import be.ehb.bv.learning.core.model.ListQuestion
import be.ehb.bv.learning.core.model.Question

object SampleData {

    val TEST_URL = "http://10.0.2.2:8000/ports.csv"

    val SAMPLE_QUESTIONS  =
        mapOf (
            "network, port en protocol" to listOf(
                ListQuestion("Port and trasport: FTP", listOf("20","TCP")),
                ListQuestion("Port and trasport: FTP Control", listOf("21","TCP")),
                ListQuestion("Port and trasport: SSH:", listOf("22","TCP")),
                ListQuestion("Port and trasport: Telnet", listOf("23","TCP")),
                ListQuestion("Port and trasport: DNS", listOf("53","UDP","TCP")),
                ListQuestion("Port and trasport: DHCP Server", listOf("67","UDP")),
                ListQuestion("Port and trasport: DHCP Client", listOf("68","UDP")),
                ListQuestion("Port and trasport: TFTP", listOf("69","UDP")),
                ListQuestion("Port and trasport: HTTP", listOf("80","TCP")),
                ListQuestion("Port and trasport: SNMP", listOf("161","UDP")),
                ListQuestion("Port and trasport: SNMP-trap", listOf("162","UDP")),
                ListQuestion("Port and trasport: LDAP", listOf("389","TCP")),
                ListQuestion("Port and trasport: HTTPS",listOf("443","TCP")),
                ListQuestion("Port and trasport: DHCP Client (ipv6)",listOf("546","UDP")),
                ListQuestion("Port and trasport: DHCP Server (ipv6)",listOf("547","UDP"))
            ) as List<Question>,
            "programming" to listOf(
                ListQuestion("Wat zijn de favoriete programmeertalen?", listOf("Java", "Kotlin","Rust", "C")),
                ListQuestion("Wat zijn de 2 keywords in Kotlin om een variabele te definieren??",
                    listOf("val", "var")),
            ) as List<Question>

            )
}