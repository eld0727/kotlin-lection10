package ru.tinkoff.kotlin.student

import kotlinx.serialization.Serializable

@Serializable
data class Student(val id: Int, val name: String)
