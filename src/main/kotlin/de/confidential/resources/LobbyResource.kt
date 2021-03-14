package de.confidential.resources

import de.confidential.domain.ChatRepository
import de.confidential.domain.Message
import io.quarkus.undertow.runtime.HttpSessionContext
import io.vertx.core.http.HttpServerRequest
import java.time.LocalDateTime
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.SecurityContext

@Path("/lobby")
class LobbyResource {

    @Inject
    @field: Default
    lateinit var chat: ChatRepository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(@Context session: HttpSessionContext): String {
        println("${session.state}")
        return "Welcome to the lobby"
    }

    @Path("/chat")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    fun getLast10Messages() = this.chat.getLast10()

    @Path("/chat")
    @POST()
    @Produces(MediaType.APPLICATION_JSON)
    fun say(@Context sec: SecurityContext, @FormParam("text") text: String): Message {
        val msg = Message(sec.userPrincipal.toString(), LocalDateTime.now(), text)
        chat.send(msg);
        return msg
    }

}
