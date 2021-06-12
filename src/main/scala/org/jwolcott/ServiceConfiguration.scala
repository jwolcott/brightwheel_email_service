package org.jwolcott

import io.dropwizard.Configuration

import java.beans.BeanProperty
import scala.beans.BeanProperty


class ServiceConfiguration extends Configuration {

  @BeanProperty
  val defaultProvider: String = "spendgrid"

  @BeanProperty
  val spendgridApiKey: String = ""

  @BeanProperty
  val snailgunApiKey: String = ""
}

