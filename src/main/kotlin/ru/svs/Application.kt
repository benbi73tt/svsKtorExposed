package ru.svs

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import ru.svs.plugins.*

fun main() {
    Database.connect("jdbc:postgresql://localhost:5432/svs", driver = "org.postgresql.Driver",
        user = "postgres", password = "medalenhunt73")
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
