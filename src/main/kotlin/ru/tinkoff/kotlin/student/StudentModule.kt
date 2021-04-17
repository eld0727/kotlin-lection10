package ru.tinkoff.kotlin.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import org.kodein.di.ktor.di
import org.kodein.di.singleton
import ru.tinkoff.kotlin.student.StudentDao
import ru.tinkoff.kotlin.student.StudentService

fun Application.studentModule() {
    val service: StudentService by closestDI().instance()

    routing {
        route("/students") {
            get {
                call.respond(service.findAll())
            }
            post {
                val request = call.receive<CreateStudentRequest>()
                call.respond(service.create(request.name))
            }
        }
    }
}

fun DI.Builder.studentComponents() {
    bind<StudentDao>() with singleton { StudentDao(instance()) }
    bind<StudentService>() with singleton { StudentService(instance(), instance()) }
}

@Serializable
private data class CreateStudentRequest(val name: String)
