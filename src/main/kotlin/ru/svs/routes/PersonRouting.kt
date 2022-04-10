package ru.svs.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.svs.model.Person
import ru.svs.model.Persons


fun Route.personRouting() {
    route("/person") {
        get("/") {
            val persons = transaction {
                Persons.selectAll().map { Persons.toPerson(it) }
            }
            call.respond(persons)
        }
        get("/{fname}") {
            val fname = call.parameters["fname"]!!.toString()
            val persons = transaction {
                Persons.select { Persons.fname eq fname }
                    .map { Persons.toPerson(it) }
            }
            call.respond(persons)
        }
        post("/") {
            val person = call.receive<Person>()
            transaction {
                Persons.insert {
                    it[Persons.fname] = person.fname
                    it[Persons.sname] = person.sname
                    it[Persons.patronymic] = person.patronymic
                    it[Persons.number] = person.number
                    it[Persons.email] = person.email
                }
            }
            call.respond(person)
        }
    }
}

fun Application.personRouts() {
    routing { personRouting() }
}