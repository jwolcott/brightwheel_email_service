package org.jwolcott.wire

case class Email(
                to: String,
                toName: String,
                from: String,
                fromName: String,
                subject: String,
                body: String
                )

// TODO: serdes tests for this
