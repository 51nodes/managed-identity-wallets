package net.catenax.core.custodian

// for 2.0.0-beta
// import io.ktor.server.engine.*
// import io.ktor.server.application.*

import io.ktor.server.netty.*

// for 1.6.7
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.server.engine.*
import io.ktor.client.features.json.*

import net.catenax.core.custodian.plugins.*
import net.catenax.core.custodian.models.ExceptionResponse
import net.catenax.core.custodian.models.NotFoundException
import net.catenax.core.custodian.models.BadRequestException
import net.catenax.core.custodian.routes.ssiRoutes

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    configureSockets()
    configureSerialization()

    install(DefaultHeaders)
    
    // for debugging
    install(CallLogging)

    // Installs the Kompendium Plugin and sets up baseline server metadata
    configureOpenAPI()

    install(StatusPages) {
        exception<BadRequestException> { cause ->
            call.respond(HttpStatusCode.BadRequest, ExceptionResponse(cause.message!!))
        }
        exception<NotFoundException> { cause ->
            call.respond(HttpStatusCode.NotFound, ExceptionResponse(cause.message!!))
        }
    }

    configureSecurity()

    configureRouting()
    ssiRoutes()

    configurePersistence()
}
