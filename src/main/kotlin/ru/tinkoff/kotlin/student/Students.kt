package ru.tinkoff.kotlin.student

import org.jetbrains.exposed.dao.id.IntIdTable

object Students : IntIdTable() {
    val name = text("name")
}