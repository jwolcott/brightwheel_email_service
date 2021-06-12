package org.jwolcott.services

import org.jwolcott.ServiceConfiguration
import org.jwolcott.clients.{SnailgunClient, SpendgridClient}
import org.jwolcott.wire.{
  Email,
  EmailResponse,
  EmailStatus,
  SnailgunEmailRequest,
  SpendgridEmail
}

class EmailService(
    snailgunClient: SnailgunClient,
    spendgridClient: SpendgridClient,
    config: ServiceConfiguration
) {

  val providers = Seq[String]("snailgun", "spendgrid")

  def sendEmail(email: Email): Either[Unit, EmailResponse] = {
    // try the default first, then try the other if it failed
    val failoverOrder: Seq[String] = Seq(
      config.defaultProvider,
      providers.filterNot(_.equalsIgnoreCase(config.defaultProvider)).head
    )

    failoverOrder.head match {
      case x if x.equals("snailgun") =>
        doSnailgunSend(email) match {
          case Right(response) => // it worked, we're good!
            Right(response)
          case Left(_) => // it failed, let's try the other one
            doSpendgridSend(email)
        }
      case x if x.equals("spendgrid") =>
        doSpendgridSend(email) match {
          case Right(response) => // it worked, we're good!
            Right(response)
          case Left(_) => // it failed, let's try the other one
            doSnailgunSend(email)
        }
    }
  }

  def doSnailgunSend(email: Email): Either[Unit, EmailResponse] = {
    val call = snailgunClient.sendEmail(
      SnailgunEmailRequest(
        fromEmail = email.from,
        fromName = email.fromName,
        toEmail = email.to,
        toName = email.toName,
        subject = email.subject,
        body = email.body
      ),
      config.snailgunApiKey
    )
    val response = call.execute()
    response.code() match {
      case 200 | 201 |
          202 => // wait, the spec doesn't say what the expected code is here - 200, 201, 202?
        Right(
          EmailResponse(
            id = Some(response.body.id),
            status = response.body.status
          )
        )
      case _ => // log failed call
        // TODO: add logging here
        Left()
    }
  }

  def doSpendgridSend(email: Email): Either[Unit, EmailResponse] = {
    val call = spendgridClient.sendEmail(
      SpendgridEmail(
        sender = s"${email.fromName} ${email.from}",
        recipient = s"${email.toName} ${email.to}",
        subject = email.subject,
        body = email.body
      ),
      config.spendgridApiKey
    )
    val response = call.execute()
    response.code() match {
      case 200 => // wait, the spec doesn't say what the expected code is here - 200, 201, 202?
        Right(EmailResponse(id = None, status = EmailStatus.SENT))
      case _ => // log failed call
        // TODO: add logging here
        Left()
    }
  }
}
