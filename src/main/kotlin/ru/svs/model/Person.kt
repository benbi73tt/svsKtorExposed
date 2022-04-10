package ru.svs.model

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table


data class Person(
    val id: Int,
    var fname: String,
    var sname: String,
    var patronymic: String,
    var email: String,
    var number: String
)

object Persons : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val fname: Column<String> = varchar("fname", 255)
    val sname: Column<String> = varchar("sname", 255)
    val patronymic: Column<String> = varchar("patronymic", 255)
    val email: Column<String> = varchar("email", 255)
    val number: Column<String> = varchar("number", 255)

    override val primaryKey = PrimaryKey(id, name = "PK_Person_ID")

    fun toPerson(row: ResultRow): Person =
        Person(
            id = row[Persons.id],
            fname = row[Persons.fname],
            sname = row[Persons.sname],
            email = row[Persons.email],
            patronymic = row[Persons.patronymic],
            number = row[Persons.number]
        )
}
