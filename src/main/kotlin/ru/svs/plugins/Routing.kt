package ru.svs.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.svs.model.Persons
import ru.svs.routes.personRouts

fun Application.configureRouting() {
    transaction {
        SchemaUtils.create(Persons)
    }

    // Starting point for a Ktor app:
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
    personRouts()
}


