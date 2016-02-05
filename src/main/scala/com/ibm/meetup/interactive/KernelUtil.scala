package com.ibm.meetup.interactive

import com.typesafe.config.{ConfigFactory, Config}
import org.apache.toree.kernel.protocol.v5.client.SparkKernelClient
import org.apache.toree.kernel.protocol.v5.client.boot.ClientBootstrap
import org.apache.toree.kernel.protocol.v5.client.boot.layers.{StandardHandlerInitialization, StandardSystemInitialization}

/**
 * Created by shallysangal on 01/02/16.
 */
object KernelUtil {

  def apply() : SparkKernelClient = {
    val profileJSON: String = """
  {
      "stdin_port" : 48691,
      "control_port" : 44808,
      "hb_port" : 49691,
      "shell_port" : 40544,
      "iopub_port" : 43462,
      "ip" : "127.0.0.1",
      "transport" : "tcp",
      "signature_scheme" : "hmac-sha256",
      "key" : ""
  } """.stripMargin

    val config: Config = ConfigFactory.parseString(profileJSON)

    val client = (new ClientBootstrap(config)
      with StandardSystemInitialization
      with StandardHandlerInitialization).createClient()

    client
  }

}
