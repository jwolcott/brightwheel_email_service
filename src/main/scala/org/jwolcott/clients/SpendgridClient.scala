package org.jwolcott.clients

import org.jwolcott.wire.SpendgridEmail
import retrofit2.http.{Body, Header, POST}
import retrofit2.{Call, Retrofit}

import javax.ws.rs.Produces
import javax.ws.rs.core.{MediaType, Response}

@Produces(MediaType.APPLICATION_JSON)
trait SpendgridClient {

  @POST("/spendgrid/send_email")
  def sendEmail(@Body spendgridEmail: SpendgridEmail, @Header("X-Api-Key") apiKey: String): Call[Response]

}
