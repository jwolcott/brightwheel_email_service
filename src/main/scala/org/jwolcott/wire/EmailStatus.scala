package org.jwolcott.wire

import com.fasterxml.jackson.core.{JsonGenerator, JsonParser}
import com.fasterxml.jackson.databind.{DeserializationContext, SerializerProvider}
import com.fasterxml.jackson.databind.annotation.{JsonDeserialize, JsonSerialize}
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.ser.std.StdSerializer

@JsonSerialize(using = classOf[EmailStatusJsonSerializer])
@JsonDeserialize(using = classOf[EmailStatusJsonDeserializer])
sealed trait EmailStatus extends Product with Serializable {
}

object EmailStatus {
  final case object QUEUED extends EmailStatus
  final case object SENT extends EmailStatus
  final case object FAILED extends EmailStatus
}

class EmailStatusJsonSerializer extends StdSerializer[EmailStatus](classOf[EmailStatus]) {
  import EmailStatus._

  override def serialize(value: EmailStatus, gen: JsonGenerator, provider: SerializerProvider): Unit = {
    val strValue = value match {
      case QUEUED => "queued"
      case SENT  => "sent"
      case FAILED => "failed"
    }
    gen.writeString(strValue)
  }
}

class EmailStatusJsonDeserializer extends StdDeserializer[EmailStatus](classOf[EmailStatus]) {
  import EmailStatus._

  override def deserialize(p: JsonParser, ctxt: DeserializationContext): EmailStatus = {
    p.getText match {
      case "queued" => QUEUED
      case "sent" => SENT
      case "failed" => FAILED
    }
  }
}

// TODO: serdes tests for this