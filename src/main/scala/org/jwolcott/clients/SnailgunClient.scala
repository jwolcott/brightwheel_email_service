package org.jwolcott.clients

import org.jwolcott.wire.{SnailgunEmailRequest, SnailgunEmailResponse}
import retrofit2.Call
import retrofit2.http.{Body, GET, Header, POST, Path}

import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Produces(MediaType.APPLICATION_JSON)
trait SnailgunClient {

  @POST("/snailgun/emails/")
  def sendEmail(@Body snailgunEmailRequest: SnailgunEmailRequest, @Header("X-Api-Key") apiKey: String): Call[SnailgunEmailResponse]

  @GET("/snailgun/emails/{requestId}")
  def checkStatus(@Path requestId: String, @Header("X-Api-Key") apiKey: String): Call[SnailgunEmailResponse]
}
