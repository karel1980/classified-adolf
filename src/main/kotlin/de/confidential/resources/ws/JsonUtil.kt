package de.confidential.resources.ws

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import javax.inject.Singleton

@Singleton
class JsonUtil {

    val mapper: ObjectMapper = ObjectMapper()
        .registerModule(KotlinModule())
        .registerModule(
            SimpleModule("lobby")
                .setMixInAnnotation(LobbyMessage::class.java, LobbyMessageMixin::class.java)
        )

    fun toString(value: Any): String = mapper.writeValueAsString(value)
    fun toLobbyMessage(value: String): LobbyMessage = mapper.readValue(value, LobbyMessage::class.java)

}
