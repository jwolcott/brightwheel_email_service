package org.jwolcott

import io.dropwizard.Application
import io.dropwizard.setup.{Bootstrap, Environment}
import okhttp3.OkHttpClient
import org.jwolcott.clients.{SnailgunClient, SpendgridClient}
import org.jwolcott.endpoints.EmailEndpoint
import org.jwolcott.services.EmailService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: actually try running it
object EmailApplication extends Application[ServiceConfiguration] {
  def init(bootstrap: Bootstrap[ServiceConfiguration]) {

  }

  def run(conf: ServiceConfiguration, env: Environment) {
    val clientBuilder: OkHttpClient.Builder = new OkHttpClient.Builder()
    val retrofit: Retrofit = new Retrofit.Builder()
      .baseUrl("https://bw-interviews.herokuapp.com")
      .addConverterFactory(GsonConverterFactory.create())
      .client(clientBuilder.build())
      .build


    val snailgunClient: SnailgunClient = retrofit.create[SnailgunClient]
    val spendgridClient: SpendgridClient = retrofit.create[SpendgridClient]


    val emailService: EmailService = new EmailService(snailgunClient, spendgridClient, conf)
    val emailEndpoint: EmailEndpoint = new EmailEndpoint(emailService)

    env.jersey().register(snailgunClient)
    env.jersey().register(spendgridClient)
    env.jersey().register(emailEndpoint)
    // TODO: think about healthchecks/monitoring
  }
}
