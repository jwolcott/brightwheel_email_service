package org.jwolcott.wire

case class SpendgridEmail (
  sender: String,
  recipient: String,
  subject: String,
  body: String
                          )

// TODO: serdes tests for this
