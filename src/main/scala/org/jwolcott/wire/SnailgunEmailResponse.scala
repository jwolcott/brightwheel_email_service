package org.jwolcott.wire

import com.fasterxml.jackson.annotation.JsonFormat

import java.time.LocalDateTime

case class SnailgunEmailResponse (
  id: String,
  fromEmail: String,
  fromName: String,
  toEmail: String,
  toName: String,
  subject: String,
  body: String,
  status: EmailStatus,
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ") createdAt: LocalDateTime
                                 )

// TODO: serdes tests for this
