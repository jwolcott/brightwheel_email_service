package org.jwolcott.wire

case class EmailResponse (
  id: Option[String],
  status: EmailStatus
                         )

// TODO: serdes tests for this
