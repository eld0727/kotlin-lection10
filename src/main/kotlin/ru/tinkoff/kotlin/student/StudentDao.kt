package ru.tinkoff.kotlin.student

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class StudentDao(private val database: Database) {
    fun findAll() = transaction(database) {
        Students.selectAll().map(::extractStudent)
    }

    fun create(name: String): Student = transaction(database) {
        val id = Students.insertAndGetId {
            it[Students.name] = name
        }

        Student(id.value, name)
    }

    private fun extractStudent(row: ResultRow): Student = Student(
        row[Students.id].value,
        row[Students.name],
    )
}