package org.jwolcott.endpoints

import org.jwolcott.services.EmailService
import org.jwolcott.wire.Email

import javax.ws.rs.{POST, Path, Produces}
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/email")
@Produces(MediaType.APPLICATION_JSON)
class EmailEndpoint(emailService: EmailService) {

  @POST
  def sendEmail(
      email: Email
  ): Response = {
    // TODO: validate email fields -> Response.status(400).build() for bad input
    // TODO: strip out HTML tags? requirements say to strip, examples still have tags intact
    val response = emailService.sendEmail(email)
    response match {
      case Right(er) =>
        Response.ok(er).build() // send back the status and if present the id
      case Left(_) =>
        Response.status(404).build() // TODO: better error responses
    }
  }

  // TODO: implement checkStatus

}
