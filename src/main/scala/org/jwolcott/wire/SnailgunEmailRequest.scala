package org.jwolcott.wire

case class SnailgunEmailRequest (
  fromEmail: String,
  fromName: String,
  toEmail: String,
  toName: String,
  subject: String,
  body: String
                                 )
// TODO: serdes tests for this
